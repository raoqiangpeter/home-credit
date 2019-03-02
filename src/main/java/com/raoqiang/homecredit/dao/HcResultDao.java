package com.raoqiang.homecredit.dao;

import com.raoqiang.homecredit.entry.HcResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HcResultDao {

    int insertResult(HcResult hcResult);

}
