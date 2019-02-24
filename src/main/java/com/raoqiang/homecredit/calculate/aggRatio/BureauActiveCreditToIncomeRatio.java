package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class BureauActiveCreditToIncomeRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM = GetValueFromMap.getDouble(map, "BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM == null || AMT_INCOME_TOTAL == null){
            map.put("BUREAU_ACTIVE_CREDIT_TO_INCOME_RATIO", null);
        }else {
            map.put("BUREAU_ACTIVE_CREDIT_TO_INCOME_RATIO", BUREAU_ACTIVE_AMT_CREDIT_SUM_SUM/AMT_INCOME_TOTAL);
        }
    }
}
