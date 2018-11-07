package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.SysDictDto;
import com.zwx.transmanage.domain.dto.SysDictItemDto;
import com.zwx.transmanage.domain.vo.SysDictItemVo;
import com.zwx.transmanage.domain.vo.SysDictVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/10.
 */
@Mapper
public interface SysDictMapper {
    Integer countSysDict();
    List<SysDictVo> selectSysDict(@Param("pageModel") PageModel pageModel);
    List<SysDictItemVo> selectSysDictItem(@Param("dict") String dict);
    List<SysDictVo> selectGroupDict();
    Integer countDictByDict(@Param("dict") String dict);
    Integer addDict(SysDictDto sysDictDto);
    Integer updateDict(SysDictDto sysDictDto);
    void deleteDict(String dict);
    void deleteDictItemByDict(String dict);
    Integer countDictItemByItemKey(@Param("dict") String dict,@Param("itemKey") String itemKey);
    Integer addDictItem(SysDictItemDto sysDictItemDto);
    void deleteDictItem(@Param("dict") String dict,@Param("itemKey") String itemKey);

    List<SysDictVo> selectSysDictVo();
    String selectItemValByKey(@Param("dict") String dict,@Param("itemKey") String itemKey);
}
