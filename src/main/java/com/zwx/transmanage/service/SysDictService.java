package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.SysDictDto;
import com.zwx.transmanage.domain.dto.SysDictItemDto;
import com.zwx.transmanage.domain.vo.SysDictItemVo;
import com.zwx.transmanage.domain.vo.SysDictVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/10.
 */
public interface SysDictService {
    List<SysDictVo> selectSysDict(PageModel pageModel);
    Integer countSysDict();
    List<SysDictItemVo> selectSysDictItem(String dict);
    List<SysDictVo> selectGroupDict();
    Integer countDictByDict(String dict);
    Integer countDictItemByItemKey(String dict,String itemKey);
    Integer addDict(SysDictDto sysDictDto);
    Integer updateDict(SysDictDto sysDictDto);
    void deleteDict(String dict);
    void deleteDictItemByDict(String dict);
    Integer addDictItem(SysDictItemDto sysDictItemDto);
    void deleteDictItem(String dict,String itemKey);

    /**
     * 初始化执行
     */
    List<SysDictVo> selectSysDictVo();

    String selectItemValByKey(String dict,String itmKey);

}
