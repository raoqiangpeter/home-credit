package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class PaymentMinToAnnuityRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ PAYMENT_MIN_TO_ANNUITY_RATIO ]");
        Double INS_AMT_PAYMENT_MIN = GetValueFromMap.getDouble(map, "INS_AMT_PAYMENT_MIN");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (INS_AMT_PAYMENT_MIN == null || AMT_ANNUITY == null){
            map.put("PAYMENT_MIN_TO_ANNUITY_RATIO", null);
        }else {
            map.put("PAYMENT_MIN_TO_ANNUITY_RATIO", INS_AMT_PAYMENT_MIN/AMT_ANNUITY);
        }
    }
}
