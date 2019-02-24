package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;

import java.util.Map;

public class EnddateDif implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        // bureau['ENDDATE_DIF'] = bureau['DAYS_CREDIT_ENDDATE'] - bureau['DAYS_ENDDATE_FACT']

        Double DAYS_CREDIT_ENDDATE = GetValueFromMap.getDouble(map, "DAYS_CREDIT_ENDDATE");
        Double DAYS_ENDDATE_FACT = GetValueFromMap.getDouble(map, "DAYS_ENDDATE_FACT");
        if (DAYS_CREDIT_ENDDATE == null || DAYS_ENDDATE_FACT == null){
            map.put("ENDDATE_DIF", null);
        }else {
            map.put("ENDDATE_DIF", DAYS_CREDIT_ENDDATE - DAYS_ENDDATE_FACT);
        }
    }
}
