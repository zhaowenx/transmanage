package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Role;
import com.zwx.transmanage.domain.dto.RoleDto;
import com.zwx.transmanage.domain.dto.RoleMenuDto;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.domain.vo.RoleMenuVo;
import com.zwx.transmanage.domain.vo.RoleVo;
import com.zwx.transmanage.model.*;
import com.zwx.transmanage.service.MenuService;
import com.zwx.transmanage.service.RoleService;
import com.zwx.transmanage.service.UserRoleService;
import com.zwx.transmanage.util.ResponseUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by zhaowenx on 2019/1/30.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/init")
    public String init(){
        return "role";
    }

    @RequestMapping(value = "/set-role-menu")
    public String setRoleMenu(HttpServletRequest request,HttpServletResponse response, Integer roleId){
        logger.info("roleId:"+roleId);
        RoleVo roleVo = roleService.selectRoleVoById(roleId);
        request.setAttribute("roleVo",roleVo);
        return "set-role-menu";
    }

    @GetMapping("/show_role_menu")
    @ResponseBody
    public Object show_role_menu(HttpServletRequest request, HttpServletResponse response, Integer roleId){
        logger.info("roleId:"+roleId);
        List<RoleMenuVo> roleMenuVoList=roleService.selectRoleMenuVoByRoleId(roleId);
        List<MenuVo> menuVoList = menuService.getMenu();
        if(menuVoList.size() == 0){
            return null;
        }
        List<Object> oneList = new ArrayList<>();
        for(MenuVo menuVo:menuVoList){
            RoleMenuModel roleMenuModel=new RoleMenuModel();
            roleMenuModel.setTitle(menuVo.getText());
            roleMenuModel.setValue(menuVo.getId().toString());
            roleMenuModel.setChecked(check(menuVo.getId(),roleMenuVoList));
            roleMenuModel.setData(getChildren(menuVo.getId(),roleMenuVoList));
            roleMenuModel.setDisabled(false);
            oneList.add(roleMenuModel);
        }
        logger.info("show_role_menu RoleMenuModel:"+oneList);
        return oneList;
    }

    public Object getChildren(Integer id,List<RoleMenuVo> roleMenuVoList){
        List<MenuVo> sonMenuVoList = menuService.getMenuById(id);
        if(sonMenuVoList.size()==0){
            return "";
        }else{
            List<Object> twoList = new ArrayList<>();
            for(MenuVo menuVo:sonMenuVoList){
                RoleMenuModel roleMenuModel=new RoleMenuModel();
                roleMenuModel.setTitle(menuVo.getText());
                roleMenuModel.setValue(menuVo.getId().toString());
                roleMenuModel.setChecked(check(menuVo.getId(),roleMenuVoList));
                roleMenuModel.setData(getChildren(menuVo.getId(),roleMenuVoList));
                roleMenuModel.setDisabled(false);
                twoList.add(roleMenuModel);
            }
            return twoList;
        }
    }

    public boolean check(Integer menuId,List<RoleMenuVo> roleMenuVoList){
        boolean flag = false;
        for(RoleMenuVo roleMenuVo:roleMenuVoList){
            if (menuId.equals(roleMenuVo.getMenuId())){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @GetMapping("/selectAll")
    @ResponseBody
    public ResponseVo selectAll(){
        logger.info("---------RoleController.selectAll start---------");
        List<RoleVo> roleVoList = roleService.selectAll();
        logger.info("---------RoleController.selectAll---------"+roleVoList.toString());
        if(roleVoList.size() == 0){
            return ResponseUtil.buildVo(false,ResponseCode.GET_INFORMATION_NULL.getCode(),"不存在角色，请联系管理员新增",null);
        }
        logger.info("---------RoleController.selectAll end---------");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),roleVoList);
    }

    @GetMapping("/getRoleId")
    @ResponseBody
    public ResponseVo getRoleId(Integer userId){
        logger.info("---------RoleController.getRoleId start---------");
        logger.info("---------RoleController.getRoleId userId:---------"+userId);
        if(userId == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"用户id不能为空",null);
        }
        List<Integer> roleId = userRoleService.selectRoleByUserId(userId);
        if(roleId.size() == 0){
            logger.info("---------RoleController.getRoleId end---------");
            return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
        }else{
            String roleIdL = "";
            for(Integer a:roleId){
                roleIdL = roleIdL+a+",";
            }
            roleIdL = roleIdL.substring(0,roleIdL.length()-1);
            logger.info("---------RoleController.getRoleId roleId:---------"+roleIdL);
            logger.info("---------RoleController.getRoleId end---------");
            return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),roleIdL);
        }
    }

    @GetMapping("/show")
    @ResponseBody
    public ResponseTVo show(HttpServletRequest request, HttpServletResponse response, Integer pageSize, Integer currentPage,String roleName,Integer roleType){
        logger.info("---------RoleController.show start---------");
        logger.info("---------RoleController.show pageSize:"+pageSize+"|currentPage:"+currentPage+"|roleName:"+roleName+"|roleType:"+roleType);
        if(pageSize == null || currentPage == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),ResponseCode.FILE.getMsg(),0,null);
        }
        Integer count = roleService.countRole();
        logger.info("---------RoleController.show count:"+count);
        if(count==0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有角色，您可以新增",0,null);
        }
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        List<RoleVo> roleVoList = roleService.selectRole(pageModel,roleName,roleType);
        logger.info("---------RoleController.show roleVoList:"+roleVoList.toString());
        logger.info("---------RoleController.show end---------");
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,roleVoList);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(HttpServletRequest request, HttpServletResponse response, Role role) throws Exception{
        logger.info("---------RoleController.add start---------");
        logger.info("RoleController|add|role:"+role.toString());
        if(role == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(role.getRoleName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"角色名称不能为空",null);
        }
        if(role.getRoleType() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"角色类型不能为空",null);
        }
        RoleDto roleDto=new RoleDto();
        BeanUtils.copyProperties(roleDto,role);
        Integer flag = roleService.addRole(roleDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增失败，请刷新页面重新操作",null);
        }
        logger.info("---------RoleController.add end---------");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(HttpServletRequest request, HttpServletResponse response, Role role) throws Exception{
        logger.info("---------RoleController.update start---------");
        logger.info("RoleController|update|role:"+role.toString());
        if(role == null || role.getRoleId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(role.getRoleName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"角色名称不能为空",null);
        }
        if(role.getRoleType() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"角色类型不能为空",null);
        }
        RoleDto roleDto=new RoleDto();
        BeanUtils.copyProperties(roleDto,role);
        Integer flag = roleService.updateRole(roleDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改失败，请重新操作",null);
        }
        logger.info("---------RoleController.update end---------");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestBody Role role){
        logger.info("---------RoleController.delete start---------");
        logger.info("RoleController|delete|role:"+role.toString());
        if(role == null || role.getRoleId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        roleService.deleteRole(role.getRoleId());
        roleService.deleteRoleMenuByRoleId(role.getRoleId());
        logger.info("---------RoleController.delete start---------");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/addRoleMenu")
    @ResponseBody
    public ResponseVo addRoleMenu(@RequestBody RoleIdMenuIdModel roleIdMenuIdModel){
        logger.info("roleId:"+roleIdMenuIdModel.getRoleId()+",menuId:"+roleIdMenuIdModel.getMenuId());
        if(roleIdMenuIdModel.getRoleId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"角色ID不能为空",null);
        }
        roleService.deleteRoleMenuByRoleId(roleIdMenuIdModel.getRoleId());
        if(StringUtils.isNotBlank(roleIdMenuIdModel.getMenuId())){
            String[] menuIdList = roleIdMenuIdModel.getMenuId().split("/");
            if(menuIdList.length != 0){
                for(String a:menuIdList){
                    if(a.equals("/") || "on".equals(a)){
                        continue;
                    }
                    RoleMenuDto roleMenuDto=new RoleMenuDto();
                    roleMenuDto.setMenuId(Integer.valueOf(a));
                    roleMenuDto.setRoleId(roleIdMenuIdModel.getRoleId());
                    Integer flag = roleService.addRoleMenu(roleMenuDto);
                     if(flag<0){
                         return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"保存失败，请重试",null);
                     }
                }
            }
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    public static void main(String[] args){
        String a = "on/1/3/4|5|6|2|7|8|10|11|12|20|21|23|";
        String[] b = a.split("/");
        for(String c:b){
            System.out.println(c);
        }
    }
}
