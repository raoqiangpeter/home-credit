package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CarToBirthRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // df['CAR_TO_BIRTH_RATIO'] = df['OWN_CAR_AGE'] / df['DAYS_BIRTH']
        Double OWN_CAR_AGE = GetValueFromMap.getDouble(map, "OWN_CAR_AGE");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (OWN_CAR_AGE == null || DAYS_BIRTH == null){
            map.put("CAR_TO_BIRTH_RATIO", null);
        }else {
            map.put("CAR_TO_BIRTH_RATIO", OWN_CAR_AGE/DAYS_BIRTH);
        }
    }
}
