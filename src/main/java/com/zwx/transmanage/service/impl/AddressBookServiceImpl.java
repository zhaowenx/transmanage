package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.AddressBookDto;
import com.zwx.transmanage.domain.vo.AddressBookVo;
import com.zwx.transmanage.mapper.AddressBookMapper;
import com.zwx.transmanage.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/5.
 */
//@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("addressBookService")
public class AddressBookServiceImpl implements AddressBookService{
    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public List<AddressBookVo> selectAddressBookVoByUserId(Integer userId) {
        return addressBookMapper.selectAddressBookVoByUserId(userId);
    }

    @Override
    public Integer add(AddressBookDto addressBookDto) {
        return addressBookMapper.add(addressBookDto);
    }

    @Override
    public AddressBookVo selectAddressBookById(Integer id) {
        return addressBookMapper.selectAddressBookById(id);
    }

    @Override
    public Integer update(AddressBookDto addressBookDto) {
        return addressBookMapper.update(addressBookDto);
    }

    @Override
    public void delete(Integer id) {
        addressBookMapper.delete(id);
    }
}
