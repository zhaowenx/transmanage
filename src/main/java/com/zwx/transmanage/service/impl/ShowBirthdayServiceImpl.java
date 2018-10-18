package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.service.AddressBookService;
import com.zwx.transmanage.service.ShowBirthdayService;
import com.zwx.transmanage.util.DateTimeUtil;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/10/18.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("showBirthdayService")
public class ShowBirthdayServiceImpl implements ShowBirthdayService{

    private final static Logger logger = LoggerFactory.getLogger(ShowBirthdayServiceImpl.class);

    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void timerTaskShowBirthday() {
        logger.info("timerTaskShowBirthday开始：");
        String date = DateTimeUtil.getFormatDay(new Date());
        logger.info("当前日期："+date);
        logger.info("timerTaskShowBirthday结束：");
    }
}
