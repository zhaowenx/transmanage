package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.SysDictDto;
import com.zwx.transmanage.domain.dto.SysDictItemDto;
import com.zwx.transmanage.domain.vo.SysDictItemVo;
import com.zwx.transmanage.domain.vo.SysDictVo;
import com.zwx.transmanage.mapper.SysDictMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/10/10.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService{

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDictVo> selectSysDict(PageModel pageModel) {
        return sysDictMapper.selectSysDict(pageModel);
    }

    @Override
    public List<SysDictItemVo> selectSysDictItem(String dict) {
        return sysDictMapper.selectSysDictItem(dict);
    }

    @Override
    public Integer countSysDict() {
        return sysDictMapper.countSysDict();
    }

    @Override
    public List<SysDictVo> selectGroupDict() {
        return sysDictMapper.selectGroupDict();
    }

    @Override
    public Integer countDictByDict(String dict) {
        return sysDictMapper.countDictByDict(dict);
    }

    @Override
    public Integer addDict(SysDictDto sysDictDto) {
        return sysDictMapper.addDict(sysDictDto);
    }

    @Override
    public Integer updateDict(SysDictDto sysDictDto) {
        return sysDictMapper.updateDict(sysDictDto);
    }

    @Override
    public void deleteDict(String dict) {
        sysDictMapper.deleteDict(dict);
    }

    @Override
    public Integer countDictItemByItemKey(String dict, String itemKey) {
        return sysDictMapper.countDictItemByItemKey(dict, itemKey);
    }

    @Override
    public Integer addDictItem(SysDictItemDto sysDictItemDto) {
        return sysDictMapper.addDictItem(sysDictItemDto);
    }

    @Override
    public void deleteDictItem(String dict, String itemKey) {
        sysDictMapper.deleteDictItem(dict, itemKey);
    }

    @Override
    public void deleteDictItemByDict(String dict) {
        sysDictMapper.deleteDictItemByDict(dict);
    }

    @Override
    public List<SysDictVo> selectSysDictVo() {
        return sysDictMapper.selectSysDictVo();
    }

    @Override
    public String selectItemValByKey(String dict, String itmKey) {
        return sysDictMapper.selectItemValByKey(dict, itmKey);
    }
}
