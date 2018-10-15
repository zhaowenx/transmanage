package com.zwx.transmanage.domain.vo;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/9/27.
 */
public class PublishNotificationVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 通知标题
     */
    private String notificationTitle;
    /**
     * 内容
     */
    private String notificationContent;
    /**
     * 发布人
     */
    private Integer publishBy;

    /**
     * 发布时间
     */
    private Date publishDate;
    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 发布状态，0：发布，1：未发布
     */
    private Integer status;
    /**
     * 置顶，1：置顶，2：未置顶
     */
    private Integer stick;
    /**
     * 消息类型
     */
    private Integer messageType;
    /**
     * 附件信息
     */
    private String messageAttachment;
    private Integer isDelete;

    public String publishName;
    public String createName;
    public String updateName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Integer getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(Integer publishBy) {
        this.publishBy = publishBy;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStick() {
        return stick;
    }

    public void setStick(Integer stick) {
        this.stick = stick;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageAttachment() {
        return messageAttachment;
    }

    public void setMessageAttachment(String messageAttachment) {
        this.messageAttachment = messageAttachment;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Override
    public String toString() {
        return "PublishNotificationVo{" +
                "id=" + id +
                ", notificationTitle='" + notificationTitle + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", publishBy=" + publishBy +
                ", publishDate=" + publishDate +
                ", createBy=" + createBy +
                ", createDate=" + createDate +
                ", updateBy=" + updateBy +
                ", updateDate=" + updateDate +
                ", status=" + status +
                ", stick=" + stick +
                ", messageType=" + messageType +
                ", messageAttachment='" + messageAttachment + '\'' +
                ", isDelete=" + isDelete +
                ", publishName='" + publishName + '\'' +
                ", createName='" + createName + '\'' +
                ", updateName='" + updateName + '\'' +
                '}';
    }
}
