package com.zwx.transmanage.controller.business;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Menu;
import com.zwx.transmanage.domain.dto.MenuDto;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.model.*;
import com.zwx.transmanage.service.MenuService;
import com.zwx.transmanage.util.ResponseUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhaowenx on 2018/10/22.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/getMenu",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo getMenu(){
        logger.info("MenuController|getMenu|start");

        List<Object> oneList = new ArrayList<>();

        List<MenuVo> menuVoList = menuService.getMenu();
        logger.info("MenuController|getMenu|menuVoList:"+menuVoList.toString());
        if(menuVoList.size()==0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"还无菜单，您可以联系管理员",null);
        }
        //递归
        for(MenuVo menuVo:menuVoList){
            MenuModel menuModel = new MenuModel();
            menuModel.setText(menuVo.getText());
            menuModel.setIcon(menuVo.getIcon());
            menuModel.setHref(menuVo.getHref());
            menuModel.setSubset(getSubset(menuVo.getId()));
            oneList.add(menuModel);
        }

        logger.info("MenuController|getMenu|data"+oneList);
        logger.info("MenuController|getMenu|end");
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),oneList);
    }

    @RequestMapping(value = "/init")
    public String init(HttpServletRequest request, HttpServletResponse response){
        return "menu";
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo tree(HttpServletRequest request, HttpServletResponse response){
        logger.info("-------------MenuController|tree|start------------");
        List<MenuVo> menuVoList = menuService.getMenu();
        logger.info("MenuController|tree|menuVoList:"+menuVoList.toString());
        if(menuVoList.size()==0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"还无一级菜单，你可以点击新增",null);
        }

        List<Object> oneList = new ArrayList<>();
        for(MenuVo menuVo:menuVoList){
            MenuInitModel menuInitModel = new MenuInitModel();
            menuInitModel.setId(menuVo.getId());
            menuInitModel.setName(menuVo.getText());
            menuInitModel.setChildren(getChildren(menuVo.getId()));
            menuInitModel.setMenuLevel(menuVo.getMenuLevel());
            oneList.add(menuInitModel);
        }

        logger.info("MenuController|tree|data"+oneList);
        logger.info("-------------MenuController|tree|end------------");
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),oneList);
    }


    public Object getChildren(Integer id){
        List<MenuVo> sonMenuVoList = menuService.getMenuById(id);
        if(sonMenuVoList.size()==0){
            return "";
        }else{
            List<Object> twoList = new ArrayList<>();
            for(MenuVo menuVo:sonMenuVoList){
                MenuInitModel menuInitModel = new MenuInitModel();
                menuInitModel.setId(menuVo.getId());
                menuInitModel.setName(menuVo.getText());
                menuInitModel.setChildren(getChildren(menuVo.getId()));
                menuInitModel.setMenuLevel(menuVo.getMenuLevel());
                twoList.add(menuInitModel);
            }
            return twoList;
        }
    }

    public Object getSubset(Integer id){
        List<MenuVo> sonMenuVoList = menuService.getMenuById(id);
        if(sonMenuVoList.size()==0){
            return "";
        }else{
            List<Object> twoList = new ArrayList<>();
            for(MenuVo menuVo1:sonMenuVoList){
                MenuModel menuModel = new MenuModel();
                menuModel.setText(menuVo1.getText());
                menuModel.setIcon(menuVo1.getIcon());
                menuModel.setHref(menuVo1.getHref());
                menuModel.setSubset(getSubset(menuVo1.getId()));
                twoList.add(menuModel);
            }
            return twoList;
        }
    }

    @GetMapping("/show")
    @ResponseBody
    public ResponseTVo show(Integer parentId){
        logger.info("MenuController|show|parentId:"+parentId);
        if(parentId == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"系统异常，父菜单不可能为空",0,null);
        }
        Integer count = menuService.countMenuById(parentId);
        logger.info("MenuController|show|count："+count);
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"子菜单为空，您可以新增",0,null);
        }

        List<MenuVo> menuVoList = menuService.getMenuById(parentId);
        logger.info("MenuController|show|dailyVoList:"+menuVoList.toString());

        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,menuVoList);
    }

    @GetMapping("/getMenuLevel")
    @ResponseBody
    public ResponseVo getMenuLevel(Integer id){
        if (id == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        Integer menuLevel = menuService.getMenuLevelById(id);
        if(menuLevel == null){
            return ResponseUtil.buildVo(false,ResponseCode.GET_INFORMATION_ERROR.getCode(),"系统错误，菜单级别不可能为空",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),menuLevel);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(HttpServletRequest request, Menu menu) throws Exception{
        logger.info("MenuController|add|Menu:"+menu.toString());
        if(menu == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(menu.getText())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"菜单名称不能为空",null);
        }
//        if(menu.getParentId() !=0 && StringUtils.isBlank(menu.getHref())){
//            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"访问地址不能为空",null);
//        }
        if(menu.getParentId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"父菜单ID不能为空",null);
        }
        Integer menuLevel = null;
        if(menu.getParentId() == 0){
            menuLevel = 1;
        }else{
            menuLevel = menuService.getMenuLevelById(menu.getParentId())+1;
        }
        MenuDto menuDto=new MenuDto();
        BeanUtils.copyProperties(menuDto,menu);
        menuDto.setMenuLevel(menuLevel);
        menuDto.setIcon("&#xe857;");
        Integer flag = menuService.addMenu(menuDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestBody Menu menu){
        logger.info("MenuController|delete|Menu:"+menu.toString());
        if(menu == null || menu.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        Integer countChildren = menuService.countChildrenById(menu.getId());
        if(countChildren>0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"拥有子菜单，不能被删除",null);
        }
        menuService.delete(menu.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(HttpServletRequest request, Menu menu) throws Exception{
        logger.info("MenuController|update|Menu:"+menu.toString());
        if(menu == null || menu.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(menu.getText())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"菜单名称不能为空",null);
        }
        MenuDto menuDto=new MenuDto();
        BeanUtils.copyProperties(menuDto,menu);
        Integer flag = menuService.update(menuDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }
}
