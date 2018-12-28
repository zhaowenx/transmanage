package com.zwx.transmanage.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * Created by zhaowenx on 2018/10/31.
 */
public class QuartzRefreshLeave {

    private final static Logger logger = LoggerFactory.getLogger(QuartzRefreshLeave.class);

    /**
     * 用户登录后，20分钟cookied到期，所以定时器20分钟跑一次，用户重新登录
     */

    public void refreshLeave(){
        logger.info("--------------------quartz定时器任务start------------------");

        logger.info("--------------------quartz定时器任务end------------------");
    }
}
