package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.RoleDto;
import com.zwx.transmanage.domain.dto.RoleMenuDto;
import com.zwx.transmanage.domain.vo.RoleMenuVo;
import com.zwx.transmanage.domain.vo.RoleVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2019/1/30.
 */
@Mapper
public interface RoleMapper {

    Integer countRole();
    List<RoleVo> selectRole(@Param("pageModel") PageModel pageModel,@Param("roleName") String roleName,@Param("roleType") Integer roleType);
    Integer addRole(RoleDto roleDto);
    Integer updateRole(RoleDto roleDto);
    void deleteRole(@Param("roleId") Integer roleId);
    RoleVo selectRoleVoById(Integer roleId);
    List<RoleMenuVo> selectRoleMenuVoByRoleId(Integer roleId);
    void deleteRoleMenuByRoleId(@Param("roleId") Integer roleId);
    Integer addRoleMenu(RoleMenuDto roleMenuDto);
    List<RoleVo> selectAll();
}
