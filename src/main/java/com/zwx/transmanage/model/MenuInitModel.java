package com.zwx.transmanage.model;

/**
 * Created by zhaowenx on 2019/1/28.
 */
public class MenuInitModel {

    private Integer id;
    private String name;
    private Object children;
    private Integer menuLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    @Override
    public String toString() {
        return "MenuInitModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                ", menuLevel=" + menuLevel +
                '}';
    }
}
