package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.BureauOneHotIndex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CreditCurrencyOneHotIndex implements Calculate {

    private static final Log LOG = LogFactory.getLog(CreditCurrencyOneHotIndex.class);

    /**
     * 计算 CreditCurrencyOneHotIndex 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {

        LOG.info("Calculate feature [ CreditCurrencyOneHotIndex ]");
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
