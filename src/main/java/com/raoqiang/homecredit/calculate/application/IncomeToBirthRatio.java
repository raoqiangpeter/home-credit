package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class IncomeToBirthRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
//        df['INCOME_TO_BIRTH_RATIO'] = df['AMT_INCOME_TOTAL'] / df['DAYS_BIRTH']

        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (AMT_INCOME_TOTAL == null || DAYS_BIRTH == null){
            map.put("INCOME_TO_BIRTH_RATIO", null);
        }else {
            map.put("INCOME_TO_BIRTH_RATIO", AMT_INCOME_TOTAL/DAYS_BIRTH);
        }

    }
}
