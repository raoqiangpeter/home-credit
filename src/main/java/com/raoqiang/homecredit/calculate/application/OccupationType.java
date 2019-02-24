package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class OccupationType implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // OCCUPATION_TYPE
        String tmp = GetValueFromMap.getString(map, "OCCUPATION_TYPE");
        if (tmp == null){
            map.put("OCCUPATION_TYPE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.OCCUPATION_TYPE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("OCCUPATION_TYPE", null);
            }else {
                map.put("OCCUPATION_TYPE", index);
            }
        }
    }
}
