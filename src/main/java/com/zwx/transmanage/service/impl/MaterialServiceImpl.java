package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.MaterialDto;
import com.zwx.transmanage.domain.vo.MaterialVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.mapper.MaterialMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/4.
 */
//@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public Integer countMaterial(Integer userId) {
        return materialMapper.countMaterial(userId);
    }

    @Override
    public List<MaterialVo> selectMaterialListByUserId(UserVo userVo, PageModel pageModel) {
        return materialMapper.selectMaterialListByUserId(userVo.getId(),pageModel);
    }

    @Override
    public Integer addMaterial(MaterialDto materialDto) {
        return materialMapper.addMaterial(materialDto);
    }

    @Override
    public void delete(Integer id) {
        materialMapper.delete(id);
    }

    @Override
    public Integer update(MaterialDto materialDto) {
        return materialMapper.update(materialDto);
    }
}
