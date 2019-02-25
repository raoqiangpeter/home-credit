package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class BureauActiveCreditToIncomeRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ BUREAU_ACTIVE_CREDIT_TO_INCOME_RATIO ]");
        Double BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM = GetValueFromMap.getDouble(map, "BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM == null || AMT_INCOME_TOTAL == null){
            map.put("BUREAU_ACTIVE_CREDIT_TO_INCOME_RATIO", null);
        }else {
            map.put("BUREAU_ACTIVE_CREDIT_TO_INCOME_RATIO", BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM/AMT_INCOME_TOTAL);
        }
    }
}
