package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class CreditToGoodsRatio implements Calculate {
    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        // df['CREDIT_TO_GOODS_RATIO'] = df['AMT_CREDIT'] / df['AMT_GOODS_PRICE']
        LOG.info("Calculate feature [ CREDIT_TO_GOODS_RATIO ]");
        Double AMT_CREDIT = GetValueFromMap.getDouble(map, "AMT_CREDIT");
        Double AMT_GOODS_PRICE = GetValueFromMap.getDouble(map, "AMT_GOODS_PRICE");
        if (AMT_CREDIT == null || AMT_GOODS_PRICE == null){
            map.put("CREDIT_TO_GOODS_RATIO", null);
        }else {
            map.put("CREDIT_TO_GOODS_RATIO", AMT_CREDIT/AMT_GOODS_PRICE);
        }
    }
}
