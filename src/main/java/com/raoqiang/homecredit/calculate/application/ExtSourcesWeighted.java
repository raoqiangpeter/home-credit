package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class ExtSourcesWeighted implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double EXT_SOURCE_1 = GetValueFromMap.getDouble(map, "EXT_SOURCE_1");
        Double EXT_SOURCE_2 = GetValueFromMap.getDouble(map, "EXT_SOURCE_2");
        Double EXT_SOURCE_3 = GetValueFromMap.getDouble(map, "EXT_SOURCE_3");
        if(EXT_SOURCE_1==null || EXT_SOURCE_2==null || EXT_SOURCE_3==null){
            map.put("EXT_SOURCES_WEIGHTED", null);
            return;
        }
        map.put("EXT_SOURCES_WEIGHTED",EXT_SOURCE_1 * 2 + EXT_SOURCE_2 * 1 +  EXT_SOURCE_3 * 3);
    }
}
