package com.zhihe.zdata.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhihe.zdata.mapper.PvtableMapper;
import com.zhihe.zdata.service.SendRequestService;
import com.zhihe.zdata.util.JsonUtils;
import com.zhihe.zdata.util.SendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class SendRequestServiceImpl implements SendRequestService{
    @Autowired
    private PvtableMapper pvtableMapper;

    public List<String> findIp(Integer page) throws Exception{
        PageHelper.startPage(page,10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date overTime = sdf.parse(sdf.format(new Date()));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(overTime);
        calendar.add(calendar.DATE,-1);
        Date beginTime = calendar.getTime();
        Map<String,Object> para = new HashMap<String, Object>();
        para.put("beginTime",beginTime);
        para.put("overTime",overTime);
        return pvtableMapper.select(para);
    }

    public Integer send(String ip) {
        String tbUrl = "http://ip.taobao.com/"+"service/getIpInfo.php?ip="+ip; //淘宝
        String xlUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip; //新浪
        String response = SendRequest.sendGet(tbUrl);
        Map MapRe  = JsonUtils.json2Map(response);
        if(!MapRe.get("code").equals("1")){
            return pvtableMapper.updateByIp((Map)MapRe.get("data"));
        }
        return -1;
    }

    public void sendRequest(){
        boolean state = true;
        while(state){
            Integer page = 1;
            try {
                Thread.sleep(1000);
                List<String> listIp = this.findIp(page);
                if (listIp != null || !listIp.isEmpty()){
                    for(String ip:listIp){
                        this.send(ip);
                    }
                }else{
                    state = false;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            page ++;
        }
    }

}
