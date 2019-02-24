package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class CodeGender implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        String tmp = GetValueFromMap.getString(map, "CODE_GENDER");
        if (tmp == null){
            map.put("CODE_GENDER", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.CODE_GENDER_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("CODE_GENDER", null);
            }else {
                map.put("CODE_GENDER", index);
            }
        }
    }
}
