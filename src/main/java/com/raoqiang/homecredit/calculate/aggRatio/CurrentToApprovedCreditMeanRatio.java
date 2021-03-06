package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CurrentToApprovedCreditMeanRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO 栏位值，= APPROVED_AMT_CREDIT_MEAN/AMT_CREDIT
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO ]");
        Double APPROVED_AMT_CREDIT_MEAN = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MEAN");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MEAN == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MEAN_RATIO", APPROVED_AMT_CREDIT_MEAN/AMT_CREDIT);
        }
    }
}
