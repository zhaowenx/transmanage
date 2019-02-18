package com.zwx.transmanage.domain.vo;

/**
 * Created by zhaowenx on 2019/1/30.
 */
public class RoleVo {

    /**
     * ROLE
     */
    private Integer roleId;//角色ID
    private String roleName;//角色名称
    private Integer roleType;//角色类型
    private String descript;//描述

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
