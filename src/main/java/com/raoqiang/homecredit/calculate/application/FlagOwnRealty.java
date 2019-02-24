package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class FlagOwnRealty implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        String tmp = GetValueFromMap.getString(map, "FLAG_OWN_REALTY");
        if (tmp == null){
            map.put("FLAG_OWN_REALTY", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.FLAG_OWN_REALTY_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("FLAG_OWN_REALTY", null);
            }else {
                map.put("FLAG_OWN_REALTY", index);
            }
        }
    }
}
