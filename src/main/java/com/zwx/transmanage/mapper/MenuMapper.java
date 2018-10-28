package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/22.
 */
@Mapper
public interface MenuMapper {
    List<MenuVo> getMenu();
    List<MenuVo> getMenuById(@Param("id") Integer id);
}
