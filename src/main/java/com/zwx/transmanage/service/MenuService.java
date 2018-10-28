package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.vo.MenuVo;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/22.
 */
public interface MenuService {
    List<MenuVo> getMenu();
    List<MenuVo> getMenuById(Integer id);
}
