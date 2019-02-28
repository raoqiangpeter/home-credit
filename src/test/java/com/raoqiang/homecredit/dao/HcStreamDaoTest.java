package com.raoqiang.homecredit.dao;

import com.raoqiang.homecredit.entry.HcStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HcStreamDaoTest {

    @Autowired
    private HcStreamDao hcStreamDao;

    @Test
    public void insertStream(){
        HcStream hcStream = new HcStream();
        hcStream.setHcId("2019022811532157811");
        hcStream.setHcResult("P");
        hcStream.setHcStatus("P01");
        hcStreamDao.insertStream(hcStream);
    }

    @Test
    public void updateStream(){
        HcStream hcStream = new HcStream();
        hcStream.setHcId("2019022811532157811");
        hcStream.setHcResult("P");
        hcStream.setHcStatus("P02");
        hcStreamDao.updateStream(hcStream);
    }
}