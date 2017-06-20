package com.zhihe.zdata.controller;

import com.github.pagehelper.PageHelper;
import com.zhihe.zdata.domain.Pvtable;
import com.zhihe.zdata.mapper.PvtableMapper;
import com.zhihe.zdata.service.PvtableService;
import com.zhihe.zdata.service.SendRequestService;
import com.zhihe.zdata.service.impl.PvtableServiceImpl;
import com.zhihe.zdata.util.SendRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16
 */
@RestController
public class GetIpInfoController {

    @Autowired
    private SendRequestService sendRequestService;
    @Scheduled(cron="0 0 1 * * ?")
    public void AI(){
        sendRequestService.sendRequest();
    }
//    @RequestMapping(value = "/gitip")
//    public List<String> getIp(){
//        try {
//            return pvtableService.getDayIp();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//    @RequestMapping(value = "/info")
//    public Integer getInfo(){
//        return sendRequestService.send("210.75.225.254");
//    }
//    @RequestMapping(value = "/page/{num}")
//    public List<String> getPage(@PathVariable("num") String page){
//        try{
//            return sendRequestService.findIp(Integer.parseInt(page));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

}
