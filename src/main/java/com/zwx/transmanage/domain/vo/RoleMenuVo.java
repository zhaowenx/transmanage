package com.zwx.transmanage.domain.vo;

/**
 * Created by zhaowenx on 2019/1/30.
 */
public class RoleMenuVo {
    /**
     * role_menu
     */
    private Integer roleId;
    private Integer menuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuVo{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
