package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.LeaveDto;
import com.zwx.transmanage.domain.vo.LeaveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/29.
 */
@Mapper
public interface LeaveMapper {
    List<LeaveVo> getReceiveLeaveVo(@Param("userId") Integer userId);
    List<LeaveVo> getSendLeaveVo(@Param("userId") Integer userId);
    Integer sendLeave(LeaveDto leaveDto);
    Integer updateIsReadById(@Param("id") Integer id);
    LeaveVo selectContentByParentId(@Param("id") Integer id);
}
