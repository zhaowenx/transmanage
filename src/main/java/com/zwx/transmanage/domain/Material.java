package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/9/4.
 */
public class Material {
    /**
     * material
     * 资料表
     */
    private Integer id;
    private Integer userId;//用户ID
    private String description;//描述
    private String url;//路径
    private String type;//分类
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
