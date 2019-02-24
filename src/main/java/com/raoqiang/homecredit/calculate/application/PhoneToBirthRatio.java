package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class PhoneToBirthRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // df['PHONE_TO_BIRTH_RATIO'] = df['DAYS_LAST_PHONE_CHANGE'] / df['DAYS_BIRTH']
        Double DAYS_LAST_PHONE_CHANGE = GetValueFromMap.getDouble(map, "DAYS_LAST_PHONE_CHANGE");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (DAYS_LAST_PHONE_CHANGE == null || DAYS_BIRTH == null){
            map.put("PHONE_TO_BIRTH_RATIO", null);
        }else {
            map.put("PHONE_TO_BIRTH_RATIO", DAYS_LAST_PHONE_CHANGE/DAYS_BIRTH);
        }
    }
}
