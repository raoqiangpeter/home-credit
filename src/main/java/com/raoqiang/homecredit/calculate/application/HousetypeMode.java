package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class HousetypeMode implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ HOUSETYPE_MODE ]");
        // HOUSETYPE_MODE
        String tmp = GetValueFromMap.getString(map, "HOUSETYPE_MODE");
        if (tmp == null){
            map.put("HOUSETYPE_MODE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.HOUSETYPE_MODE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("HOUSETYPE_MODE", null);
            }else {
                map.put("HOUSETYPE_MODE", index);
            }
        }
    }
}
