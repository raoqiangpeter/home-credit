package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CurrentToApprovedCreditMinRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_AMT_CREDIT_MIN = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MIN");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MIN == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MIN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MIN_RATIO", APPROVED_AMT_CREDIT_MIN/AMT_CREDIT);
        }
    }
}
