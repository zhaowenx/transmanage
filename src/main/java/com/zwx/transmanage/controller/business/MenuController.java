package com.zwx.transmanage.controller.business;

import com.google.gson.Gson;
import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.vo.MenuVo;
import com.zwx.transmanage.model.MenuModel;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.MenuService;
import com.zwx.transmanage.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhaowenx on 2018/10/22.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/getMenu",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo getMenu(){
        logger.info("MenuController|getMenu|start");

        List<Object> oneList = new ArrayList<>();

        List<MenuVo> menuVoList = menuService.getMenu();
        logger.info("MenuController|getMenu|menuVoList:"+menuVoList.toString());
        if(menuVoList.size()==0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"还无菜单，您可以联系管理员",null);
        }
        for(MenuVo menuVo:menuVoList){
            List<MenuVo> sonMenuVoList = menuService.getMenuById(menuVo.getId());
            logger.info("MenuController|getMenu|sonMenuVoList:"+sonMenuVoList.toString());
            if(sonMenuVoList.size()==0){
                MenuModel menuModel1 = new MenuModel();
                menuModel1.setText(menuVo.getText());
                menuModel1.setIcon(menuVo.getIcon());
                menuModel1.setHref(menuVo.getHref());
                menuModel1.setSubset("");
                oneList.add(menuModel1);
            }else{
                List<Object> twoList = new ArrayList<>();
                for(MenuVo menuVo1:sonMenuVoList){
                    MenuModel menuModel2 = new MenuModel();
                    menuModel2.setText(menuVo1.getText());
                    menuModel2.setIcon(menuVo1.getIcon());
                    menuModel2.setHref(menuVo1.getHref());
                    menuModel2.setSubset("");
                    twoList.add(menuModel2);
                }
                MenuModel menuModel3 = new MenuModel();
                menuModel3.setText(menuVo.getText());
                menuModel3.setIcon(menuVo.getIcon());
                menuModel3.setHref(menuVo.getHref());
                Gson gson = new Gson();
                Object subset = gson.toJson(twoList);
                menuModel3.setSubset(subset);
                oneList.add(menuModel3);
            }
        }

        Gson gson = new Gson();
        String data = gson.toJson(oneList);
        logger.info("MenuController|getMenu|data"+data);

        logger.info("MenuController|getMenu|end");
        return ResponseUtil.buildVo(true, ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),data);
    }
}
