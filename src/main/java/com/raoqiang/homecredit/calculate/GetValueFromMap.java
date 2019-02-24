package com.raoqiang.homecredit.calculate;

import java.util.Map;

/**
 * 获取特定类型的数据从Map中
 */
public class GetValueFromMap {

    public static Double getDouble(Map map, String key){
        if(map == null || map.get(key) == null){
            return null;
        }
        return Double.parseDouble("" + map.get(key));
    }

    public static Integer getInteger(Map map, String key){
        if(map == null || map.get(key) == null){
            return null;
        }
        return Integer.parseInt("" + map.get(key));
    }

    public static String getString(Map map, String key){
        if(map == null || map.get(key) == null){
            return null;
        }
        return "" + map.get(key);
    }

    public static void removeKeysFromMap(Map map, String[] keys){
        if (keys==null || keys.length == 0){
            return;
        }
        for (String key : keys){
                map.remove(key);
        }
    }

}
