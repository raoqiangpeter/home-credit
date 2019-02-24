package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class EmployedToBirthRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // df['EMPLOYED_TO_BIRTH_RATIO'] = df['DAYS_EMPLOYED'] / df['DAYS_BIRTH']
        Double DAYS_EMPLOYED = GetValueFromMap.getDouble(map, "DAYS_EMPLOYED");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (DAYS_EMPLOYED == null || DAYS_BIRTH == null){
            map.put("EMPLOYED_TO_BIRTH_RATIO", null);
        }else {
            map.put("EMPLOYED_TO_BIRTH_RATIO", DAYS_EMPLOYED/DAYS_BIRTH);
        }
    }
}
