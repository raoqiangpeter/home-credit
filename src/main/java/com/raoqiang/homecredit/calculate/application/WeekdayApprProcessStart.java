package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class WeekdayApprProcessStart implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ WEEKDAY_APPR_PROCESS_START ]");
        // WEEKDAY_APPR_PROCESS_START
        String tmp = GetValueFromMap.getString(map, "WEEKDAY_APPR_PROCESS_START");
        if (tmp == null){
            map.put("WEEKDAY_APPR_PROCESS_START", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.WEEKDAY_APPR_PROCESS_START_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("WEEKDAY_APPR_PROCESS_START", null);
            }else {
                map.put("WEEKDAY_APPR_PROCESS_START", index);
            }
        }
    }
}
