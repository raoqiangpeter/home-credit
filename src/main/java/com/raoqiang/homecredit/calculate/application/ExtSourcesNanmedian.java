package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class ExtSourcesNanmedian implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 EXT_SOURCES_NANMEDIAN 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ EXT_SOURCES_NANMEDIAN ]");
//        if ()
        double res = 0;
        int count = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i=1; i<=3;i++){
            if (GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i)!=null){
                min = Double.min(min, GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i));
                max = Double.max(max, GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i));
                res += GetValueFromMap.getDouble(map, "EXT_SOURCE_"+i);
                count ++;
            }
        }
        if (count == 0){
            map.put("EXT_SOURCES_NANMEDIAN", null);
        }else if (count == 1 || count == 2){
            map.put("EXT_SOURCES_NANMEDIAN", res/count);
        }else {
            map.put("EXT_SOURCES_NANMEDIAN", res - max - min);
        }
    }
}
