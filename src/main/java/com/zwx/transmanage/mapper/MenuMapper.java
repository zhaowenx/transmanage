package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.MenuDto;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.model.PageModel;
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
    Integer countMenuById(@Param("id") Integer id);
    Integer getMenuLevelById(@Param("id") Integer id);
    Integer addMenu(MenuDto menuDto);
    Integer countChildrenById(@Param("id") Integer id);
    void delete(@Param("id") Integer id);
    Integer update(MenuDto menuDto);
}
