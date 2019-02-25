package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DocumentCount implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ DOCUMENT_COUNT ]");
        /*
            df['DOCUMENT_COUNT'] = df[docs].sum(axis=1)
            FLAG_DOCUMENT_2 到 FLAG_DOCUMENT_21求和
         */
        double res = 0;
        for (int i=2; i<22; i++){
            if (GetValueFromMap.getDouble(map, "FLAG_DOCUMENT_"+i) == null)
                continue;
            res += GetValueFromMap.getDouble(map, "FLAG_DOCUMENT_"+i);
        }
        map.put("DOCUMENT_COUNT", res);

    }
}
