package com.zhihe.zdata.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhihe.zdata.domain.Pvtable;

public interface PvtableMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Pvtable record);

    int insertSelective(Pvtable record);

    Pvtable selectByPrimaryKey(Integer id);

    int selectByCountPv(String devicetype);

    int selectByCountUv(String devicetype);

    List<Integer> selectDayPv(Map<String, Object> para);

    List<Integer> selectDaySumPv(Map<String, Object> para);

    List<Integer> selectDayUv(Map<String, Object> para);

    List<Integer> selectDaySumUv(Map<String, Object> para);

    List<String> selectDayIp(Map<String,Object> para);

    int updateByIp(Map<String,Object> para);

    int selectPv();

    int selectUv();

    List<String> select(Map<String,Object> para);

    int updateByPrimaryKeySelective(Pvtable record);

    int updateByPrimaryKey(Pvtable record);
}