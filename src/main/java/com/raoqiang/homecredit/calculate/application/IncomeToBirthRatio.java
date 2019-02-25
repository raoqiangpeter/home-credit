package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class IncomeToBirthRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
//        df['INCOME_TO_BIRTH_RATIO'] = df['AMT_INCOME_TOTAL'] / df['DAYS_BIRTH']
        LOG.info("Calculate feature [ INCOME_TO_BIRTH_RATIO ]");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (AMT_INCOME_TOTAL == null || DAYS_BIRTH == null){
            map.put("INCOME_TO_BIRTH_RATIO", null);
        }else {
            map.put("INCOME_TO_BIRTH_RATIO", AMT_INCOME_TOTAL/DAYS_BIRTH);
        }

    }
}
