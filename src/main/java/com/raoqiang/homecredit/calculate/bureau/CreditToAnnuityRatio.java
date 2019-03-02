package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class CreditToAnnuityRatio implements Calculate {

    /**
     * 计算 CREDIT_TO_ANNUITY_RATIO 栏位值， = AMT_CREDIT_SUM / AMT_ANNUITY
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        //bureau['CREDIT_TO_ANNUITY_RATIO'] = bureau['AMT_CREDIT_SUM'] / bureau['AMT_ANNUITY']

        Double AMT_CREDIT_SUM = GetValueFromMap.getDouble(map, "AMT_CREDIT_SUM");
        Double AMT_ANNUITY = GetValueFromMap.getDouble(map, "AMT_ANNUITY");
        if (AMT_CREDIT_SUM == null || AMT_ANNUITY == null || AMT_ANNUITY == 0.0){
            map.put("CREDIT_TO_ANNUITY_RATIO", null);
        }else {
            map.put("CREDIT_TO_ANNUITY_RATIO", AMT_CREDIT_SUM / AMT_ANNUITY);
        }
    }
}
