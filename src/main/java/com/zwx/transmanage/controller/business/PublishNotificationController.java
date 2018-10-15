package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.PublishNotification;
import com.zwx.transmanage.domain.dto.PublishNotificationDto;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseTVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.PublishNotificationService;
import com.zwx.transmanage.service.UserService;
import com.zwx.transmanage.util.DateTimeUtil;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.ResponseUtil;
import com.zwx.transmanage.util.UserUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaowenx on 2018/10/8.
 */
@RestController
@RequestMapping("/publish")
public class PublishNotificationController {
    private final static Logger logger = LoggerFactory.getLogger(PublishNotificationController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PublishNotificationService publishNotificationService;
    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public ResponseTVo show(HttpServletRequest request, Integer pageSize, Integer currentPage){
        logger.info("PublishNotificationController|show|pageSize:"+pageSize+"|currentPage:"+currentPage);
        if(pageSize == null || currentPage == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),ResponseCode.FILE.getMsg(),0,null);
        }
        Integer count = publishNotificationService.countPublishNotification();
        logger.info("PublishNotificationController|show|count："+count);
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有公告记录，您可以新增",0,null);
        }
        logger.info("DailyController|show|count："+count);
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        List<PublishNotificationVo> publishNotificationVoList = publishNotificationService.selectAllPublishNotification(pageModel);
        List<PublishNotificationVo> publishNotificationVoList1 = new ArrayList<>();
        for(PublishNotificationVo vo:publishNotificationVoList){
            if(vo.getPublishBy()!=null){
                vo.setPublishName(userService.selectUserNameByUserId(vo.getPublishBy()));
            }
            if(vo.getCreateBy()!=null){
                vo.setCreateName(userService.selectUserNameByUserId(vo.getCreateBy()));
            }
            if(vo.getUpdateBy()!=null){
                vo.setUpdateName(userService.selectUserNameByUserId(vo.getUpdateBy()));
            }
            publishNotificationVoList1.add(vo);
        }
        logger.info("PublishNotificationController|show|publishNotificationVoList:"+publishNotificationVoList.toString());
        logger.info("PublishNotificationController|show|publishNotificationVoList1:"+publishNotificationVoList1.toString());
        logger.info("PublishNotificationController|show|pageModel:"+pageModel.toString());
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,publishNotificationVoList1);
    }

    @PostMapping("/add")
    public ResponseVo add(HttpServletRequest request, PublishNotification publishNotification) throws Exception{
        logger.info("PublishNotificationController|add|publishNotification:"+publishNotification.toString());
        if(publishNotification == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(publishNotification.getNotificationTitle())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"通知标题不能为空",null);
        }
        if(StringUtils.isBlank(publishNotification.getNotificationContent())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"内容不能为空",null);
        }
        if(publishNotification.getStatus()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否发布不能为空",null);
        }
        if(publishNotification.getStick()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否置顶不能为空",null);
        }
        if(publishNotification.getMessageType()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"消息类型不能为空",null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);

        PublishNotificationDto publishNotificationDto= new PublishNotificationDto();
        BeanUtils.copyProperties(publishNotificationDto,publishNotification);
        if(publishNotification.getStatus()==0){
            publishNotificationDto.setPublishBy(userId);
            publishNotificationDto.setPublishDate(DateTimeUtil.formatDateTime(new Date()));
        }else{
            publishNotificationDto.setPublishBy(0);
        }
        publishNotificationDto.setCreateBy(userId);
        publishNotificationDto.setUpdateBy(userId);
        Integer flag = publishNotificationService.addPublishNotification(publishNotificationDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/update")
    public ResponseVo update(HttpServletRequest request,PublishNotification publishNotification) throws Exception{
        logger.info("PublishNotificationController|update|publishNotification:"+publishNotification.toString());
        if(publishNotification == null || publishNotification.getId() ==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(publishNotification.getNotificationTitle())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"通知标题不能为空",null);
        }
        if(StringUtils.isBlank(publishNotification.getNotificationContent())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"内容不能为空",null);
        }
        if(publishNotification.getStatus()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否发布不能为空",null);
        }
        if(publishNotification.getStick()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否置顶不能为空",null);
        }
        if(publishNotification.getMessageType()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"消息类型不能为空",null);
        }
        PublishNotificationDto publishNotificationDto= new PublishNotificationDto();
        BeanUtils.copyProperties(publishNotificationDto,publishNotification);
        Integer userId = UserUtil.getUserId(request,redisUtil);
        PublishNotificationVo publishNotificationVo=publishNotificationService.selectPublisNotificationById(publishNotification.getId());
        logger.info("PublishNotificationController|update|publishNotificationVo:"+publishNotificationVo.toString());
        logger.info("PublishNotificationController|update|createBy:"+publishNotificationVo.getCreateBy());
        if(publishNotificationVo.getCreateBy() != userId && userId!=1){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_SYSTEM_ERROR.getCode(),"只有公告的创建者和超级管理员才能修改",null);
        }
        if(publishNotification.getStatus()==0){
            publishNotificationDto.setPublishBy(userId);
            publishNotificationDto.setPublishDate(DateTimeUtil.formatDateTime(new Date()));
        }else{
            publishNotificationDto.setPublishBy(0);
        }
        publishNotificationDto.setUpdateBy(userId);
        Integer flag = publishNotificationService.update(publishNotificationDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    public ResponseVo delete(HttpServletRequest request,@RequestBody PublishNotification publishNotification){
        logger.info("PublishNotificationController|delete|publishNotification:"+publishNotification.toString());
        if(publishNotification == null || publishNotification.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        PublishNotificationVo publishNotificationVo=publishNotificationService.selectPublisNotificationById(publishNotification.getId());
        logger.info("PublishNotificationController|delete|publishNotificationVo:"+publishNotificationVo.toString());
        logger.info("PublishNotificationController|delete|createBy:"+publishNotificationVo.getCreateBy());
        if(publishNotificationVo.getCreateBy() != userId && userId!=1){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_SYSTEM_ERROR.getCode(),"只有公告的创建者和超级管理员才能删除",null);
        }
        publishNotificationService.delete(publishNotification.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    /**
     * 屏蔽
     * @param publishNotification
     * @return
     */
    @PostMapping("/shield")
    public ResponseVo shield(HttpServletRequest request,@RequestBody PublishNotification publishNotification) throws Exception{
        logger.info("PublishNotificationController|shield|publishNotification:"+publishNotification.toString());
        if(publishNotification == null || publishNotification.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        PublishNotificationVo publishNotificationVo=publishNotificationService.selectPublisNotificationById(publishNotification.getId());
        logger.info("PublishNotificationController|shield|publishNotificationVo:"+publishNotificationVo.toString());
        logger.info("PublishNotificationController|shield|createBy:"+publishNotificationVo.getCreateBy());
        if(publishNotificationVo.getCreateBy() != userId && userId!=1){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_SYSTEM_ERROR.getCode(),"只有公告的创建者和超级管理员才能屏蔽",null);
        }
        PublishNotificationDto publishNotificationDto= new PublishNotificationDto();
        BeanUtils.copyProperties(publishNotificationDto,publishNotification);
        publishNotificationDto.setUpdateBy(userId);
        Integer flag = publishNotificationService.shield(publishNotificationDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"屏蔽失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/showPublishDetail")
    public ResponseVo showPublishDetail(@RequestBody PublishNotification publishNotification){
        logger.info("PublishNotificationController|showPublishDetail|publishNotification:"+publishNotification.toString());
        if(publishNotification==null||publishNotification.getId()==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        PublishNotificationVo publishNotificationVo = publishNotificationService.selectPublisNotificationById(publishNotification.getId());
        if(publishNotificationVo == null){
            return ResponseUtil.buildVo(false,ResponseCode.GET_INFORMATION_NULL.getCode(),ResponseCode.GET_INFORMATION_NULL.getMsg(),null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),publishNotificationVo);
    }
}
