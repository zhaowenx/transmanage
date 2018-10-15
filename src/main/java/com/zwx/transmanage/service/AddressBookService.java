package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.AddressBookDto;
import com.zwx.transmanage.domain.vo.AddressBookVo;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/5.
 */
public interface AddressBookService {
    List<AddressBookVo> selectAddressBookVoByUserId(Integer userId);
    Integer add(AddressBookDto addressBookDto);
    AddressBookVo selectAddressBookById(Integer id);
    Integer update(AddressBookDto addressBookDto);
    void delete(Integer id);
}
