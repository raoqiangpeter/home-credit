package com.raoqiang.homecredit.calculate.bureau;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.BureauOneHotIndex;

import java.util.Map;

public class CretitActiveOneHotIndex implements Calculate {

    /**
     * 计算 CREDIT_ACTIVE 栏位值对应的 ONE_HOT编码
     * @param map 申请信息
     */
    @Override
    public void labelCalculate(Map map) {
        String CREDIT_ACTIVE = GetValueFromMap.getString(map, "CREDIT_ACTIVE");

        for (String s: BureauOneHotIndex.CREDIT_ACTIVE_ONE_HOT_INDEX){
            if(s.substring(s.lastIndexOf("_")+1).equals(""+CREDIT_ACTIVE)){
                map.put(s, 1);
            }else {
                map.put(s, 0);
            }
        }

    }
}
