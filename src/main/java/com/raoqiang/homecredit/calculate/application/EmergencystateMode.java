package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class EmergencystateMode implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        String tmp = GetValueFromMap.getString(map, "EMERGENCYSTATE_MODE");
        if (tmp == null){
            map.put("EMERGENCYSTATE_MODE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.EMERGENCYSTATE_MODE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("EMERGENCYSTATE_MODE", null);
            }else {
                map.put("EMERGENCYSTATE_MODE", index);
            }
        }
    }
}
