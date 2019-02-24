package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CurrentToApprovedCreditMeanRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_AMT_CREDIT_MEAN = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MEAN");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MEAN == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO", APPROVED_AMT_CREDIT_MEAN/AMT_CREDIT);
        }
    }
}
