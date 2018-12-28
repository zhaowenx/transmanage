package com.zwx.transmanage.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhaowenx on 2018/11/7.
 */
@Aspect
@Component
public class CheckLoginAop {

    private final static Logger logger = LoggerFactory.getLogger(CheckLoginAop.class);

    //切点配置
    @Pointcut("@annotation(com.zwx.transmanage.aop.CheckLogin)")
    public void checkLogin() {
    }

    @Before(value = "com.zwx.transmanage.aop.CheckLoginAop.checkLogin()")
    public void before(){
        logger.info("-----------------校验是否登录开始1--------------------");
    }

    @After("com.zwx.transmanage.aop.CheckLoginAop.checkLogin() && @annotation(checkLogin)")
    public void after(JoinPoint joinPoint, CheckLogin checkLogin) {
        logger.info("-----------------校验是否登录开始2--------------------");
    }

    @AfterReturning(returning = "result",pointcut = "com.zwx.transmanage.aop.CheckLoginAop.checkLogin() && @annotation(checkLogin)")
    public void afterReturning(JoinPoint joinPoint, CheckLogin checkLogin, Object result){
        logger.info("-----------------校验是否登录开始3--------------------");
        logger.info("result:"+result);
        logger.info("joinPoint:"+joinPoint);
    }

    @AfterThrowing(pointcut = "checkLogin()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.info("-----------------异常通知--------------------");
        e.printStackTrace();
    }
}
