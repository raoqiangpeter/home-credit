package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CurrentToApprovedCreditMaxRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 CURRENT_TO_APPROVED_CREDIT_MAX_RATIO 栏位值，= APPROVED_AMT_CREDIT_MAX/AMT_CREDIT
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ CURRENT_TO_APPROVED_CREDIT_MAX_RATIO ]");
        Double APPROVED_AMT_CREDIT_MAX = GetValueFromMap.getDouble(map, "APPROVED_AMT_CREDIT_MAX");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        if (APPROVED_AMT_CREDIT_MAX == null || AMT_CREDIT == null){
            map.put("CURRENT_TO_APPROVED_CREDIT_MAX_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_CREDIT_MAX_RATIO", APPROVED_AMT_CREDIT_MAX/AMT_CREDIT);
        }
    }
}
