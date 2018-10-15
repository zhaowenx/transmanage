package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.WordpadDto;
import com.zwx.transmanage.domain.vo.WordpadVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/8/31.
 */
@Mapper
public interface WordpadMapper {
    Integer saveWordpad(WordpadDto wordpadDto);
    List<WordpadVo> selectWordpadByUserId(@Param("userId") Integer userId,@Param("startRow") Integer startRow,@Param("endRow") Integer endRow);
    Integer countWordpadList(@Param("userId") Integer userId);
    void deleteWordpad(Integer id);
    WordpadVo selectWordpadById(Integer id);
    Integer updateWordpad(WordpadDto wordpadDto);
}
