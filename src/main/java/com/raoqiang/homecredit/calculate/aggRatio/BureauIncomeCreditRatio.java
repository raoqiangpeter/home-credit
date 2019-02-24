package com.raoqiang.homecredit.calculate.aggRatio;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class BureauIncomeCreditRatio implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        Double BUREAU_AMT_CREDIT_SUM_MEAN = GetValueFromMap.getDouble(map, "BUREAU_AMT_CREDIT_SUM_MEAN");
        Double AMT_INCOME_TOTAL = GetValueFromMap.getDouble(map, "AMT_INCOME_TOTAL");
        if (BUREAU_AMT_CREDIT_SUM_MEAN == null || AMT_INCOME_TOTAL == null){
            map.put("BUREAU_INCOME_CREDIT_RATIO", null);
        }else {
            map.put("BUREAU_INCOME_CREDIT_RATIO", BUREAU_AMT_CREDIT_SUM_MEAN/AMT_INCOME_TOTAL);
        }
    }
}
