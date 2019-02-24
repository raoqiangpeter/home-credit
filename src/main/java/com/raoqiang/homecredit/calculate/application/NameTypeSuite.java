package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class NameTypeSuite implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // NAME_TYPE_SUITE
        String tmp = GetValueFromMap.getString(map, "NAME_TYPE_SUITE");
        if (tmp == null){
            map.put("NAME_TYPE_SUITE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.NAME_TYPE_SUITE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("NAME_TYPE_SUITE", null);
            }else {
                map.put("NAME_TYPE_SUITE", index);
            }
        }
    }
}
