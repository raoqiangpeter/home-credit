package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class AnnuityToIncomeRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        //df['ANNUITY_TO_INCOME_RATIO'] = df['AMT_ANNUITY'] / df['AMT_INCOME_TOTAL']

        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (AMT_ANNUITY == null || AMT_INCOME_TOTAL == null){
            map.put("ANNUITY_TO_INCOME_RATIO", null);
        }else {
            map.put("ANNUITY_TO_INCOME_RATIO", AMT_ANNUITY/AMT_INCOME_TOTAL);
        }

    }
}
