package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DaysLastPhoneChange implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DAYS_LAST_PHONE_CHANGE ]");
        if (map==null){
            return;
        }
        if (map.get("DAYS_LAST_PHONE_CHANGE")==null || Double.parseDouble(map.get("DAYS_LAST_PHONE_CHANGE")+"")==0){
            map.put("DAYS_LAST_PHONE_CHANGE", null);

        }
    }
}
