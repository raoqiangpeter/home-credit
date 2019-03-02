package com.raoqiang.homecredit.dao;

import com.raoqiang.homecredit.entry.HcResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HcResultDaoTest {

    @Autowired
    private HcResultDao hcResultDao;

    @Test
    public void insertResult(){
        HcResult hcResult = new HcResult();
        hcResult.setHcId("1236487236482935");
        hcResult.setHcSkId("100700");
        hcResult.setHcScore(new BigDecimal(0.3728945983795));
        hcResultDao.insertResult(hcResult);
    }

}