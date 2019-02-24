package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class NameFamilyStatus implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // NAME_FAMILY_STATUS
        String tmp = GetValueFromMap.getString(map, "NAME_FAMILY_STATUS");
        if (tmp == null){
            map.put("NAME_FAMILY_STATUS", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.NAME_FAMILY_STATUS_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("NAME_FAMILY_STATUS", null);
            }else {
                map.put("NAME_FAMILY_STATUS", index);
            }
        }
    }
}
