package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CarToEmployedRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // df['CAR_TO_EMPLOYED_RATIO'] = df['OWN_CAR_AGE'] / df['DAYS_EMPLOYED']
        Double OWN_CAR_AGE = GetValueFromMap.getDouble(map, "OWN_CAR_AGE");
        Double DAYS_EMPLOYED = GetValueFromMap.getDouble(map, "DAYS_EMPLOYED");
        if (OWN_CAR_AGE == null || DAYS_EMPLOYED == null){
            map.put("CAR_TO_EMPLOYED_RATIO", null);
        }else {
            map.put("CAR_TO_EMPLOYED_RATIO", OWN_CAR_AGE/DAYS_EMPLOYED);
        }
    }
}
