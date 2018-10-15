package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/9/3.
 */
public class Daily {
    /**
     * daily
     * 日报表
     */
    private Integer id;//ID
    private Integer userId;//用户ID
    private String content;//内容
    private String isEvection;//是否出差，Y：是，N：否
    private String dailyDate;//日报日期
    private String createTime;//创建时间
    private String updateTime;//更新时间

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", isEvection='" + isEvection + '\'' +
                ", dailyDate='" + dailyDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
