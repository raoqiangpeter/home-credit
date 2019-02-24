package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class ExtSourcesMin implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double EXT_SOURCE_1 = GetValueFromMap.getDouble(map, "EXT_SOURCE_1");
        Double EXT_SOURCE_2 = GetValueFromMap.getDouble(map, "EXT_SOURCE_2");
        Double EXT_SOURCE_3 = GetValueFromMap.getDouble(map, "EXT_SOURCE_3");
        if(EXT_SOURCE_1==null&&EXT_SOURCE_2==null&&EXT_SOURCE_3==null){
            map.put("EXT_SOURCES_MIN", null);
            return;
        }
        EXT_SOURCE_1 = EXT_SOURCE_1==null?Double.MAX_VALUE:EXT_SOURCE_1;
        EXT_SOURCE_2 = EXT_SOURCE_2==null?Double.MAX_VALUE:EXT_SOURCE_2;
        EXT_SOURCE_3 = EXT_SOURCE_3==null?Double.MAX_VALUE:EXT_SOURCE_3;
        map.put("EXT_SOURCES_MIN", Math.min(EXT_SOURCE_1, Math.min(EXT_SOURCE_2, EXT_SOURCE_3)));
    }
}
