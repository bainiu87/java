package com.zhihe.zdata.mapper;

import com.zhihe.zdata.domain.Shares;

public interface SharesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shares record);

    int insertSelective(Shares record);

    Shares selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shares record);

    int updateByPrimaryKeyWithBLOBs(Shares record);

    int updateByPrimaryKey(Shares record);
}