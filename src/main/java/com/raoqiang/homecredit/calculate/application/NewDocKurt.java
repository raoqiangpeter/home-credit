package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class NewDocKurt implements Calculate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ NEW_DOC_KURT ]");
        // NEW_DOC_KURT
        Double sum = 0.0;
        Double sqrt = 0.0;
        int count = 0;
        for (int i=2; i<22; i++){
            Double tmp = GetValueFromMap.getDouble(map, "FLAG_DOCUMENT_"+i);
            if (tmp == null)
                continue;
            sum += tmp;
            sqrt += tmp * tmp;
            count += 1;
        }
        if (count == 0){
            map.put("NEW_DOC_KURT", null);
        }
        Double mean = sum / count;
        sqrt = sqrt / count - mean * mean;
//        System.out.println(sqrt+ "<->" +count + "<->" + mean);
        Double s4 = 0.0;
        for (int i=2; i<22; i++){
            Double tmp = GetValueFromMap.getDouble(map, "FLAG_DOCUMENT_"+i);
            if (tmp == null)
                continue;
            s4 += (tmp - mean) * (tmp - mean) * (tmp - mean) * (tmp - mean)/ count;
        }
        map.put("NEW_DOC_KURT", s4 / (sqrt * sqrt) - 3);
    }
}
