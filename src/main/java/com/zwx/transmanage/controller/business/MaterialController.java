package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.Material;
import com.zwx.transmanage.domain.dto.MaterialDto;
import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.domain.vo.MaterialVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.model.ResponseTVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.MaterialService;
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
 * Created by zhaowenx on 2018/9/4.
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    private final static Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MaterialService materialService;

    @GetMapping("/show")
    public ResponseTVo show(HttpServletRequest request, Integer pageSize, Integer currentPage,String description,String type){
        logger.info("MaterialController|show|pageSize:"+pageSize+"|currentPage:"+currentPage+"|description:"+description+"|type:"+type);
        if(pageSize == null || currentPage == null){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),ResponseCode.FILE.getMsg(),0,null);
        }
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        logger.info("MaterialController|show|userId:"+userVo.getId());
        Integer count = materialService.countMaterial(userVo.getId(),description,type);
        logger.info("MaterialController|show|count："+count);
        if(count == 0){
            return  ResponseUtil.buildTVo(ResponseCode.FILE.getCode(),"还没有新增资料，您可以新增",0,null);
        }
        logger.info("MaterialController|show|count："+count);
        PageModel pageModel=new PageModel(currentPage,pageSize,count,null);
        List<MaterialVo> materialVoList = materialService.selectMaterialListByUserId(userVo,pageModel,description,type);
        logger.info("MaterialController|show|materialVoList:"+materialVoList.toString());
        logger.info("MaterialController|show|pageModel:"+pageModel.toString());
        return  ResponseUtil.buildTVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,materialVoList);
    }

    @PostMapping("/add")
    public ResponseVo add(HttpServletRequest request, Material material) throws Exception{
        logger.info("MaterialController|add|material:"+material.toString());
        if(material == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(material.getDescription())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"描述不能为空",null);
        }
        if(StringUtils.isBlank(material.getUrl())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"路径不能为空",null);
        }
        if(StringUtils.isBlank(material.getType())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"类型不能为空",null);
        }
        Integer userId = UserUtil.getUserId(request,redisUtil);
        MaterialDto materialDto= new MaterialDto();
        BeanUtils.copyProperties(materialDto,material);
        materialDto.setUserId(userId);
        Integer flag = materialService.addMaterial(materialDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"新增失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody Material material){
        logger.info("MaterialController|delete|material:"+material.toString());
        if(material == null || material.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        materialService.delete(material.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/update")
    public ResponseVo update(Material material) throws Exception{
        logger.info("MaterialController|update|material:"+material.toString());
        if(material == null || material.getId() ==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
        if(StringUtils.isBlank(material.getDescription())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"描述不能为空",null);
        }
        if(StringUtils.isBlank(material.getUrl())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"路径不能为空",null);
        }
        if(StringUtils.isBlank(material.getType())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"类型不能为空",null);
        }
        MaterialDto materialDto= new MaterialDto();
        BeanUtils.copyProperties(materialDto,material);
        Integer flag = materialService.update(materialDto);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"修改失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }
}
