package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class BureauIncomeCreditRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ BUREAU_INCOME_CREDIT_RATIO ]");
        Double BUREAU_AMT_CREDIT_SUM_MEAN = GetValueFromMap.getDouble(map, "BUREAU_AMT_CREDIT_SUM_MEAN");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (BUREAU_AMT_CREDIT_SUM_MEAN == null || AMT_INCOME_TOTAL == null){
            map.put("BUREAU_INCOME_CREDIT_RATIO", null);
        }else {
            map.put("BUREAU_INCOME_CREDIT_RATIO", BUREAU_AMT_CREDIT_SUM_MEAN/AMT_INCOME_TOTAL);
        }
    }
}
