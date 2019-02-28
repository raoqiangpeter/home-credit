package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DaysEmployed implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 DAYS_EMPLOYED 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DAYS_EMPLOYED ]");
        if (map==null){
            return;
        }
        if (map.get("DAYS_EMPLOYED")==null || Double.parseDouble(map.get("DAYS_EMPLOYED")+"")==365243){
            map.put("DAYS_EMPLOYED", null);

        }
    }
}
