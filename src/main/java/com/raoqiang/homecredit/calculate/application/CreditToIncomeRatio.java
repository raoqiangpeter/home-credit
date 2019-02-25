package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CreditToIncomeRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        // df['CREDIT_TO_INCOME_RATIO'] = df['AMT_CREDIT'] / df['AMT_INCOME_TOTAL']
        LOG.info("Calculate feature [ CREDIT_TO_INCOME_RATIO ]");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (AMT_CREDIT == null || AMT_INCOME_TOTAL == null){
            map.put("CREDIT_TO_INCOME_RATIO", null);
        }else {
            map.put("CREDIT_TO_INCOME_RATIO", AMT_CREDIT/AMT_INCOME_TOTAL);
        }

    }
}
