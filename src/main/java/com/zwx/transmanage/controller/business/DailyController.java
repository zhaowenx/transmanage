package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Daily;
import com.zwx.transmanage.domain.dto.DailyDto;
import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseTVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.DailyService;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.ResponseUtil;
import com.zwx.transmanage.util.UserUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaowenx on 2018/9/3.
 */
@RestController
@RequestMapping("/daily")
public class DailyController {
    private final static Logger logger = LoggerFactory.getLogger(DailyController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DailyService dailyService;

    @GetMapping("/show")
    public ResponseTVo show(HttpServletRequest request, Integer pageSize, Integer currentPage){
        logger.info("DailyController|show|pageSize:"+pageSize+"|currentPage:"+currentPage);
        if(pageSize == null || currentPage == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),ResponseCode.FILE.getMsg(),0,null);
        }
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        logger.info("DailyController|show|userId:"+userVo.getId());
        Integer count = dailyService.countDaily(userVo.getId());
        logger.info("DailyController|show|count："+count);
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有日报记录，您可以新增",0,null);
        }
        logger.info("DailyController|show|count："+count);
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        List<DailyVo> dailyVoList = dailyService.selectDailyListByUserId(userVo,pageModel);
        logger.info("DailyController|show|dailyVoList:"+dailyVoList.toString());
//        pageModel.setDataList(dailyVoList);
        logger.info("DailyController|show|pageModel:"+pageModel.toString());
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,dailyVoList);
    }

    @PostMapping("/add")
    public ResponseVo add(HttpServletRequest request, Daily daily) throws Exception{
        logger.info("DailyController|add|daily:"+daily.toString());
        if(daily == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(daily.getContent())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"日报内容不能为空",null);
        }
        if(StringUtils.isBlank(daily.getIsEvection())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否出差不能为空",null);
        }
        if(StringUtils.isBlank(daily.getDailyDate())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"日报日期不能为空",null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        Integer count = dailyService.countDailyByDate(daily.getDailyDate(),userId);
        if(count>0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"日报一天只能新增一次，如要新增，请点击修改按钮修改",null);
        }
        DailyDto dailyDto= new DailyDto();
        BeanUtils.copyProperties(dailyDto,daily);
        dailyDto.setUserId(userId);
        Integer flag = dailyService.addDaily(dailyDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody Daily daily){
        logger.info("DailyController|delete|daily:"+daily.toString());
        if(daily == null || daily.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        dailyService.delete(daily.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/update")
    public ResponseVo update(Daily daily) throws Exception{
        logger.info("DailyController|update|daily:"+daily.toString());
        if(daily == null || daily.getId() ==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(daily.getContent())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"日报内容不能为空",null);
        }
        if(StringUtils.isBlank(daily.getIsEvection())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"是否出差不能为空",null);
        }
        DailyDto dailyDto= new DailyDto();
        BeanUtils.copyProperties(dailyDto,daily);
        Integer flag = dailyService.update(dailyDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }
}
