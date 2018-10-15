package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.Wordpad;
import com.zwx.transmanage.domain.dto.WordpadDto;
import com.zwx.transmanage.domain.vo.WordpadVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/31.
 */
public interface WordpadService {
    Integer saveWordpad(WordpadDto wordpadDto);
    List<WordpadVo> selectWordpadByUserId(Integer userId,Integer startRow,Integer endRow);
    Integer countWordpadList(Integer userId);
    void deleteWordpad(Integer id);
    WordpadVo selectWordpadById(Integer id);
    Integer updateWordpad(WordpadDto wordpadDto);
}
