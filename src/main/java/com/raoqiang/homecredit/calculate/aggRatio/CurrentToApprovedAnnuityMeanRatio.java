package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CurrentToApprovedAnnuityMeanRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_AMT_ANNUITY_MEAN = GetValueFromMap.getDouble(map, "APPROVED_AMT_ANNUITY_MEAN");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (APPROVED_AMT_ANNUITY_MEAN == null || AMT_ANNUITY == null){
            map.put("CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO", APPROVED_AMT_ANNUITY_MEAN/AMT_ANNUITY);
        }
    }
}
