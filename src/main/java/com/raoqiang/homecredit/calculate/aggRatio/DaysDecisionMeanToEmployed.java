package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DaysDecisionMeanToEmployed implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 DAYS_DECISION_MEAN_TO_EMPLOYED 栏位值，= APPROVED_DAYS_DECISION_MEAN/DAYS_EMPLOYED
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DAYS_DECISION_MEAN_TO_EMPLOYED ]");
        Double APPROVED_DAYS_DECISION_MEAN = GetValueFromMap.getDouble(map, "APPROVED_DAYS_DECISION_MEAN");
        Double DAYS_EMPLOYED = GetValueFromMap.getDouble(map, "DAYS_EMPLOYED");
        if (APPROVED_DAYS_DECISION_MEAN == null || DAYS_EMPLOYED == null){
            map.put("DAYS_DECISION_MEAN_TO_EMPLOYED", null);
        }else {
            map.put("DAYS_DECISION_MEAN_TO_EMPLOYED", APPROVED_DAYS_DECISION_MEAN/DAYS_EMPLOYED);
        }
    }
}
