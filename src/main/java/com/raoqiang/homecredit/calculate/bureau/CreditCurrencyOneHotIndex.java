package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.BureauOneHotIndex;

import java.util.Map;

public class CreditCurrencyOneHotIndex implements Calculate {

    @Override
    public void labelCalculate(Map map) {
        String CREDIT_CURRENCY = GetValueFromMap.getString(map, "CREDIT_CURRENCY");

        for (String s: BureauOneHotIndex.CREDIT_CURRENCY_ONE_HOT_INDEX){
            if(s.substring(s.lastIndexOf("_")+1).equals(""+CREDIT_CURRENCY)){
                map.put(s, 1);
            }else {
                map.put(s, 0);
            }
        }

    }
}
