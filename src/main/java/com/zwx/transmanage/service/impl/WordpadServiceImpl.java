package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.WordpadDto;
import com.zwx.transmanage.domain.vo.WordpadVo;
import com.zwx.transmanage.mapper.WordpadMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.WordpadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/31.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("wordpadService")
public class WordpadServiceImpl implements WordpadService {

    @Autowired
    private WordpadMapper wordpadMapper;

    @Override
    public Integer saveWordpad(WordpadDto wordpadDto) {
        return wordpadMapper.saveWordpad(wordpadDto);
    }

    @Override
    public List<WordpadVo> selectWordpadByUserId(Integer userId,Integer startRow,Integer endRow) {
        return wordpadMapper.selectWordpadByUserId(userId,startRow,endRow);
    }

    @Override
    public Integer countWordpadList(Integer userId) {
        return wordpadMapper.countWordpadList(userId);
    }

    @Override
    public void deleteWordpad(Integer id) {
        wordpadMapper.deleteWordpad(id);
    }

    @Override
    public WordpadVo selectWordpadById(Integer id) {
        return wordpadMapper.selectWordpadById(id);
    }

    @Override
    public Integer updateWordpad(WordpadDto wordpadDto) {
        return wordpadMapper.updateWordpad(wordpadDto);
    }
}
