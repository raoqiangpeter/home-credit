package com.raoqiang.homecredit.calculate.bureauBalance;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.BureauBalanceOneHotIndex;

import java.util.Map;

public class StatusOneHotIndex implements Calculate {
    @Override
    public void labelCalculate(Map map) {
        String STATUS = GetValueFromMap.getString(map, "STATUS");

        for (String s: BureauBalanceOneHotIndex.STATUS_ONE_HOT_INDEX){
            if(s.substring(s.lastIndexOf("_")+1).equals(""+STATUS)){
                map.put(s, 1);
            }else {
                map.put(s, 0);
            }
        }
        int sum = 0;
        for (int i=1; i<6; i++){
            sum += Integer.parseInt(""+map.get("STATUS_"+i));
        }
        map.put("STATUS_12345", sum);
    }
}
