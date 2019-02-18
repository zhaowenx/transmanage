package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2019/2/18.
 */
@Mapper
public interface UserRoleMapper {
    List<Integer> selectRoleByUserId(@Param("userId") Integer userId);
    void deleteUserRoleByUserId(@Param("userId") Integer userId);
    Integer addUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}
