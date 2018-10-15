package com.zwx.transmanage.service.impl;

import com.zwx.transmanage.domain.dto.DailyDto;
import com.zwx.transmanage.domain.vo.DailyVo;
import com.zwx.transmanage.domain.vo.UserVo;
import com.zwx.transmanage.mapper.DailyMapper;
import com.zwx.transmanage.model.PageModel;
import com.zwx.transmanage.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaowenx on 2018/9/3.
 */
@ComponentScan({"com.zwx.transmanage.mapper"})
@Service("dailyService")
public class DailyServiceImpl implements DailyService{

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    public Integer countDaily(Integer userId) {
        return dailyMapper.countDaily(userId);
    }

    @Override
    public List<DailyVo> selectDailyListByUserId(UserVo userVo, PageModel pageModel) {
        List<DailyVo> dailyVoList = dailyMapper.selectDailyListByUserId(userVo.getId(),pageModel);
        for(DailyVo dailyVo:dailyVoList){
            dailyVo.setUserName(userVo.getUserName());
        }
        return dailyVoList;
    }

    @Override
    public Integer countDailyByDate(String dailyDate, Integer userId) {
        return dailyMapper.countDailyByDate(dailyDate,userId);
    }

    @Override
    public Integer addDaily(DailyDto dailyDto) {
        return dailyMapper.addDaily(dailyDto);
    }

    @Override
    public void delete(Integer id) {
        dailyMapper.delete(id);
    }

    @Override
    public Integer update(DailyDto dailyDto) {
        return dailyMapper.update(dailyDto);
    }

    @Override
    public List<DailyVo> selectDailyVoByUserId(Integer userId) {
        return dailyMapper.selectDailyVoByUserId(userId);
    }
}
