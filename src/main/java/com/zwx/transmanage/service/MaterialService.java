package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.MaterialDto;
import com.zwx.transmanage.domain.vo.MaterialVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/4.
 */
public interface MaterialService {
    Integer countMaterial(Integer userId,String description,String type);
    List<MaterialVo> selectMaterialListByUserId(UserVo userVo, PageModel pageModel,String description,String type);
    Integer addMaterial(MaterialDto materialDto);
    void delete(Integer id);
    Integer update(MaterialDto materialDto);
}
