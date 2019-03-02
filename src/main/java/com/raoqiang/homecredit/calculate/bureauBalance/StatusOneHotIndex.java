package com.raoqiang.homecredit.calculate.bureauBalance;

import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.BureauBalanceOneHotIndex;

import java.util.Map;

public class StatusOneHotIndex implements Calculate {

    /**
     * 计算 STATUS 栏位值对应的 ONE_HOT编码
     * @param map 申请信息
     */
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
