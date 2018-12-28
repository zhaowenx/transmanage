package com.zwx.transmanage.aop;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;

/**
 * Created by zhaowenx on 2018/11/7.
 * 该注解用来判断是否登录，未登录处理跳转到登录页面
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckLogin {
}
