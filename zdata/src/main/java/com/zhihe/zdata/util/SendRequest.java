package com.zhihe.zdata.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SendRequest {
    public static String sendGet(String url){
        String result = "";
        BufferedReader in = null;
        try{
//            String tbUrl = "http://ip.taobao.com/"+"service/getIpInfo.php?ip=210.75.225.254"; //淘宝
////            String xlUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip; //新浪
            URL urlObject = new URL(url);
            URLConnection connection = urlObject.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "-1";
    }
}
