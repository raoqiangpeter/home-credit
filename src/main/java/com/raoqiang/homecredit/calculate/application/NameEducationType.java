package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class NameEducationType implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // NAME_EDUCATION_TYPE
        String tmp = GetValueFromMap.getString(map, "NAME_EDUCATION_TYPE");
        if (tmp == null){
            map.put("NAME_EDUCATION_TYPE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.NAME_EDUCATION_TYPE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("NAME_EDUCATION_TYPE", null);
            }else {
                map.put("NAME_EDUCATION_TYPE", index);
            }
        }
    }
}