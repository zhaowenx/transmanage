package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/9/27.
 */
public class PublishNotification {

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
    private String publishDate;
    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private String updateDate;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
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

    @Override
    public String toString() {
        return "PublishNotification{" +
                "id=" + id +
                ", notificationTitle='" + notificationTitle + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", publishBy=" + publishBy +
                ", publishDate='" + publishDate + '\'' +
                ", createBy=" + createBy +
                ", createDate='" + createDate + '\'' +
                ", updateBy=" + updateBy +
                ", updateDate='" + updateDate + '\'' +
                ", status=" + status +
                ", stick=" + stick +
                ", messageType=" + messageType +
                ", messageAttachment='" + messageAttachment + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
