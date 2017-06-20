package com.zhihe.zdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.prism.shader.DrawCircle_Color_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihe.zdata.domain.Pvtable;
import com.zhihe.zdata.mapper.PvtableMapper;
import com.zhihe.zdata.service.PvtableService;

/**
 * Created by Administrator on 2017/6/9.
 */
@Service
public class PvtableServiceImpl implements PvtableService {

    @Autowired
    private PvtableMapper pvtableMapper;

    public Pvtable getPvtableById(Integer id) {
        return pvtableMapper.selectByPrimaryKey(id);
    }
//    添加数据到数据库
    public int insertData(Pvtable record){
        return pvtableMapper.insert(record);
    }
//    得到总的PV
    public int getSumPv(String devicetype){
        if (devicetype.equals("SUM")){
            return pvtableMapper.selectPv();
        }else if(devicetype.equals("PC") || devicetype.equals("IOS") || devicetype.equals("ANDROID")){
            return pvtableMapper.selectByCountPv(devicetype);
        }else{
            return -1;
        }
    }
//    得到总的Uv
    public int getSumUv(String devicetype){
        if (devicetype.equals("SUM")){
            return pvtableMapper.selectUv();
        }else if(devicetype.equals("PC") || devicetype.equals("IOS") || devicetype.equals("ANDROID")){
            return pvtableMapper.selectByCountUv(devicetype);
        }else{
            return -1;
        }
    }
//    得到天的PV
    public List<Integer> getDayPv(String devicetype, String beginTime, String overTime) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = sdf.parse(beginTime);
        Date over = sdf.parse(overTime);
        if (devicetype.equals("SUM")){
            Map<String,Object> para = new HashMap<String, Object>();
            para.put("beginTime",begin);
            para.put("overTime",over);
            return pvtableMapper.selectDaySumPv(para);
        }else if(devicetype.equals("PC") || devicetype.equals("IOS") || devicetype.equals("ANDROID")){
            Map<String,Object> para = new HashMap<String, Object>();
            para.put("devicetype",devicetype);
            para.put("beginTime",begin);
            para.put("overTime",over);
            return pvtableMapper.selectDayPv(para);
        }else{
            return null;
        }
    }
//    得到天的UV
    public List<Integer> getDayUv(String devicetype,String beginTime,String overTime) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = sdf.parse(beginTime);
        Date over = sdf.parse(overTime);
        if (devicetype.equals("SUM")){
            Map<String,Object> para = new HashMap<String, Object>();
            para.put("beginTime",begin);
            para.put("overTime",over);
            return pvtableMapper.selectDaySumUv(para);
        }else if(devicetype.equals("PC") || devicetype.equals("IOS") || devicetype.equals("ANDROID")){
            Map<String,Object> para = new HashMap<String, Object>();
            para.put("devicetype",devicetype);
            para.put("beginTime",begin);
            para.put("overTime",over);
            return pvtableMapper.selectDayUv(para);
        }else{
            return null;
        }
    }

//    得到一天的所有IP
    public List<String> getDayIp() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date overTime = sdf.parse(sdf.format(new Date()));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(overTime);
        calendar.add(calendar.DATE,-1);
        Date beginTime = calendar.getTime();
        Map<String,Object> para = new HashMap<String, Object>();
        para.put("beginTime",beginTime);
        para.put("overTime",overTime);
        return pvtableMapper.selectDayIp(para);
    }
}
