package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class NameHousingType implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 NAME_HOUSING_TYPE 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        // NAME_HOUSING_TYPE
        LOG.info("Calculate feature [ NAME_HOUSING_TYPE ]");
        String tmp = GetValueFromMap.getString(map, "NAME_HOUSING_TYPE");
        if (tmp == null){
            map.put("NAME_HOUSING_TYPE", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.NAME_HOUSING_TYPE_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("NAME_HOUSING_TYPE", null);
            }else {
                map.put("NAME_HOUSING_TYPE", index);
            }
        }
    }
}
