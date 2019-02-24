package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CurrentToApprovedCreditMaxRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_AMT_CREDIT_MAX = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MAX");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MAX == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MAX_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MAX_RATIO", APPROVED_AMT_CREDIT_MAX/AMT_CREDIT);
        }
    }
}
