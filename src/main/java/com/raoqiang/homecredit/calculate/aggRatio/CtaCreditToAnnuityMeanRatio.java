package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CtaCreditToAnnuityMeanRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ CTA_CREDIT_TO_ANNUITY_MEAN_RATIO ]");
        Double APPROVED_CREDIT_TO_ANNUITY_RATIO_MEAN = GetValueFromMap.getDouble(map, "APPROVED_CREDIT_TO_ANNUITY_RATIO_MEAN");
        Double CREDIT_TO_ANNUITY_RATIO = GetValueFromMap.getDouble(map, "CREDIT_TO_ANNUITY_RATIO");
        if (APPROVED_CREDIT_TO_ANNUITY_RATIO_MEAN == null || CREDIT_TO_ANNUITY_RATIO == null){
            map.put("CTA_CREDIT_TO_ANNUITY_MEAN_RATIO", null);
        }else {
            map.put("CTA_CREDIT_TO_ANNUITY_MEAN_RATIO", APPROVED_CREDIT_TO_ANNUITY_RATIO_MEAN/CREDIT_TO_ANNUITY_RATIO);
        }
    }
}
