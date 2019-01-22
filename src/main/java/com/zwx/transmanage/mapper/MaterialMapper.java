package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.MaterialDto;
import com.zwx.transmanage.domain.vo.MaterialVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/4.
 */
@Mapper
public interface MaterialMapper {
    Integer countMaterial(@Param("userId") Integer userId,@Param("description") String description,@Param("type") String type);
    List<MaterialVo> selectMaterialListByUserId(@Param("userId") Integer userId, @Param("pageModel") PageModel pageModel,@Param("description") String description,@Param("type") String type);
    Integer addMaterial(MaterialDto materialDto);
    void delete(Integer id);
    Integer update(MaterialDto materialDto);
}
