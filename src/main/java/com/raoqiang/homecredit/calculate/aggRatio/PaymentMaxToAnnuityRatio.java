package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class PaymentMaxToAnnuityRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ PAYMENT_MAX_TO_ANNUITY_RATIO ]");
        Double INS_AMT_PAYMENT_MAX = GetValueFromMap.getDouble(map, "INS_AMT_PAYMENT_MAX");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (INS_AMT_PAYMENT_MAX == null || AMT_ANNUITY == null){
            map.put("PAYMENT_MAX_TO_ANNUITY_RATIO", null);
        }else {
            map.put("PAYMENT_MAX_TO_ANNUITY_RATIO", INS_AMT_PAYMENT_MAX/AMT_ANNUITY);
        }
    }
}
