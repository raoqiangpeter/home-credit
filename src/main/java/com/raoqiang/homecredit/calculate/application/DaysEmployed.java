package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;

import java.util.Map;

public class DaysEmployed implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        if (map==null){
            return;
        }
        if (map.get("DAYS_EMPLOYED")==null || Double.parseDouble(map.get("DAYS_EMPLOYED")+"")==365243){
            map.put("DAYS_EMPLOYED", null);

        }
    }
}
