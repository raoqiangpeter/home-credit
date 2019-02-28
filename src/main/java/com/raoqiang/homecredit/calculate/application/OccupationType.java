package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class OccupationType implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 OCCUPATION_TYPE 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ OCCUPATION_TYPE ]");
        // OCCUPATION_TYPE
        String tmp = GetValueFromMap.getString(map, "OCCUPATION_TYPE");
        if (tmp == null){
            map.put("OCCUPATION_TYPE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.OCCUPATION_TYPE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("OCCUPATION_TYPE", null);
            }else {
                map.put("OCCUPATION_TYPE", index);
            }
        }
    }
}
