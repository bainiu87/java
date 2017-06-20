package com.zhihe.zdata.service.impl;


import com.zhihe.zdata.domain.Shares;
import com.zhihe.zdata.mapper.SharesMapper;
import com.zhihe.zdata.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SharesServiceImpl implements SharesService{
        @Autowired
        private SharesMapper sharesMapper;
        public int insertDate(Shares record) {
                return sharesMapper.insert(record);
        }
}