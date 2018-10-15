package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.SysDict;
import com.zwx.transmanage.domain.SysDictItem;
import com.zwx.transmanage.domain.dto.SysDictDto;
import com.zwx.transmanage.domain.dto.SysDictItemDto;
import com.zwx.transmanage.domain.vo.SysDictItemVo;
import com.zwx.transmanage.domain.vo.SysDictVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseTVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.SysDictService;
import com.zwx.transmanage.util.ResponseUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/10.
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {
    private final static Logger logger = LoggerFactory.getLogger(SysDictController.class);

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("/showDict")
    public ResponseTVo showDict(Integer pageSize, Integer currentPage){
        logger.info("SysDictController|showDict|pageSize:"+pageSize+"|currentPage:"+currentPage);
        if(pageSize == null || currentPage == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),ResponseCode.FILE.getMsg(),0,null);
        }
        Integer count = sysDictService.countSysDict();
        logger.info("SysDictController|showDict|count："+count);
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有新增数据字典，您可以新增",0,null);
        }
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        logger.info("SysDictController|showDict|pageModel:"+pageModel.toString());
        List<SysDictVo> sysDictVoList = sysDictService.selectSysDict(pageModel);
        logger.info("SysDictController|showDict|sysDictVoList:"+sysDictVoList.toString());
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,sysDictVoList);
    }

    @GetMapping("/showDictItem")
    public ResponseTVo showDictItem(String dict){
        logger.info("SysDictController|showDictItem|dict:"+dict);
        if(dict == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"请点击左侧字典信息显示子项值",0,null);
        }
        List<SysDictItemVo> sysDictItemVoList = sysDictService.selectSysDictItem(dict);
        logger.info("SysDictController|showDictItem|sysDictItemVoList:"+sysDictItemVoList.toString());
        if(sysDictItemVoList.size()==0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"暂无子字典信息，您可以新增",0,null);
        }
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),sysDictItemVoList.size(),sysDictItemVoList);
    }

    @PostMapping("/selectGroupDict")
    public ResponseVo selectGroupDict(){
        logger.info("SysDictController|selectGroupDict|查询数据字典分组开始：");
        List<SysDictVo> sysDictVoList = sysDictService.selectGroupDict();
        if(sysDictVoList.size()==0){
            return ResponseUtil.buildVo(false,ResponseCode.GET_INFORMATION_NULL.getCode(),"还无数据字典分组信息，请联系管理员",null);
        }
        logger.info("SysDictController|selectGroupDict|sysDictVoList："+sysDictVoList.toString());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),sysDictVoList);
    }

    @PostMapping("addDict")
    public ResponseVo addDict(SysDict sysDict) throws Exception{
        logger.info("SysDictController|addDict|sysDict："+sysDict.toString());
        if(sysDict==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(sysDict.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典标识不能为空",null);
        }
        if(StringUtils.isBlank(sysDict.getDictName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典名称不能为空",null);
        }
        if(StringUtils.isBlank(sysDict.getGroupDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典分组不能为空",null);
        }

        Integer count = sysDictService.countDictByDict(sysDict.getDict());
        logger.info("SysDictController|addDict|count："+count);
        if(count>0){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"该字典标识已经存在",null);
        }
        SysDictDto sysDictDto = new SysDictDto();
        BeanUtils.copyProperties(sysDictDto,sysDict);
        Integer flag = sysDictService.addDict(sysDictDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增数据字典失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("addDictItem")
    public ResponseVo addDictItem(SysDictItem sysDictItem) throws Exception{
        logger.info("SysDictController|addDictItem|sysDictItem："+sysDictItem.toString());
        if(sysDictItem==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(sysDictItem.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典标识不能为空",null);
        }
        if(StringUtils.isBlank(sysDictItem.getItemKey())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"子项值不能为空",null);
        }
        if(StringUtils.isBlank(sysDictItem.getItemVal())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"子项名不能为空",null);
        }

        Integer count = sysDictService.countDictItemByItemKey(sysDictItem.getDict(),sysDictItem.getItemKey());
        logger.info("SysDictController|addDictItem|count："+count);
        if(count>0){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"子项字典值"+sysDictItem.getItemKey()+"重复",null);
        }
        SysDictItemDto sysDictItemDto = new SysDictItemDto();
        BeanUtils.copyProperties(sysDictItemDto,sysDictItem);
        Integer flag = sysDictService.addDictItem(sysDictItemDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增数据字典子项失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/updateDict")
    public ResponseVo updateDict(SysDict sysDict)throws Exception{
        logger.info("SysDictController|updateDict|sysDict："+sysDict.toString());
        if(sysDict==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(sysDict.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典标识不能为空",null);
        }
        if(StringUtils.isBlank(sysDict.getDictName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典名称不能为空",null);
        }

        SysDictDto sysDictDto = new SysDictDto();
        BeanUtils.copyProperties(sysDictDto,sysDict);
        Integer flag = sysDictService.updateDict(sysDictDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改数据字典失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/updateDictItem")
    public ResponseVo updateDictItem(SysDictItem sysDictItem)throws Exception{
        logger.info("SysDictController|updateDictItem|sysDictItem："+sysDictItem.toString());
        if(sysDictItem==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(sysDictItem.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"字典标识不能为空",null);
        }
        if(StringUtils.isBlank(sysDictItem.getItemKey())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"子项值不能为空",null);
        }
        if(StringUtils.isBlank(sysDictItem.getItemVal())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"子项名不能为空",null);
        }
        if(StringUtils.isBlank(sysDictItem.getOldItemKey())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"历史子项值不能为空",null);
        }

        sysDictService.deleteDictItem(sysDictItem.getDict(),sysDictItem.getOldItemKey());

        Integer count = sysDictService.countDictItemByItemKey(sysDictItem.getDict(),sysDictItem.getItemKey());
        logger.info("SysDictController|updateDictItem|count："+count);
        if(count>0){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),"子项字典值"+sysDictItem.getItemKey()+"重复",null);
        }
        SysDictItemDto sysDictItemDto = new SysDictItemDto();
        BeanUtils.copyProperties(sysDictItemDto,sysDictItem);
        Integer flag = sysDictService.addDictItem(sysDictItemDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改数据字典失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/deleteDict")
    public ResponseVo deleteDict(@RequestBody SysDict sysDict){
        logger.info("SysDictController|deleteDict|sysDict："+sysDict.toString());
        if(sysDict==null || StringUtils.isBlank(sysDict.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        sysDictService.deleteDict(sysDict.getDict());
        sysDictService.deleteDictItemByDict(sysDict.getDict());
        Integer count = sysDictService.countDictByDict(sysDict.getDict());
        if(count>0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/deleteDictItem")
    public ResponseVo deleteDictItem(@RequestBody SysDictItem sysDictItem){
        logger.info("SysDictController|deleteDictItem|sysDict："+sysDictItem.toString());
        if(sysDictItem==null || StringUtils.isBlank(sysDictItem.getDict()) || StringUtils.isBlank(sysDictItem.getItemKey())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        sysDictService.deleteDictItem(sysDictItem.getDict(),sysDictItem.getItemKey());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/selectDictItemByDict")
    public ResponseVo selectDictItemByDict(@RequestBody SysDictItem sysDictItem){
        logger.info("SysDictController|selectDictItemByDict|sysDictItem："+sysDictItem.toString());
        if(sysDictItem ==null||StringUtils.isBlank(sysDictItem.getDict())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        List<SysDictItemVo> sysDictItemVoList = sysDictService.selectSysDictItem(sysDictItem.getDict());
        logger.info("SysDictController|selectDictItemByDict|sysDictItemVoList："+sysDictItemVoList.toString());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),sysDictItemVoList);
    }
}
