package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.User;
import com.zwx.transmanage.domain.dto.UserDto;
import com.zwx.transmanage.domain.vo.RoleVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseTVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.service.RoleService;
import com.zwx.transmanage.service.UserRoleService;
import com.zwx.transmanage.service.UserService;
import com.zwx.transmanage.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaowenx on 2018/8/23.
 */
@Controller
//@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加
//@RestController
//标识业务层的类，用来找到业务层对象
@ComponentScan({"com.zwx.transmanage.service"})
//标识持久层mapper接口，用来找到mapper对象
@MapperScan("com.zwx.transmanage.mapper")
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/replenish")
    public String replenish(HttpServletRequest request){
        Integer userId = UserUtil.getUserId(request,redisUtil);
        UserVo userVo = userService.selectUserById(userId);
        request.setAttribute("user",userVo);
        return "update-user-information";
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updateUser(User user) throws Exception{
        logger.info("UserController|updateUser|user："+user.toString());
        if(user == null || user.getId() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(user.getUserName())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"用户名不能为空",null);
        }
        if(StringUtils.isBlank(user.getMobile())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"手机不能为空",null);
        }

        Integer count = userService.selectUserVoByPhoneAndUserName(user.getMobile(),user.getUserName());
        if(count>1){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"用户名或手机号码重复",null);
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto,user);
        Integer flag = userService.updateUser(userDto);
        if(flag<0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
        }
        UserVo userVo=userService.selectUserById(user.getId());
        UserUtil.refreshUser(userVo,redisUtil);
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @RequestMapping(value = "/updatePassWord",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updatePassWord(String oldPassWord,String newPassWord1,String newPassWord2,HttpServletRequest request){
        logger.info("UserController|updatePassWord|oldPassWord:"+oldPassWord);
        logger.info("UserController|updatePassWord|newPassWord1:"+newPassWord1);
        logger.info("UserController|updatePassWord|newPassWord2:"+newPassWord2);
        if(StringUtils.isBlank(oldPassWord)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"原密码不能为空",null);
        }
        if(StringUtils.isBlank(newPassWord1)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"新密码不能为空",null);
        }
        if(StringUtils.isBlank(newPassWord2)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"第二次输入的新密码不能为空",null);
        }
        if(!newPassWord1.equals(newPassWord2)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"两次输入的新密码要相同",null);
        }
        if(!CommenUtil.isInteger(newPassWord1)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"新密码必须是数字",null);
        }
        if(newPassWord1.length()!=6){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"新密码必须是6位",null);
        }
        if(oldPassWord.equals(newPassWord1)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"新密码不能和旧密码相同",null);
        }

        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassWord(oldPassWord);
        UserVo userVo1 = userService.checkUserNameAndPassWord(user);
        if(userVo1==null||userVo1.getId()==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"原密码输入有误",null);
        }
        Integer flag = userService.updatePassWord(userVo1.getId(),newPassWord1);
        if(flag<0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo selectUserByName(@RequestParam String name){
        logger.info("UserController|selectUserByName|name:"+name);
        if(StringUtils.isBlank(name)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        UserVo userVo= userService.selectUserByName(name);
        if(Objects.isNull(userVo) || userVo.getId() == null){
            return ResponseUtil.buildVo(false, ResponseCode.GET_INFORMATION_NULL.getCode(),ResponseCode.GET_INFORMATION_NULL.getMsg(),null);
        }
        logger.info("UserController|selectUserByName|userVo:"+userVo.toString());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),userVo);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseVo addUser(HttpServletRequest request,User user) throws  Exception{
        logger.info("UserController|addUser|user:"+user.toString());
        if(user == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(user.getUserName())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"用户名不能为空",null);
        }
        if(StringUtils.isBlank(user.getPassWord())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"密码不能为空",null);
        }
        if(StringUtils.isBlank(user.getMobile())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"手机号码不能为空",null);
        }
        Integer count = userService.selectUserVoByPhoneAndUserName(user.getMobile(),user.getUserName());
        if(count>0){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"用户名或手机号码重复",null);
        }
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        List<Integer> loginRoleId = userRoleService.selectRoleByUserId(userVo.getId());
        for(Integer a:loginRoleId){
            if(a !=1){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_SYSTEM_ERROR.getCode(),"不是超级管理员不能添加用户",null);
            }
        }

        //新增用户的时候，同时设置角色，可以设置多个
        //设置了超级管理员角色就不能设置其他角色，不是超机管理员可以设置多个角色
        if(StringUtils.isBlank(user.getRoleId())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"角色不能为空",null);
        }else{
            String[] roleId = user.getRoleId().split(",");
            if(roleId.length != 1){
                for(String a : roleId){
                    RoleVo roleVo = roleService.selectRoleVoById(Integer.valueOf(a));
                    if(roleVo.getRoleType() == 1){
                        return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"超级管理员角色和其他角色不能同时存在",null);
                    }
                }
            }
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto,user);
        logger.info("UserController|addUser|userDto:"+userDto.toString());
        Integer flag = userService.addUser(userDto);
        logger.info("UserController|addUser|flag:"+flag);
        if(flag<0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
        }

        //新增用户角色表
        String[] roleId = user.getRoleId().split(",");
        for(String b:roleId){
            Integer flag1 = userRoleService.addUserRole(userDto.getId(),Integer.valueOf(b));
            if(flag1<0){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
            }
        }

        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public ResponseTVo selectAll(Integer pageSize, Integer currentPage,String userName,String mobile){
        logger.info("UserController|selectAll|start");
        logger.info("UserController|selectAll|pageSize:"+pageSize+" currentPage:"+currentPage+" userName:"+userName+" mobile："+mobile);
        Integer count = userService.countUserNotSuper();
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有普通用户，您可以新增",0,null);
        }
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        List<UserVo> userVoList = userService.selectAll(pageModel,userName,mobile);
        logger.info("UserController|selectAll|userVoList："+userVoList.toString());
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,userVoList);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public ResponseVo deleteUser(HttpServletRequest request, @RequestBody User user){
        logger.info("UserController|deleteUser|user："+user.toString());
        if(user == null || user.getId() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        List<Integer> userRoleId = userRoleService.selectRoleByUserId(user.getId());
        for(Integer a:userRoleId){
            if(a == 1){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_SYSTEM_ERROR.getCode(),"超级管理员用户不能删除",null);
            }
        }
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        List<Integer> loginRoleId = userRoleService.selectRoleByUserId(userVo.getId());
        for(Integer a:loginRoleId){
            if(a != 1){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_SYSTEM_ERROR.getCode(),"不是超级管理员不能删除用户",null);
            }
        }
        userService.deleteUserById(user.getId());
        userRoleService.deleteUserRoleByUserId(user.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @RequestMapping(value = "/receiveUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo receiveUser(HttpServletRequest request){
        logger.info("UserController|receiveUser|start");
        Integer userId = UserUtil.getUserId(request,redisUtil);
        List<UserVo> userVoList= userService.selectReceiveUser(userId);
        if(userVoList.size()==0){
            return ResponseUtil.buildVo(false, ResponseCode.GET_INFORMATION_NULL.getCode(),ResponseCode.GET_INFORMATION_NULL.getMsg(),null);
        }
        for(UserVo userVo:userVoList){
            userVo.setRealName(StringUtils.isBlank(userVo.getRealName())?" ":userVo.getRealName());
        }
        logger.info("UserController|receiveUser|userVoList:"+userVoList.toString());
        logger.info("UserController|receiveUser|count:"+userVoList.size());
        logger.info("UserController|receiveUser|end");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),userVoList);
    }


    @PostMapping("/addUserRole")
    @ResponseBody
    public ResponseVo addUserRole(User user){
        logger.info("UserController|addUserRole|start");
        logger.info("UserController|addUserRole|user:"+user.toString());
        if(user == null || user.getId() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(user.getRoleId())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"角色不能为空",null);
        }
        String[] roleId = user.getRoleId().split(",");
        if(roleId.length != 1){
            for(String a : roleId){
                RoleVo roleVo = roleService.selectRoleVoById(Integer.valueOf(a));
                if(roleVo.getRoleType() == 1){
                    return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"超级管理员角色和其他角色不能同时存在",null);
                }
            }
        }
        userRoleService.deleteUserRoleByUserId(user.getId());
        //新增用户角色表
        for(String b:roleId){
            Integer flag1 = userRoleService.addUserRole(user.getId(),Integer.valueOf(b));
            if(flag1<0){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
            }
        }
        logger.info("UserController|addUserRole|end");
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }
}
