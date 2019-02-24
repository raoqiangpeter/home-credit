package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CreditToAnnuityRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // df['CREDIT_TO_ANNUITY_RATIO'] = df['AMT_CREDIT'] / df['AMT_ANNUITY']

        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (AMT_CREDIT == null || AMT_ANNUITY == null){
            map.put("CREDIT_TO_ANNUITY_RATIO", null);
        }else {
            map.put("CREDIT_TO_ANNUITY_RATIO", AMT_CREDIT/AMT_ANNUITY);
        }

    }
}
