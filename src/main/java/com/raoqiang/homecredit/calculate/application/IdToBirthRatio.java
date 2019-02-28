package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class IdToBirthRatio implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 ID_TO_BIRTH_RATIO 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ ID_TO_BIRTH_RATIO ]");
        // df['ID_TO_BIRTH_RATIO'] = df['DAYS_ID_PUBLISH'] / df['DAYS_BIRTH']
        Double DAYS_ID_PUBLISH = GetValueFromMap.getDouble(map, "DAYS_ID_PUBLISH");
        Double DAYS_BIRTH = GetValueFromMap.getDouble(map, "DAYS_BIRTH");
        if (DAYS_ID_PUBLISH == null || DAYS_BIRTH == null){
            map.put("ID_TO_BIRTH_RATIO", null);
        }else {
            map.put("ID_TO_BIRTH_RATIO", DAYS_ID_PUBLISH/DAYS_BIRTH);
        }
    }
}
