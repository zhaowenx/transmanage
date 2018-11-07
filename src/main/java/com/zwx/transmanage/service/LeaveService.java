package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.LeaveDto;
import com.zwx.transmanage.domain.vo.LeaveVo;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/29.
 */
public interface LeaveService {
    List<LeaveVo> getReceiveLeaveVo(Integer userId);
    List<LeaveVo> getSendLeaveVo(Integer userId);
    Integer sendLeave(LeaveDto leaveDto);
    Integer updateIsReadById(Integer id);
    LeaveVo selectContentByParentId(Integer id);
}
