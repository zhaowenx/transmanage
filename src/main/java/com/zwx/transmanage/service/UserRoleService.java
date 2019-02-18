package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.vo.UserRoleVo;

import java.util.List;

/**
 * Created by zhaowenx on 2019/2/18.
 */
public interface UserRoleService {
    List<Integer> selectRoleByUserId(Integer userId);
    void deleteUserRoleByUserId(Integer userId);
    Integer addUserRole(Integer userId,Integer roleId);
}
