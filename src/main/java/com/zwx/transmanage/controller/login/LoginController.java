package com.zwx.transmanage.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.zwx.transmanage.commen.constant.*;
import com.zwx.transmanage.domain.dto.UserDto;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.domain.User;
import com.zwx.transmanage.model.ValidateCodeReturn;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.service.PublishNotificationService;
import com.zwx.transmanage.service.UserService;
import com.zwx.transmanage.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

/**
 * Created by zhaowenx on 2018/8/23.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String SESSION_ID = "sessionId";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private PublishNotificationService publishNotificationService;

    /**
     * 登录操作，先获取登录密匙
     * @param user
     * @return
     */
    @RequestMapping("/getSecret")
    @ResponseBody
    public ResponseVo getSecret(HttpServletRequest req, HttpServletResponse resp,@RequestBody User user)throws Exception{
        logger.info("LoginController|getSecret|user:"+user.toString());

        /**
         * 每一次登录都当成是重新登录，清除cookie和redis
         */
        removeCookieRedis(req,resp);

        if(user==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }

        if(StringUtils.isBlank(user.getUserName())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"用户名不能为空",null);
        }

        if(StringUtils.isBlank(user.getPassWord())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"密码不能为空",null);
        }

        if(!CommenUtil.isInteger(user.getPassWord())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"密码必须是数字",null);
        }
        if(user.getPassWord().length()!=6){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"密码必须是6位",null);
        }

        if(StringUtils.isBlank(user.getValidateCode())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"验证码不能为空",null);
        }

        //校验验证码
        // 获取cookie
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            logger.error("登录校验获取验证码COOKIE信息失败，用户名【" + user.getUserName() + "】");
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_UUID_CODE_ERROR.getCode(),"获取COOKIE失败，请刷新登录页面",null);
        }
        // 获取cookie中的验证码KEY
        String validateCodeKey = "";
        for (Cookie cookie : cookies) {
            // 找到匹配的COOKIE信息
            if (CookieNameConstant.VALIDATE_CODE.equals(cookie.getName())) {
                validateCodeKey = cookie.getValue();
            }
        }
        if (validateCodeKey == "") {
            logger.error("登录校验获取验证码COOKIE信息失败，用户名【" + user.getUserName() + "】");
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_UUID_CODE_ERROR.getCode(),"验证码校验错误，请刷新验证码",null);
        }

        String validateCode = redisUtil.get(validateCodeKey);

        if(StringUtils.isBlank(validateCode)){
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_UUID_CODE_ERROR.getCode(),"验证码校验错误，请刷新验证码",null);
        }

        if(!validateCode.toLowerCase().equals(user.getValidateCode().toLowerCase())){
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_UUID_CODE_ERROR.getCode(),"请输入正确的验证码",null);
        }

        //密匙
        String loginKey = getUUid(user);
        return ResponseUtil.buildVo(true,ResponseCode.LOGIN_UUID_CODE_SUCCESS.getCode(),ResponseCode.LOGIN_UUID_CODE_SUCCESS.getMsg(),loginKey);
    }

    @RequestMapping("/toCheck")
    @ResponseBody
    public ResponseVo toCheck(HttpServletRequest req, HttpServletResponse resp,@RequestBody User user,HttpSession session) throws Exception{
        logger.info("LoginController|toCheck|user:"+user.toString());

        if(user==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }

        if(StringUtils.isBlank(user.getUserName())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"用户名不能为空",null);
        }

        if(StringUtils.isBlank(user.getPassWord())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"密码不能为空",null);
        }

        String passWord = decodePassWord(user);
        user.setPassWord(passWord);

        UserVo userVo = userService.checkUserNameAndPassWord(user);

        if(userVo==null || userVo.getId() ==null){
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_CODE_ERROR.getCode(),"用户名或密码错误，请重新输入",null);
        }

        if("Y".equals(userVo.getIsDelete())){
            return ResponseUtil.buildVo(false, ResponseCode.LOGIN_CODE_ERROR.getCode(),"该账号已被删除",null);
        }

        /**
         * 登录成功后将用户信息存放到redis中
         * redis的key先经过加密存放到cookie中
         */
        // 生成KEY保存在cookie中
        String redisKey = RandomUtil.createRandom(10)+userVo.getId()+RandomUtil.createRandom(10);
        // 生成cookie
        Cookie cookie = new Cookie(CookieNameConstant.LOGIN_COOKIE, redisKey);
        // 设置失效时间，不设置过期时间就是会话
        // cookie.setMaxAge(365 * 24 * 60 * 60);
        // 设置路径
        // 这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        // 添加cookie
        resp.addCookie(cookie);
        String userVoJson = JSONObject.toJSONString(userVo);
        redisUtil.set(redisKey, userVoJson,RedisTimeConstant.ONE_DAY);

        logger.info("LoginController|toCheck|userVo: "+userVo.toString());

        /**
         * 登录成功后向session中存放sessionId
         */
        session.setAttribute("sessionId",userVo.getId());

        /**
         * 登录成功后修改用户信息
         * 修改 登录次数、最后一次登录时间、最后登录的IP、更新时间
         */
        UserDto userDto=new UserDto();
        userDto.setLoginTimes(userVo.getLoginTimes()+1);
        userDto.setLastIp(IpUtils.getIpAddr(req));
        userDto.setUserName(userVo.getUserName());
        userService.updateUserAfterLogin(userDto);

        return ResponseUtil.buildVo(true,ResponseCode.LOGIN_CODE_SUCCESS.getCode(),ResponseCode.LOGIN_CODE_SUCCESS.getMsg(),userVo);
    }

    /***
     * 跳转欢迎页面
     * @return
     */
    @RequestMapping(value = "/toIndex")
    public String toIndex(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession(true);
        if(session.getAttribute(SESSION_ID) == null){
            return "logout";
        }
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        List<PublishNotificationVo> publishNotificationVoList = publishNotificationService.selectAllPublish();
        request.setAttribute("user", userVo);
        request.setAttribute("publishNotificationVOList",publishNotificationVoList);
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        removeCookieRedis(request,response);
        return "login";
    }


    @RequestMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        // 获取验证码信息
        ValidateCodeReturn validateCodeReturn = ValidateCodeUtil.getValidateCode(80 , 30, 4, new Color(242, 242, 242));
        // 生成redis获取验证码的KEY保存到cookie中
        String validateCodeKey = EncodeDes3.encode(CipherUtil.ALGORITHM_3DES);
        redisUtil.remove(validateCodeKey);
        // 保存到cookie
        Cookie cookie = new Cookie(CookieNameConstant.VALIDATE_CODE, validateCodeKey);
        // 设置失效时间，不设置过期时间就是会话
        // cookie.setMaxAge(365 * 24 * 60 * 60);
        // 设置路径
        // 这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        // cookie.setHttpOnly(true);
        // 添加cookie
        resp.addCookie(cookie);
        // 保存验证码到redis，时间为5分钟
        redisUtil.set(validateCodeKey,validateCodeReturn.getValidateCode(), RedisTimeConstant.FIVE_MINUTES);
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(validateCodeReturn.getValidateCodeImg(), "jpeg", sos);
        sos.close();
    }

    public String getUUid(User user){
        //1.通过userName获取秘钥
        String loginKey = KeyNameConstant.LOGIN_KEY_UUID + RandomUtil.createRandom(20);

        logger.info("LoginController|getUUid|loginKey:"+loginKey);

        //2.保存到redis
        redisUtil.set(user.getUserName(), loginKey, RedisTimeConstant.ONE_MINUTES);

        return loginKey;
    }

    public String decodePassWord(User user){
        logger.info("解密前密码："+user.getPassWord());
        //加密密码
        String des3UUID = user.getPassWord();
        //用户名称
        String userName = user.getUserName();
        //1.解密des3
        String passWord = "";
        try {
            passWord = EncodeDes3.decode(des3UUID, redisUtil.get(user.getUserName()));
            //从redis中删除
            redisUtil.remove(userName);
        } catch (Exception e) {
            logger.error("用户" + userName + "des3解密异常！");
            e.printStackTrace();
        }
        logger.info("解密后密码："+passWord);
        return passWord;
    }

    public void removeCookieRedis(HttpServletRequest request,HttpServletResponse response){
        // 清除cookie信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieNameConstant.LOGIN_COOKIE.equals(cookie.getName())) {
                    // 设置cookie的生命为0（删除）
                    cookie.setMaxAge(0);
                    // 必须设置路径，否则无效
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    // 清除redies
                    try {
                        String redisKey = cookie.getValue();
                        redisUtil.remove(redisKey);
                        logger.info("LoginController|removeCookieRedis|redis:"+redisUtil.get(redisKey));
                    } catch (Exception e) {
                        logger.error("用户注销删除缓存信息失败");
                    }
                }
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionId",null);
        logger.info("LoginController|removeCookieRedis|session:"+session.getAttribute("sessionId"));
    }
}
