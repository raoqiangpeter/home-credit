package com.raoqiang.homecredit.dao;

import com.raoqiang.homecredit.entry.HcStream;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HcStreamDao {

    int insertStream(HcStream hcStream);

    int updateStream(HcStream hcStream);

}
