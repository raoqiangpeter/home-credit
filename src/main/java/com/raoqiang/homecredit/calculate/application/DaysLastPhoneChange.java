package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;

import java.util.Map;

public class DaysLastPhoneChange implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        if (map==null){
            return;
        }
        if (map.get("DAYS_LAST_PHONE_CHANGE")==null || Double.parseDouble(map.get("DAYS_LAST_PHONE_CHANGE")+"")==0){
            map.put("DAYS_LAST_PHONE_CHANGE", null);

        }
    }
}
