package com.zhihe.zdata.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhihe.zdata.domain.Shares;
import com.zhihe.zdata.service.PvtableService;
import com.zhihe.zdata.service.SharesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhihe.zdata.domain.Pvtable;
import com.zhihe.zdata.util.RequestParserUtils;

/**
 * Created by dutao on 2016/12/14.
 */
@RestController
public class StatisticsController {

    @Autowired
    private PvtableService pvtableService;

    @Autowired
    private SharesService sharesService;

    private Logger logger = LogManager.getLogger("statistics");

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView redirectRequest() throws IOException {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping(value = "/stat.gif", method = RequestMethod.GET)
    public void statistic(HttpServletRequest request) throws Exception{
        String webid = request.getParameter("webid");//网站ID
        String cookieid = request.getParameter("cookieid");//COOKIE-ID
        String ip = RequestParserUtils.getIpAddr(request);
        String phone = request.getParameter("phone");//FROM
        String devicetype = RequestParserUtils.getDeviceType(request);

        String domain = request.getParameter("domain");//网站域名
        String url = request.getParameter("url");//网站地址
        String title = request.getParameter("title");//网页标题
        String referrer = request.getParameter("referrer");//FROM
        String uri = request.getRequestURI();
        String res = request.getParameter("res");//屏幕分辨率
        String colorDepth = request.getParameter("cd");//颜色
        String lang = request.getParameter("lang");//语言
        String brower = request.getParameter("brower");//语言
        Map<String, String> requestHeaders = RequestParserUtils.getRequestHeaders(request);
        String agent = requestHeaders.get("user-agent");

//        String country = request.getParameter("country");//国家
//        String province = request.getParameter("province");//省份
//        String city = request.getParameter("city");//城市
//        String isp = request.getParameter("isp");//运营商

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curTime = sdf.parse(sdf.format(new Date()));
        Pvtable pvtable = new Pvtable(webid, cookieid, ip, phone, devicetype, url, title,
                referrer, res, colorDepth, lang, brower, agent,curTime,domain,uri);

        pvtableService.insertData(pvtable);
        //第二个参数为标识  1.PV  2.SHARE
//        logger.info(webid + "^" + "VIEW" + "^" + "channel" + "^" + cookieid + "^" + phone + "^" + ip + "^" + devicetype + "^" + url + "^" + title + "^" + referrer + "^" + res + "^" + colorDepth  + "^" + lang + "^" + brower  + "^" + agent);
    }

    /**
     * 分享统计
     * @param request
     */
    @RequestMapping(value = "/share.gif", method = RequestMethod.GET)
    public void share(HttpServletRequest request) throws Exception{
        String webid = request.getParameter("webid");//网站ID
        String cookieid = request.getParameter("cookieid");//COOKIE-ID
        String channel = request.getParameter("channel");//分享渠道
        String url = request.getParameter("url");//COOKIE-ID
        String phone = request.getParameter("phone");//FROM

        String ip = RequestParserUtils.getIpAddr(request);
        String devicetype = RequestParserUtils.getDeviceType(request);

        String domain = request.getParameter("domain");//网站域名
        String referrer = request.getParameter("referrer");//FROM
        String title = request.getParameter("title");//网页标题
        String uri = request.getRequestURI();
        String res = request.getParameter("res");//屏幕分辨率
        String colorDepth = request.getParameter("cd");//颜色
        String lang = request.getParameter("lang");//语言
        String brower = request.getParameter("brower");//语言
        Map<String, String> requestHeaders = RequestParserUtils.getRequestHeaders(request);
        String agent = requestHeaders.get("user-agent");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curTime = sdf.parse(sdf.format(new Date()));

        String country = request.getParameter("country");//国家
        String province = request.getParameter("province");//省份
        String city = request.getParameter("city");//城市
        String isp = request.getParameter("isp");//运营商

        Shares shares = new Shares(webid,cookieid,channel,phone,ip,devicetype,res,colorDepth,
                lang,brower,url, domain, referrer,title,uri,agent,curTime,country,province,city,isp);
        sharesService.insertDate(shares);
//        logger.info(webid + "^" + "SHARE" + "^" + channel + "^" + cookieid + "^" + phone + "^" + ip + "^" + devicetype + "^" + url + "^" + title  + "^" + referrer + "^" + res + "^" + colorDepth  + "^" + lang + "^" + brower  + "^" + agent);


    }
//    获取总的PV
    @RequestMapping("/sumPv/{devicetype}")
    public int sumPv_mold(@PathVariable("devicetype") String devicetype) throws IOException{
        return pvtableService.getSumPv(devicetype);
    }
//    获取总的UV
    @RequestMapping("/sumUv/{devicetype}")
    public int sumUv_mold(@PathVariable("devicetype") String devicetype) throws  IOException{
        return pvtableService.getSumUv(devicetype);
    }
//    获取每一天的PV
    @RequestMapping("/dayPv/{devicetype}/{beginTime}/{overTime}")
    public List<Integer> dayPv(@PathVariable("devicetype") String devicetype, @PathVariable("beginTime") String beginTime,
                      @PathVariable("overTime") String overTime){
        try {
            return pvtableService.getDayPv(devicetype,beginTime,overTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    获取每一天的UV
    @RequestMapping("/dayUv/{devicetype}/{beginTime}/{overTime}")
    public List<Integer> dayUv(@PathVariable("devicetype") String devicetype, @PathVariable("beginTime") String beginTime,
                     @PathVariable("overTime") String overTime){
        try{
            return pvtableService.getDayUv(devicetype,beginTime,overTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
