package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.vo.UserRoleVo;
import com.zwx.transmanage.mapper.UserRoleMapper;
import com.zwx.transmanage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2019/2/18.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Integer> selectRoleByUserId(Integer userId) {
        return userRoleMapper.selectRoleByUserId(userId);
    }

    @Override
    public void deleteUserRoleByUserId(Integer userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
    }

    @Override
    public Integer addUserRole(Integer userId, Integer roleId) {
        return userRoleMapper.addUserRole(userId, roleId);
    }
}
