package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DaysCreditMeanToEmployed implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DAYS_CREDIT_MEAN_TO_EMPLOYED ]");
        Double BUREAU_DAYS_CREDIT_MEAN = GetValueFromMap.getDouble(map, "BUREAU_DAYS_CREDIT_MEAN");
        Double DAYS_EMPLOYED = GetValueFromMap.getDouble(map, "DAYS_EMPLOYED");
        if (BUREAU_DAYS_CREDIT_MEAN == null || DAYS_EMPLOYED == null){
            map.put("DAYS_CREDIT_MEAN_TO_EMPLOYED", null);
        }else {
            map.put("DAYS_CREDIT_MEAN_TO_EMPLOYED", BUREAU_DAYS_CREDIT_MEAN/DAYS_EMPLOYED);
        }
    }
}
