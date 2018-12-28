package com.zwx.transmanage.quartz;

import com.zwx.transmanage.service.ShowBirthdayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhaowenx on 2018/10/18.
 */
public class QuartzShowBirthday {
    private final static Logger logger = LoggerFactory.getLogger(QuartzShowBirthday.class);

    @Autowired
    private ShowBirthdayService showBirthdayService;

    /**
     * 通过查询通讯录里面的生日，如果生日在当前系统时间，则生成一个公告消息
     */

    public void showBirthday(){
        logger.info("quartz定时器任务开始：将生日为当天的信息生成一个公告消息");
        showBirthdayService.timerTaskShowBirthday();
        logger.info("--------------------quartz定时器任务结束-------------------");
    }
}
