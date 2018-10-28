package com.zwx.transmanage.controller.business;

import com.zwx.transmanage.commen.constant.ResponseCode;
import com.zwx.transmanage.domain.AddressBook;
import com.zwx.transmanage.domain.dto.AddressBookDto;
import com.zwx.transmanage.domain.vo.AddressBookVo;
import com.zwx.transmanage.domain.vo.SysDictItemVo;
import com.zwx.transmanage.domain.vo.SysDictVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.ResponseVo;
import com.zwx.transmanage.service.AddressBookService;
import com.zwx.transmanage.service.SysDictService;
import com.zwx.transmanage.util.RedisUtil;
import com.zwx.transmanage.util.ResponseUtil;
import com.zwx.transmanage.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhaowenx on 2018/9/5.
 */
@Controller
@RequestMapping("/address")
public class AddressBookController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
    /**
     * 正则表达式：验证汉字
     */
//    public static final String REGEX_CHINESE = "^[\\u4e00-\\u9fa5]";
    /**
     * 大于0的正整数
     */
    public static final String BIG_ZERO_NUMBER = "[1-9][0-9]{4,14}";
    /**
     * 8位的正整数
     */
    public static final String BIRTHDAY = "\\b\\d{4}((?:0[13578]|1[02])(?:0[1-9]|[12]\\d|3[01])|02(?:0[1-9]|[12]\\d)|(?:0[469]|11)(?:0[1-9]|[12]\\d|30))\\b";

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private SysDictService sysDictService;

    @RequestMapping(value = "/show")
    public String show(HttpServletRequest request) throws Exception{
        logger.info("AddressBookController|show|展示通讯录开始");
        UserVo userVo = UserUtil.getLoginUser(request,redisUtil);
        if(userVo==null || userVo.getId()==null){
            return "logout";
        }
        List<AddressBookVo> addressBookVoList = addressBookService.selectAddressBookVoByUserId(userVo.getId());
        List<AddressBookVo> addressBookVos = new ArrayList<>();
        for(AddressBookVo addressBookVo:addressBookVoList){
            addressBookVo.setProfession(sysDictService.selectItemValByKey("profession",addressBookVo.getProfession()));
            addressBookVo.setType(sysDictService.selectItemValByKey("type",addressBookVo.getType()));
            addressBookVos.add(addressBookVo);
        }
        request.setAttribute("addressBookVoList",addressBookVos);
        request.setAttribute("count",addressBookVos.size());
        logger.info("AddressBookController|show|count:"+addressBookVos.size());
        return "address-book";
    }

    @PostMapping("add")
    @ResponseBody
    public ResponseVo add(HttpServletRequest request,AddressBook addressBook) throws Exception{
        logger.info("AddressBookController|add|addressBook："+addressBook.toString());
        if(addressBook==null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(addressBook.getChineseName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"中文名不能为空",null);
        }
//        if(!check(addressBook.getChineseName(),REGEX_CHINESE)){
//            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"中文名只能是中文",null);
//        }
        if(StringUtils.isBlank(addressBook.getPhone())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"手机号码不能为空",null);
        }
        if(!check(addressBook.getPhone(),REGEX_MOBILE)){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"请输入正确的手机号码",null);
        }
        if(StringUtils.isNotBlank(addressBook.getQqNumber())){
            if(!check(addressBook.getQqNumber(),BIG_ZERO_NUMBER)){
                return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"请输入正确的QQ号码",null);
            }
        }
        if(StringUtils.isBlank(addressBook.getBirthday())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"出生日期不能为空",null);
        }
        if(!check(addressBook.getBirthday(),BIRTHDAY)){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"出生日期的格式为yyyyMMdd",null);
        }
        if(StringUtils.isBlank(addressBook.getProfession())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"职业不能为空",null);
        }
        if(StringUtils.isBlank(addressBook.getType())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"所属分类不能为空",null);
        }
        if(StringUtils.isBlank(addressBook.getSex())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"性别不能为空",null);
        }

        Integer userId = UserUtil.getUserId(request,redisUtil);
        addressBook.setUserId(userId);
        AddressBookDto addressBookDto = new AddressBookDto();
        BeanUtils.copyProperties(addressBookDto,addressBook);
        logger.info("AddressBookController|add|addressBookDto："+addressBookDto.toString());
        Integer flag = addressBookService.add(addressBookDto);
        logger.info("AddressBookController|add|flag："+flag);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"通讯录添加失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/selectAddressBookById")
    @ResponseBody
    public ResponseVo selectAddressBookById(@RequestBody AddressBook addressBook){
        logger.info("AddressBookController|selectAddressBookById|addressBook："+addressBook.toString());
        if(addressBook==null||addressBook.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),ResponseCode.CODE_ERROR.getMsg(),null);
        }
        AddressBookVo addressBookVo = addressBookService.selectAddressBookById(addressBook.getId());
        logger.info("AddressBookController|selectAddressBookById|addressBookVo："+addressBookVo.toString());
        if (addressBookVo==null){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"系统错误",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),addressBookVo);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVo update(AddressBook addressBook) throws Exception{
        logger.info("AddressBookController|update|addressBook："+addressBook.toString());
        if(addressBook == null || addressBook.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        if(StringUtils.isBlank(addressBook.getChineseName())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"中文名不能为空",null);
        }
//        if(!check(addressBook.getChineseName(),REGEX_CHINESE)){
//            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"中文名只能是中文",null);
//        }
        if(StringUtils.isBlank(addressBook.getPhone())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"手机号码不能为空",null);
        }
        if(!check(addressBook.getPhone(),REGEX_MOBILE)){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"请输入正确的手机号码",null);
        }
        if(StringUtils.isNotBlank(addressBook.getQqNumber())){
            if(!check(addressBook.getQqNumber(),BIG_ZERO_NUMBER)){
                return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"请输入正确的QQ号码",null);
            }
        }
        if(StringUtils.isBlank(addressBook.getBirthday())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"出生日期不能为空",null);
        }
        if(!check(addressBook.getBirthday(),BIRTHDAY)){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"出生日期的格式为yyyyMMdd",null);
        }
        if(StringUtils.isBlank(addressBook.getProfession())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"职业不能为空",null);
        }
        if(StringUtils.isBlank(addressBook.getType())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"所属分类不能为空",null);
        }
        if(StringUtils.isBlank(addressBook.getSex())){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),"性别不能为空",null);
        }
        AddressBookDto addressBookDto = new AddressBookDto();
        BeanUtils.copyProperties(addressBookDto,addressBook);
        logger.info("AddressBookController|update|addressBookDto："+addressBookDto.toString());
        Integer flag = addressBookService.update(addressBookDto);
        logger.info("AddressBookController|update|flag："+flag);
        if(flag<0){
            return ResponseUtil.buildVo(false,ResponseCode.CODE_ERROR.getCode(),"通讯录添加失败，请刷新页面重新操作",null);
        }
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(@RequestBody AddressBook addressBook){
        logger.info("AddressBookController|delete|addressBook："+addressBook.toString());
        if(addressBook == null || addressBook.getId() == null){
            return ResponseUtil.buildVo(false,ResponseCode.PARAMETER_NULL.getCode(),ResponseCode.PARAMETER_NULL.getMsg(),null);
        }
        addressBookService.delete(addressBook.getId());
        return ResponseUtil.buildVo(true,ResponseCode.CODE_SUCCESS.getCode(),ResponseCode.CODE_SUCCESS.getMsg(),null);
    }

    public static boolean check(String parameter,String regular) {
        return Pattern.matches(regular, parameter);
    }
}
