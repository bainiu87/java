package com.zhihe.zdata.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.Manufacturer;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * Created by dutao on 2016/9/19.
 */
public class RequestParserUtils {

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 解析request获取头部信息
     * @param request
     * @return
     */
    public static Map<String,String> getRequestHeaders(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static String getDeviceType(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        DeviceType deviceType = operatingSystem.getDeviceType();
        String type = "PC";
        if(deviceType == DeviceType.MOBILE){//移动设备
            Manufacturer manufacturer = operatingSystem.getManufacturer();
            if(manufacturer == Manufacturer.APPLE){
                type = "IOS";
            }else{
                type = "ANDROID";
            }
        }
        return type;
    }

    /// 根据 Agent 判断是否是智能手机
    ///</summary>
    ///<returns></returns>
    public static boolean CheckAgent(String agent)
    {
        boolean flag = false;

        String[] keywords = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };

        //排除 Windows 桌面系统
        if (!agent.contains("Windows NT") || (agent.contains("Windows NT") && agent.contains("compatible; MSIE 9.0;")))
        {
            //排除 苹果桌面系统
            if (!agent.contains("Windows NT") && !agent.contains("Macintosh"))
            {
                for (String item : keywords)
                {
                    if (agent.contains(item))
                    {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

}
