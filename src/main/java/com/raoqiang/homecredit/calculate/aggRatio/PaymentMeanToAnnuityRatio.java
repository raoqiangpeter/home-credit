package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class PaymentMeanToAnnuityRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 PAYMENT_MEAN_TO_ANNUITY_RATIO 栏位值，= INS_AMT_PAYMENT_MEAN/AMT_ANNUITY
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ PAYMENT_MEAN_TO_ANNUITY_RATIO ]");
        Double INS_AMT_PAYMENT_MEAN = GetValueFromMap.getDouble(map, "INS_AMT_PAYMENT_MEAN");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (INS_AMT_PAYMENT_MEAN == null || AMT_ANNUITY == null){
            map.put("PAYMENT_MEAN_TO_ANNUITY_RATIO", null);
        }else {
            map.put("PAYMENT_MEAN_TO_ANNUITY_RATIO", INS_AMT_PAYMENT_MEAN/AMT_ANNUITY);
        }
    }
}
