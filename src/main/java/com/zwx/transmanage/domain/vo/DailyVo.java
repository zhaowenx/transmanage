package com.zwx.transmanage.domain.vo;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/9/3.
 */
public class DailyVo {
    private Integer id;//ID
    private Integer userId;//用户ID
    private String content;//内容
    private String isEvection;//是否出差，Y：是，N：否
    private String dailyDate;//日报日期
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String userName;//用户名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsEvection() {
        return isEvection;
    }

    public void setIsEvection(String isEvection) {
        this.isEvection = isEvection;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DailyVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", isEvection='" + isEvection + '\'' +
                ", dailyDate='" + dailyDate + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userName='" + userName + '\'' +
                '}';
    }
}
