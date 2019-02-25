package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class ExtSourcesMin implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ EXT_SOURCES_MIN ]");
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
