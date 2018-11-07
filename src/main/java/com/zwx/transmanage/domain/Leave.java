package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/10/28.
 */
public class Leave {

    private Integer id;
    private Integer leaveFrom;
    private Integer leaveTo;
    private String content;
    private String createTime;
    private Integer isRead;
    private Integer parentId;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", leaveFrom=" + leaveFrom +
                ", leaveTo=" + leaveTo +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isRead=" + isRead +
                ", parentId=" + parentId +
                '}';
    }
}
