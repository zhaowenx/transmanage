package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.AddressBookDto;
import com.zwx.transmanage.domain.vo.AddressBookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/5.
 */
@Mapper
public interface AddressBookMapper {
    List<AddressBookVo> selectAddressBookVoByUserId(@Param("userId") Integer userId);
    Integer add(AddressBookDto addressBookDto);
    AddressBookVo selectAddressBookById(@Param("id") Integer id);
    Integer update(AddressBookDto addressBookDto);
    void delete(@Param("id") Integer id);
}
