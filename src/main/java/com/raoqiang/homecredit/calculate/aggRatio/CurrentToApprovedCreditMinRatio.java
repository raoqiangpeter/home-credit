package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CurrentToApprovedCreditMinRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ CURRENT_TO_APPROVED_CREDIT_MIN_RATIO ]");
        Double APPROVED_AMT_CREDIT_MIN = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MIN");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MIN == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MIN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MIN_RATIO", APPROVED_AMT_CREDIT_MIN/AMT_CREDIT);
        }
    }
}
