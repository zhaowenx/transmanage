package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.MenuDto;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.mapper.MenuMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/22.
 */
//@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> getMenu() {
        return menuMapper.getMenu();
    }

    @Override
    public List<MenuVo> getMenuById(Integer id) {
        return menuMapper.getMenuById(id);
    }

    @Override
    public Integer countMenuById(Integer id) {
        return menuMapper.countMenuById(id);
    }

    @Override
    public Integer getMenuLevelById(Integer id) {
        return menuMapper.getMenuLevelById(id);
    }

    @Override
    public Integer addMenu(MenuDto menuDto) {
        return menuMapper.addMenu(menuDto);
    }

    @Override
    public Integer countChildrenById(Integer id) {
        return menuMapper.countChildrenById(id);
    }

    @Override
    public void delete(Integer id) {
        menuMapper.delete(id);
    }

    @Override
    public Integer update(MenuDto menuDto) {
        return menuMapper.update(menuDto);
    }

    @Override
    public List<Integer> getMenuByRoleId(List<Integer> roleId) {
        return menuMapper.getMenuByRoleId(roleId);
    }

    @Override
    public List<MenuVo> getMenuVoListByRoleId(List<Integer> roleId) {
        return menuMapper.getMenuVoListByRoleId(roleId);
    }
}
