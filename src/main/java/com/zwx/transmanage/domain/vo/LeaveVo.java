package com.zwx.transmanage.domain.vo;

import com.zwx.transmanage.util.DateTimeUtil;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/10/28.
 */
public class LeaveVo {
    private Integer id;
    private Integer leaveFrom;
    private Integer leaveTo;
    private String content;
    private Date createTime;
    private Integer isRead;
    private Integer parentId;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(Integer leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public Integer getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(Integer leaveTo) {
        this.leaveTo = leaveTo;
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

    public String getFormatCreateTime(){
        return DateTimeUtil.getFormatDateTimeCn(createTime);
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getWorkDate(){
        return DateTimeUtil.getFormatDay(createTime);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LeaveVo{" +
                "id=" + id +
                ", leaveFrom=" + leaveFrom +
                ", leaveTo=" + leaveTo +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isRead=" + isRead +
                ", parentId=" + parentId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
