package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class NameTypeSuite implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 NAME_TYPE_SUITE 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        // NAME_TYPE_SUITE
        LOG.info("Calculate feature [ NAME_TYPE_SUITE ]");
        String tmp = GetValueFromMap.getString(map, "NAME_TYPE_SUITE");
        if (tmp == null){
            map.put("NAME_TYPE_SUITE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.NAME_TYPE_SUITE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("NAME_TYPE_SUITE", null);
            }else {
                map.put("NAME_TYPE_SUITE", index);
            }
        }
    }
}
