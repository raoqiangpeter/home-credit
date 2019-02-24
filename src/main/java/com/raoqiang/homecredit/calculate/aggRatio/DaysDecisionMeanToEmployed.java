package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class DaysDecisionMeanToEmployed implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_DAYS_DECISION_MEAN = GetValueFromMap.getDouble(map, "APPROVED_DAYS_DECISION_MEAN");
        Double DAYS_EMPLOYED = GetValueFromMap.getDouble(map, "DAYS_EMPLOYED");
        if (APPROVED_DAYS_DECISION_MEAN == null || DAYS_EMPLOYED == null){
            map.put("DAYS_DECISION_MEAN_TO_EMPLOYED", null);
        }else {
            map.put("DAYS_DECISION_MEAN_TO_EMPLOYED", APPROVED_DAYS_DECISION_MEAN/DAYS_EMPLOYED);
        }
    }
}
