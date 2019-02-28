package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * 该变量计算需要在 EXT_SOURCES_MEAN 之后
 */
public class ExtSourcesVar implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 EXT_SOURCES_VAR 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ EXT_SOURCES_VAR ]");
        double res = 0;
        int count = 0;
        for (int i=1; i<=3;i++){
            if (GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i)!=null){
                res += (GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i) - GetValueFromMap.getDouble(map, "EXT_SOURCES_MEAN"))*
                        (GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i) - GetValueFromMap.getDouble(map, "EXT_SOURCES_MEAN"));
                count ++;
            }
        }
        if (count == 0){
            map.put("EXT_SOURCES_VAR", null);
        }else {
            map.put("EXT_SOURCES_VAR", res / count);
        }
    }
}
