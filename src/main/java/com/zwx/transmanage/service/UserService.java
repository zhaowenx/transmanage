package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.User;
import com.zwx.transmanage.domain.dto.UserDto;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/23.
 */
public interface UserService {
    Integer insertUser(UserDto user);
    Integer updateUser(UserDto user);
    Integer deleteUserByName(String userName);
    void dropUserByName(String userName);
    UserVo selectUserByName(String userName);
    UserVo selectUserById(Integer id);
    UserVo checkUserNameAndPassWord(User user);
    void updateUserAfterLogin(UserDto userDto);
    Integer updatePassWord(Integer id,String password);

    String selectUserNameByUserId(Integer userId);
    Integer selectUserVoByPhoneAndUserName(String mobile,String userName);
    Integer addUser(UserDto userDto);
    List<UserVo> selectAllNotSuper(PageModel pageModel);
    Integer countUserNotSuper();
    void deleteUserById(Integer id);

    List<UserVo> selectReceiveUser(Integer userId);
}
