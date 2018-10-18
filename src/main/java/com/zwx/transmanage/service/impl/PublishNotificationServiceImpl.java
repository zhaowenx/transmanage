package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.PublishNotificationDto;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.mapper.PublishNotificationMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.PublishNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/8.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("publishNotificationService")
public class PublishNotificationServiceImpl implements PublishNotificationService{

    @Autowired
    private PublishNotificationMapper publishNotificationMapper;

    @Override
    public Integer countPublishNotification() {
        return publishNotificationMapper.countPublishNotification();
    }

    @Override
    public List<PublishNotificationVo> selectAllPublishNotification(PageModel pageModel) {
        return publishNotificationMapper.selectAllPublishNotification(pageModel);
    }

    @Override
    public Integer addPublishNotification(PublishNotificationDto publishNotificationDto) {
        return publishNotificationMapper.addPublishNotification(publishNotificationDto);
    }

    @Override
    public Integer update(PublishNotificationDto publishNotificationDto) {
        return publishNotificationMapper.update(publishNotificationDto);
    }

    @Override
    public PublishNotificationVo selectPublisNotificationById(Integer id) {
        return publishNotificationMapper.selectPublisNotificationById(id);
    }

    @Override
    public void delete(Integer id) {
        publishNotificationMapper.delete(id);
    }

    @Override
    public Integer shield(PublishNotificationDto publishNotificationDto) {
        return publishNotificationMapper.shield(publishNotificationDto);
    }

    @Override
    public List<PublishNotificationVo> selectAllPublish() {
        return publishNotificationMapper.selectAllPublish();
    }

    @Override
    public List<PublishNotificationVo> selectPublishNotificationByMessageType(Integer messageType) {
        return publishNotificationMapper.selectPublishNotificationByMessageType(messageType);
    }
}
