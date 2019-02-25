package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class OrganizationType implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ ORGANIZATION_TYPE ]");
        // ORGANIZATION_TYPE
        String tmp = GetValueFromMap.getString(map, "ORGANIZATION_TYPE");
        if (tmp == null){
            map.put("ORGANIZATION_TYPE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.ORGANIZATION_TYPE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("ORGANIZATION_TYPE", null);
            }else {
                map.put("ORGANIZATION_TYPE", index);
            }
        }
    }
}
