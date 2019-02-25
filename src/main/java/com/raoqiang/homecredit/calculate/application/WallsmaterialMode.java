package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class WallsmaterialMode implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ WALLSMATERIAL_MODE ]");
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
