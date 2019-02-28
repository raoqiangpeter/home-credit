package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class EmergencystateMode implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 EMERGENCYSTATE_MODE 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ EMERGENCYSTATE_MODE ]");
        String tmp = GetValueFromMap.getString(map, "EMERGENCYSTATE_MODE");
        if (tmp == null){
            map.put("EMERGENCYSTATE_MODE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.EMERGENCYSTATE_MODE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("EMERGENCYSTATE_MODE", null);
            }else {
                map.put("EMERGENCYSTATE_MODE", index);
            }
        }
    }
}
