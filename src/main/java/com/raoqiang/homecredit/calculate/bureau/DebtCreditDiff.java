package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class DebtCreditDiff implements Calculate {


    /**
     * 计算 DEBT_CREDIT_DIFF 栏位值， = AMT_CREDIT_SUM - AMT_CREDIT_SUM
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        // bureau['DEBT_CREDIT_DIFF'] = bureau['AMT_CREDIT_SUM'] - bureau['AMT_CREDIT_SUM_DEBT']

        Double AMT_CREDIT_SUM = GetValueFromMap.getDouble(map, "AMT_CREDIT_SUM");
        Double AMT_CREDIT_SUM_DEBT = GetValueFromMap.getDouble(map, "AMT_CREDIT_SUM_DEBT");
        if (AMT_CREDIT_SUM == null || AMT_CREDIT_SUM_DEBT == null){
            map.put("DEBT_CREDIT_DIFF", null);
        }else {
            map.put("DEBT_CREDIT_DIFF", AMT_CREDIT_SUM - AMT_CREDIT_SUM);
        }
    }
}
