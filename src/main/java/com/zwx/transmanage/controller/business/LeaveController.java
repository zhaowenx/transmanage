package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.aop.CheckLogin;
import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Leave;
import com.zwx.transmanage.domain.dto.LeaveDto;
import com.zwx.transmanage.domain.vo.LeaveVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.LeaveService;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.ResponseUtil;
import com.zwx.transmanage.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhaowenx on 2018/10/29.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    private final static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/show")
    @CheckLogin
    public String show(HttpServletRequest request, HttpServletResponse response){
        logger.info("LeaveController|show|start");
        Integer userId = UserUtil.getUserId(request,redisUtil);
        logger.info("LeaveController|show|userId:"+userId);
        List<LeaveVo> receiveLeaveVoList = leaveService.getReceiveLeaveVo(userId);
        logger.info("LeaveController|show|receiveLeaveVoList:"+receiveLeaveVoList.toString());
        List<LeaveVo> sendLeaveVoList = leaveService.getSendLeaveVo(userId);
        logger.info("LeaveController|show|sendLeaveVoList:"+sendLeaveVoList.toString());
        request.setAttribute("receiveLeaveVoList",receiveLeaveVoList);
        request.setAttribute("sendLeaveVoList",sendLeaveVoList);
        request.setAttribute("userId",userId);
        logger.info("LeaveController|show|end");
        return "leave";
    }

    @RequestMapping(value = "/sendLeave")
    @ResponseBody
    public ResponseVo sendLeave(@RequestBody Leave leave){
        logger.info("LeaveController|sendLeave|start");
        logger.info("LeaveController|sendLeave|leave:"+leave.toString());
        if(leave == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(leave.getLeaveFrom() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"消息发送人不能为空",null);
        }
        if(leave.getLeaveTo() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"消息接收人不能为空",null);
        }
        String content = leave.getContent().trim();
        if(StringUtils.isBlank(content)){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"消息内容不能为空",null);
        }
        LeaveDto leaveDto = new LeaveDto();
        leaveDto.setLeaveFrom(leave.getLeaveFrom());
        leaveDto.setLeaveTo(leave.getLeaveTo());
        leaveDto.setContent(content);
        leaveDto.setIsRead(2);
        if(leave.getParentId() !=null){
            leaveDto.setParentId(leave.getParentId());
        }else{
            leaveDto.setParentId(0);
        }
        if(leave.getParentId() !=null){
            Integer update = leaveService.updateIsReadById(leave.getParentId());
            logger.info("LeaveController|sendLeave|update:"+update);
            if(update<0){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"修改原消息失败",null);
            }else{
                Integer flag = leaveService.sendLeave(leaveDto);
                logger.info("LeaveController|sendLeave|flag:"+flag);
                if(flag<0){
                    return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"发送消息失败",null);
                }
            }
        }else{
            Integer flag = leaveService.sendLeave(leaveDto);
            logger.info("LeaveController|sendLeave|flag:"+flag);
            if(flag<0){
                return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"发送消息失败",null);
            }
        }
        logger.info("LeaveController|sendLeave|end");
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @RequestMapping(value = "/selectReceiveContent")
    @ResponseBody
    public ResponseVo selectReceiveContent(@RequestBody Leave leave){
        logger.info("LeaveController|selectReceiveContent|start");
        if(leave == null || leave.getId()==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        LeaveVo leaveVo = leaveService.selectContentByParentId(leave.getId());
        logger.info("LeaveController|selectReceiveContent|leaveVo:"+leaveVo.toString());
        logger.info("LeaveController|selectReceiveContent|end");
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),leaveVo);
    }

}
