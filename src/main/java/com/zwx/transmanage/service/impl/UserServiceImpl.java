package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.User;
import com.zwx.transmanage.domain.dto.UserDto;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.mapper.UserMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/23.
 */
//是为了找到对应的UserMapper对象
//@ComponentScan({"com.zwx.transmanage.mapper"})
//表示这是service
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer insertUser(UserDto user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer updateUser(UserDto user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteUserByName(String userName) {
        return userMapper.deleteUserByName(userName);
    }

    @Override
    public void dropUserByName(String userName) {
        userMapper.dropUserByName(userName);
    }

    @Override
    public UserVo selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public UserVo selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public UserVo checkUserNameAndPassWord(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setPassWord(user.getPassWord());
        return userMapper.checkUserNameAndPassWord(userDto);
    }

    @Override
    public void updateUserAfterLogin(UserDto userDto) {
        userMapper.updateUserAfterLogin(userDto);
    }

    @Override
    public Integer updatePassWord(Integer id, String password) {
        return userMapper.updatePassWord(id,password);
    }

    @Override
    public String selectUserNameByUserId(Integer userId) {
        return userMapper.selectUserNameByUserId(userId);
    }

    @Override
    public Integer selectUserVoByPhoneAndUserName(String mobile, String userName) {
        return userMapper.selectUserVoByPhoneAndUserName(mobile, userName);
    }

    @Override
    public Integer addUser(UserDto userDto) {
        return userMapper.addUser(userDto);
    }

    @Override
    public List<UserVo> selectAll(PageModel pageModel,String userName,String mobile) {
        return userMapper.selectAll(pageModel,userName,mobile);
    }

    @Override
    public Integer countUserNotSuper() {
        return userMapper.countUserNotSuper();
    }

    @Override
    public List<UserVo> selectReceiveUser(Integer userId) {
        return userMapper.selectReceiveUser(userId);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
