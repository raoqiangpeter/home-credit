package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CurrentToApprovedAnnuityMaxRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_AMT_ANNUITY_MAX = GetValueFromMap.getDouble(map, "APPROVED_AMT_ANNUITY_MAX");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (APPROVED_AMT_ANNUITY_MAX == null || AMT_ANNUITY == null){
            map.put("CURRENT_TO_APPROVED_ANNUITY_MAX_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_ANNUITY_MAX_RATIO", APPROVED_AMT_ANNUITY_MAX/AMT_ANNUITY);
        }
    }
}
