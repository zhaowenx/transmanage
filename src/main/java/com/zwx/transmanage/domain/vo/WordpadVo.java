package com.zwx.transmanage.domain.vo;

import com.zwx.transmanage.domain.Wordpad;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/8/31.
 */
public class WordpadVo {
    private Integer id;//ID
    private Integer userId;//用户ID
    private String title;//标题
    private String content;//内容
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String isDelete;//是否删除，Y：是，N：否
    private String isShield;//是否屏蔽，Y：是，N：否

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsShield() {
        return isShield;
    }

    public void setIsShield(String isShield) {
        this.isShield = isShield;
    }

    @Override
    public String toString() {
        return "WordpadVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete='" + isDelete + '\'' +
                ", isShield='" + isShield + '\'' +
                '}';
    }
}
