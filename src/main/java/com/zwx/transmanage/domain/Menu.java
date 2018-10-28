package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/10/22.
 */
public class Menu {

    private Integer id;
    private String text;//菜单名称
    private String icon;//菜单图标
    private String href;//访问地址
    private Integer parentId;//父菜单id,所有一级菜单的父菜单id都为0
    private Integer available;//是否可用，1：可用，2：不可用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", parentId=" + parentId +
                ", available=" + available +
                '}';
    }
}
