package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.LeaveDto;
import com.zwx.transmanage.domain.vo.LeaveVo;
import com.zwx.transmanage.mapper.LeaveMapper;
import com.zwx.transmanage.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/29.
 */
//@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<LeaveVo> getReceiveLeaveVo(Integer userId) {
        return leaveMapper.getReceiveLeaveVo(userId);
    }

    @Override
    public List<LeaveVo> getSendLeaveVo(Integer userId) {
        return leaveMapper.getSendLeaveVo(userId);
    }

    @Override
    public Integer sendLeave(LeaveDto leaveDto) {
        return leaveMapper.sendLeave(leaveDto);
    }

    @Override
    public Integer updateIsReadById(Integer id) {
        return leaveMapper.updateIsReadById(id);
    }

    @Override
    public LeaveVo selectContentByParentId(Integer id) {
        return leaveMapper.selectContentByParentId(id);
    }
}
