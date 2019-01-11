package com.zwx.transmanage.mapper;

import com.zwx.transmanage.domain.dto.DailyDto;
import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.model.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/3.
 */
@Mapper
public interface DailyMapper {
    Integer countDaily(@Param("userId") Integer userId,@Param("dailyDate") String dailyDate,@Param("isEvection") String isEvection);
    List<DailyVo> selectDailyListByUserId(@Param("userId") Integer userId, @Param("pageModel") PageModel pageModel,
                                          @Param("dailyDate") String dailyDate,@Param("isEvection") String isEvection);
    Integer countDailyByDate(@Param("dailyDate") String dailyDate,@Param("userId") Integer userId);
    Integer addDaily(DailyDto dailyDto);
    void delete(Integer id);
    Integer update(DailyDto dailyDto);
    List<DailyVo> selectDailyVoByUserId(@Param("userId") Integer userId);
}
