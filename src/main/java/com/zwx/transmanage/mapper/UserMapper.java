package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.UserDto;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/23.
 */
@Mapper
public interface UserMapper {
    Integer insertUser(UserDto user);
    Integer updateUser(UserDto userDto);
    Integer deleteUserByName(String userName);
    void dropUserByName(String userName);
    UserVo selectUserByName(String userName);
    UserVo selectUserById(Integer id);
    UserVo checkUserNameAndPassWord(UserDto userDto);
    void updateUserAfterLogin(UserDto userDto);
    Integer updatePassWord(@Param("id") Integer id,@Param("password") String password);

    String selectUserNameByUserId(Integer userId);
    Integer selectUserVoByPhoneAndUserName(@Param("mobile") String mobile,@Param("userName") String userName);
    Integer addUser(UserDto userDto);
    List<UserVo> selectAllNotSuper(@Param("pageModel") PageModel pageModel);
    Integer countUserNotSuper();
    void deleteUserById(@Param("id") Integer id);
}
