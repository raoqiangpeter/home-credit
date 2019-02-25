package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class AnnuityToIncomeRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        //df['ANNUITY_TO_INCOME_RATIO'] = df['AMT_ANNUITY'] / df['AMT_INCOME_TOTAL']
        LOG.info("Calculate feature [ ANNUITY_TO_INCOME_RATIO ]");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (AMT_ANNUITY == null || AMT_INCOME_TOTAL == null){
            map.put("ANNUITY_TO_INCOME_RATIO", null);
        }else {
            map.put("ANNUITY_TO_INCOME_RATIO", AMT_ANNUITY/AMT_INCOME_TOTAL);
        }

    }
}
