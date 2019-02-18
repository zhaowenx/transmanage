package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.RoleDto;
import com.zwx.transmanage.domain.dto.RoleMenuDto;
import com.zwx.transmanage.domain.vo.RoleMenuVo;
import com.zwx.transmanage.domain.vo.RoleVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2019/1/30.
 */
public interface RoleService {
    Integer countRole();
    List<RoleVo> selectRole(PageModel pageModel,String roleName,Integer roleType);
    Integer addRole(RoleDto roleDto);
    Integer updateRole(RoleDto roleDto);
    void deleteRole(Integer roleId);
    RoleVo selectRoleVoById(Integer roleId);
    List<RoleMenuVo> selectRoleMenuVoByRoleId(Integer roleId);
    void deleteRoleMenuByRoleId(Integer roleId);
    Integer addRoleMenu(RoleMenuDto roleMenuDto);
    List<RoleVo> selectAll();
}
