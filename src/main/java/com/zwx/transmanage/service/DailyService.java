package com.zwx.transmanage.service;

import com.zwx.transmanage.domain.dto.DailyDto;
import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.model.PageModel;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/3.
 */
public interface DailyService {
    Integer countDaily(Integer userId,String dailyDate,String isEvection);
    List<DailyVo> selectDailyListByUserId(UserVo userVo, PageModel pageModel,String dailyDate,String isEvection);
    Integer countDailyByDate(String dailyDate,Integer userId);
    Integer addDaily(DailyDto dailyDto);
    void delete(Integer id);
    Integer update(DailyDto dailyDto);
    List<DailyVo> selectDailyVoByUserId(Integer userId);
}
