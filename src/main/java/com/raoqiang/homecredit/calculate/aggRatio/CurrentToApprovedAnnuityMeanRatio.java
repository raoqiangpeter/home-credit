package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CurrentToApprovedAnnuityMeanRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO 栏位值，= APPROVED_AMT_ANNUITY_MEAN/AMT_ANNUITY
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO ]");
        Double APPROVED_AMT_ANNUITY_MEAN = GetValueFromMap.getDouble(map, "APPROVED_AMT_ANNUITY_MEAN");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (APPROVED_AMT_ANNUITY_MEAN == null || AMT_ANNUITY == null){
            map.put("CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO", null);
        }else {
            map.put("CURRENT_TO_APPROVED_ANNUITY_MEAN_RATIO", APPROVED_AMT_ANNUITY_MEAN/AMT_ANNUITY);
        }
    }
}
