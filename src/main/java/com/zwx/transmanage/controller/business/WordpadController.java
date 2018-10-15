package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Wordpad;
import com.zwx.transmanage.domain.dto.WordpadDto;
import com.zwx.transmanage.domain.vo.WordpadVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.WordpadService;
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
 * Created by zhaowenx on 2018/8/31.
 */
@RestController
@RequestMapping("/wordpad")
public class WordpadController {

    private final static Logger logger = LoggerFactory.getLogger(WordpadController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WordpadService wordpadService;

    @RequestMapping(value = "/showWordPad",method = RequestMethod.POST)
    public ResponseVo toShow(HttpServletRequest request,Integer pageSize,Integer currentPage){
        logger.info("WordpadController|showWordPad|pageSize:"+pageSize+"|currentPage:"+currentPage);
        if(pageSize == null || currentPage == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        logger.info("WordpadController|showWordPad|userId:"+userId);
        Integer count = wordpadService.countWordpadList(userId);
        if(count == 0){
            return ResponseUtil.buildVo(false,ResponseCode.GET_INFORMATION_NULL.getCode(),ResponseCode.GET_INFORMATION_NULL.getMsg(),null);
        }
        logger.info("WordpadController|showWordPad|count："+count);
        List<WordpadVo> wordpadVoList = wordpadService.selectWordpadByUserId(userId,(currentPage - 1) * pageSize,pageSize);
        logger.info("WordpadController|showWordPad|wordpadVoList:"+wordpadVoList.toString());
        PageModel pageModel=new PageModel(currentPage,pageSize,count,wordpadVoList);
        logger.info("WordpadController|showWordPad|pageModel:"+pageModel.toString());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),pageModel);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseVo save(HttpServletRequest request,Wordpad wordpad) throws Exception{
        logger.info("WordpadController|save|wordpad:"+wordpad.toString());
        if(StringUtils.isBlank(wordpad.getContent())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"内容不能为空！",null);
        }
        if(StringUtils.isBlank(wordpad.getTitle())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"标题不能为空！",null);
        }
        if(wordpad.getTitle().length()>30){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"标题只能30字符以内！",null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        WordpadDto wordpadDto = new WordpadDto();
        BeanUtils.copyProperties(wordpadDto,wordpad);
        wordpadDto.setUserId(userId);
        Integer flag = wordpadService.saveWordpad(wordpadDto);
        if(flag<0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"发布失败",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @RequestMapping(value = "/deleteWordpad",method = RequestMethod.POST)
    public ResponseVo deleteWordpad(@RequestBody Wordpad wordpad){
        logger.info("WordpadController|deleteWordpad|wordpad:"+wordpad.toString());
        if(wordpad==null || wordpad.getId()==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"删除失败",null);
        }
        wordpadService.deleteWordpad(wordpad.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/selectWordpadById")
    public ResponseVo selectWordpadById(@RequestBody Wordpad wordpad){
        logger.info("WordpadController|selectWordpadById|wordpad:"+wordpad.toString());
        if(wordpad==null || wordpad.getId()==null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        WordpadVo wordpadVo = wordpadService.selectWordpadById(wordpad.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),wordpadVo);
    }

    @PostMapping("/updateWordpad")
    public ResponseVo updateWordpad(Wordpad wordpad) throws Exception{
        logger.info("WordpadController|updateWordpad|wordpad:"+wordpad.toString());
        if(wordpad.getId() == null){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"ID不能为空",null);
        }
        if(StringUtils.isBlank(wordpad.getTitle())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"标题不能为空！",null);
        }
        if(wordpad.getTitle().length()>30){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_ERROR.getCode(),"标题只能30字符以内！",null);
        }
        if(StringUtils.isBlank(wordpad.getContent())){
            return ResponseUtil.buildVo(false, ResponseCode.PARAMETER_NULL.getCode(),"内容不能为空！",null);
        }
        WordpadDto wordpadDto = new WordpadDto();
        BeanUtils.copyProperties(wordpadDto,wordpad);
        Integer flag = wordpadService.updateWordpad(wordpadDto);
        if(flag<0){
            return ResponseUtil.buildVo(false, ResponseCode.CODE_ERROR.getCode(),"修改失败",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }
}
