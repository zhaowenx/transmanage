package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.PublishNotificationDto;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/8.
 */
public interface PublishNotificationService {
    Integer countPublishNotification();
    List<PublishNotificationVo> selectAllPublishNotification(PageModel pageModel);
    Integer addPublishNotification(PublishNotificationDto publishNotificationDto);
    Integer update(PublishNotificationDto publishNotificationDto);
    PublishNotificationVo selectPublisNotificationById(Integer id);
    void delete(Integer id);
    Integer shield(PublishNotificationDto publishNotificationDto);
    List<PublishNotificationVo> selectAllPublish();
}
