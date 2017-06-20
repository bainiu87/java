package com.zhihe.zdata.service;

import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface SendRequestService {
    public Integer send(String ip);
    public List<String> findIp(Integer page) throws Exception;
    public void sendRequest();
}
