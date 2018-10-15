package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.domain.vo.PublishNotificationVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.service.DailyService;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaowenx on 2018/10/12.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DailyService dailyService;

    @RequestMapping(value = "/show")
    public String show(HttpServletRequest request) throws Exception{
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        if(userVo==null || userVo.getId()==null){
            return "logout";
        }
        List<DailyVo> dailyVoList = dailyService.selectDailyVoByUserId(userVo.getId());
        request.setAttribute("dailyVoList",dailyVoList);
        return "welcome";
    }
}
