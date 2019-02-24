package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CtaCreditToAnnuityMaxRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double APPROVED_CREDIT_TO_ANNUITY_RATIO_MAX = GetValueFromMap.getDouble(map, "APPROVED_CREDIT_TO_ANNUITY_RATIO_MAX");
        Double CREDIT_TO_ANNUITY_RATIO = GetValueFromMap.getDouble(map, "CREDIT_TO_ANNUITY_RATIO");
        if (APPROVED_CREDIT_TO_ANNUITY_RATIO_MAX == null || CREDIT_TO_ANNUITY_RATIO == null){
            map.put("CTA_CREDIT_TO_ANNUITY_MAX_RATIO", null);
        }else {
            map.put("CTA_CREDIT_TO_ANNUITY_MAX_RATIO", APPROVED_CREDIT_TO_ANNUITY_RATIO_MAX/CREDIT_TO_ANNUITY_RATIO);
        }
    }
}
