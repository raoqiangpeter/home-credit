package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.ApplicationLabelIndex;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Map;

public class FlagOwnCar implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 FLAG_OWN_CAR 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ FLAG_OWN_CAR ]");
        String tmp = GetValueFromMap.getString(map, "FLAG_OWN_CAR");
        if (tmp == null){
            map.put("FLAG_OWN_CAR", null);
        }else {
            int index = Arrays.asList(ApplicationLabelIndex.FLAG_OWN_CAR_INDEX).indexOf(tmp);
            if (index < 0){
                map.put("FLAG_OWN_CAR", null);
            }else {
                map.put("FLAG_OWN_CAR", index);
            }
        }
    }
}
