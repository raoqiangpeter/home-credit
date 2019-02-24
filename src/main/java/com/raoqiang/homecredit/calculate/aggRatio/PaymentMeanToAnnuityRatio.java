package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class PaymentMeanToAnnuityRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double INS_AMT_PAYMENT_MEAN = GetValueFromMap.getDouble(map, "INS_AMT_PAYMENT_MEAN");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (INS_AMT_PAYMENT_MEAN == null || AMT_ANNUITY == null){
            map.put("PAYMENT_MEAN_TO_ANNUITY_RATIO", null);
        }else {
            map.put("PAYMENT_MEAN_TO_ANNUITY_RATIO", INS_AMT_PAYMENT_MEAN/AMT_ANNUITY);
        }
    }
}
