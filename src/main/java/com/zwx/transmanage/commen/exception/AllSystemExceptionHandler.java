package com.zwx.transmanage.commen.exception;

import com.zwx.transmanage.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaowenx on 2018/10/30.
 * 全局异常处理
 */
//@ControllerAdvice 控制器增强，一个被@Component注册的组件。配合@ExceptionHandler来增强所有的@requestMapping方法。
@ControllerAdvice
public class AllSystemExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(AllSystemExceptionHandler.class);

    //声明要捕获的异常
    //@ExceptionHandler(Exception.class)  用来捕获@requestMapping的方法中所有抛出的exception。
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String allSystemExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof BusinessException) {
            logger.error("BusinessException:业务异常："+e.getMessage());
            BusinessException businessException = (BusinessException)e;
            request.setAttribute("msg",businessException.getErrorCode().getMsg());
            request.setAttribute("code",businessException.getErrorCode().getCode());
            request.setAttribute("flag","0");//业务异常
            return "error";
        }
        request.setAttribute("msg",e.getMessage());
        request.setAttribute("flag","1");//系统异常
        return "error";
    }

}
