package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.mapper.MenuMapper;
import com.zwx.transmanage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/22.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
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
}
