package com.zwx.transmanage.service;

/**
 * Created by zhaowenx on 2018/10/18.
 */
public interface ShowBirthdayService {
    /**
     * 定时器补发没有发送成功的信息
     * */
    void timerTaskShowBirthday();
}
