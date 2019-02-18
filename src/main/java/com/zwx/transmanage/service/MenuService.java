package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.MenuDto;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/22.
 */
public interface MenuService {
    List<MenuVo> getMenu();
    List<MenuVo> getMenuById(Integer id);
    Integer countMenuById(Integer id);
    Integer getMenuLevelById(Integer id);
    Integer addMenu(MenuDto menuDto);
    Integer countChildrenById(Integer id);
    void delete(Integer id);
    Integer update(MenuDto menuDto);
    List<Integer> getMenuByRoleId(List<Integer> roleId);
    List<MenuVo> getMenuVoListByRoleId( List<Integer> roleId);
}
