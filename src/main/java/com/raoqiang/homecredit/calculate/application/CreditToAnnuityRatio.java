package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CreditToAnnuityRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 CREDIT_TO_ANNUITY_RATIO 栏位值， = AMT_CREDIT/AMT_ANNUITY
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        // df['CREDIT_TO_ANNUITY_RATIO'] = df['AMT_CREDIT'] / df['AMT_ANNUITY']
        LOG.info("Calculate feature [ CREDIT_TO_ANNUITY_RATIO ]");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (AMT_CREDIT == null || AMT_ANNUITY == null){
            map.put("CREDIT_TO_ANNUITY_RATIO", null);
        }else {
            map.put("CREDIT_TO_ANNUITY_RATIO", AMT_CREDIT/AMT_ANNUITY);
        }

    }
}
