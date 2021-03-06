package com.raoqiang.homecredit.calculate.application;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class AgeRange implements Calculate {
    /*
    df['AGE_RANGE'] = df['DAYS_BIRTH'].apply(lambda x: get_age_label(x))
    def get_age_label(days_birth):
        """ Return the age group label (int). """
        age_years = -days_birth / 365
        if age_years < 27: return 1
        elif age_years < 40: return 2
        elif age_years < 50: return 3
        elif age_years < 65: return 4
        elif age_years < 99: return 5
        else: return 0
     */

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    /**
     * 计算 AGE_RANGE 栏位值
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        LOG.info("Calculate feature [ AGE_RANGE ]");
        int days = GetValueFromMap.getInteger(map, "DAYS_BIRTH");
        map.put("AGE_RANGE", birth2Label(days));
    }

    private int birth2Label(int days){

        int age_years = -days / 365;
        if (age_years < 27){
            return 1;
        }else if(age_years < 40){
            return 2;
        }else if (age_years < 50){
            return 3;
        }else if (age_years < 65){
            return 4;
        }else if (age_years < 99){
            return 5;
        }else {
            return 0;
        }
    }
}
