package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;

import java.util.Arrays;
import java.util.Map;

public class WallsmaterialMode implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // WALLSMATERIAL_MODE
        String tmp = GetValueFromMap.getString(map, "WALLSMATERIAL_MODE");
        if (tmp == null){
            map.put("WALLSMATERIAL_MODE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.WALLSMATERIAL_MODE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("WALLSMATERIAL_MODE", null);
            }else {
                map.put("WALLSMATERIAL_MODE", index);
            }
        }
    }
}
