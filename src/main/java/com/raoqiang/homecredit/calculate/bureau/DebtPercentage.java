package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class DebtPercentage implements Calculate {
    @Override
    public void labelCalculate(Map map) {

        // bureau['DEBT_PERCENTAGE'] = bureau['AMT_CREDIT_SUM'] / bureau['AMT_CREDIT_SUM_DEBT']

        Double AMT_CREDIT_SUM = GetValueFromMap.getDouble(map, "AMT_CREDIT_SUM");
        Double AMT_CREDIT_SUM_DEBT = GetValueFromMap.getDouble(map, "AMT_CREDIT_SUM_DEBT");
        if (AMT_CREDIT_SUM == null || AMT_CREDIT_SUM_DEBT == null || AMT_CREDIT_SUM_DEBT == 0.0){
            map.put("DEBT_PERCENTAGE", null);
        }else {
            map.put("DEBT_PERCENTAGE", AMT_CREDIT_SUM / AMT_CREDIT_SUM_DEBT);
        }
    }
}
