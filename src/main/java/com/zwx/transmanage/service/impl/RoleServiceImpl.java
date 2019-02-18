package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.RoleDto;
import com.zwx.transmanage.domain.dto.RoleMenuDto;
import com.zwx.transmanage.domain.vo.RoleMenuVo;
import com.zwx.transmanage.domain.vo.RoleVo;
import com.zwx.transmanage.mapper.RoleMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2019/1/30.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer countRole() {
        return roleMapper.countRole();
    }

    @Override
    public List<RoleVo> selectRole(PageModel pageModel,String roleName,Integer roleType) {
        return roleMapper.selectRole(pageModel,roleName,roleType);
    }

    @Override
    public Integer addRole(RoleDto roleDto) {
        return roleMapper.addRole(roleDto);
    }

    @Override
    public Integer updateRole(RoleDto roleDto) {
        return roleMapper.updateRole(roleDto);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public RoleVo selectRoleVoById(Integer roleId) {
        return roleMapper.selectRoleVoById(roleId);
    }

    @Override
    public List<RoleMenuVo> selectRoleMenuVoByRoleId(Integer roleId) {
        return roleMapper.selectRoleMenuVoByRoleId(roleId);
    }

    @Override
    public void deleteRoleMenuByRoleId(Integer roleId) {
        roleMapper.deleteRoleMenuByRoleId(roleId);
    }

    @Override
    public Integer addRoleMenu(RoleMenuDto roleMenuDto) {
        return roleMapper.addRoleMenu(roleMenuDto);
    }

    @Override
    public List<RoleVo> selectAll() {
        return roleMapper.selectAll();
    }
}
