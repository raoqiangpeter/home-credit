package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DaysDecisionMeanToBirth implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DAYS_DECISION_MEAN_TO_BIRTH ]");
        Double APPROVED_DAYS_DECISION_MEAN = GetValueFromMap.getDouble(map, "APPROVED_DAYS_DECISION_MEAN");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (APPROVED_DAYS_DECISION_MEAN == null || DAYS_BIRTH == null){
            map.put("DAYS_DECISION_MEAN_TO_BIRTH", null);
        }else {
            map.put("DAYS_DECISION_MEAN_TO_BIRTH", APPROVED_DAYS_DECISION_MEAN/DAYS_BIRTH);
        }
    }
}
