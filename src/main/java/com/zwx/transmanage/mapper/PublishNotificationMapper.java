package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.PublishNotificationDto;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/8.
 */
@Mapper
public interface PublishNotificationMapper {
    Integer countPublishNotification();
    List<PublishNotificationVo> selectAllPublishNotification(@Param("pageModel") PageModel pageModel);
    Integer addPublishNotification(PublishNotificationDto publishNotificationDto);
    Integer update(PublishNotificationDto publishNotificationDto);
    PublishNotificationVo selectPublisNotificationById(@Param("id")Integer id);
    void delete(Integer id);
    Integer shield(PublishNotificationDto publishNotificationDto);
    List<PublishNotificationVo> selectAllPublish();
}
