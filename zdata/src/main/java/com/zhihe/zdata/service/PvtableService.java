package com.zhihe.zdata.service;

import java.util.List;

import com.zhihe.zdata.domain.Pvtable;

/**
 * Created by Administrator on 2017/6/9.
 */
public interface PvtableService {
    /**
     * 获取
     * @param id
     * @return
     */
    Pvtable getPvtableById(Integer id);
//    添加数据到数据库
    int insertData(Pvtable record);
//    得到总Pv
    int getSumPv(String devicetype);
//    得到总Uv
    int getSumUv(String devicetype);
//    得到天的PV
    List<Integer> getDayPv(String devicetype, String beginTime, String overTime) throws Exception;
//    得到天的UV
    List<Integer> getDayUv(String devicetype, String beginTime, String overTime) throws Exception;
//    查询当天的所有ip地址
    List<String> getDayIp() throws Exception;
}
