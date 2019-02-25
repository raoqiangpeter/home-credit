package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class ExtSourcesMean implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ EXT_SOURCES_MEAN ]");
//        if ()
        double res = 0;
        int count = 0;
        for (int i=1; i<=3;i++){
            if (GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i)!=null){
                res += GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i);
                count ++;
            }
        }
        if (count == 0){
            map.put("EXT_SOURCES_MEAN", null);
        }else{
            map.put("EXT_SOURCES_MEAN", res/count);
        }


    }
}
