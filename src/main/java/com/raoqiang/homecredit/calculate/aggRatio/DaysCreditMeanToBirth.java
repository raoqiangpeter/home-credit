package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class DaysCreditMeanToBirth implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double BUREAU_DAYS_CREDIT_MEAN = GetValueFromMap.getDouble(map, "BUREAU_DAYS_CREDIT_MEAN");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (BUREAU_DAYS_CREDIT_MEAN == null || DAYS_BIRTH == null){
            map.put("DAYS_CREDIT_MEAN_TO_BIRTH", null);
        }else {
            map.put("DAYS_CREDIT_MEAN_TO_BIRTH", BUREAU_DAYS_CREDIT_MEAN/DAYS_BIRTH);
        }
    }
}
