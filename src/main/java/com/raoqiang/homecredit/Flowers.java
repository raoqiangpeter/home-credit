package com.raoqiang.homecredit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.DropIndex;
import com.raoqiang.homecredit.entry.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Flowers {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        File file = new File("E:\\workspace\\spark-study\\home-credit\\src\\main\\resources\\application.json");
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        String rs = "";
//        int a;
        while ((is.read(bytes))>0){
            rs += new String(bytes);
            bytes=new byte[1024];
        }
//        System.out.println(rs);
        Application application = JSON.parseObject(rs, Application.class);
        System.out.println(application);
        Map map = application.getData();
//
        Class clazz1 = Class.forName("com.raoqiang.homecredit.calculate.application.AgeRange");
        Calculate calculate1 = (Calculate) clazz1.newInstance();
        calculate1.labelCalculate(map);


        System.out.println(map.get("AGE_RANGE"));
        System.out.println(map.get("CODE_GENDER"));
        System.out.println(map.get("NAME_EDUCATION_TYPE"));
        System.out.println(map.get("OCCUPATION_TYPE"));
        System.out.println(map.get("ORGANIZATION_TYPE"));

        Map appAgg = new HashMap();
        appAgg.put("AGE_RANGE",map.get("AGE_RANGE")+"");
        appAgg.put("CODE_GENDER",map.get("CODE_GENDER"));
        appAgg.put("NAME_EDUCATION_TYPE",map.get("NAME_EDUCATION_TYPE"));
        appAgg.put("OCCUPATION_TYPE",map.get("OCCUPATION_TYPE"));
        appAgg.put("ORGANIZATION_TYPE",map.get("ORGANIZATION_TYPE"));

        Request request = new Request();
        Params param = new Params();
        param.setTableName("T_APPLICATION_AGG");
//        Map con = new HashMap();
//        con.put("SK_ID_CURR","100170");
        param.setCondition(appAgg);
        request.setParams(param);
        HttpEntity<String> formEntity1 = new HttpEntity<String>(JSONObject.toJSONString(request), headers);
//        log.debug("执行结果为 -> " + result.toString());
        Response result = null;
        result = restTemplate.postForObject("http://localhost:8080/phoenix/hbase/query/detail", formEntity1, Response.class);

        System.out.println(result);
        map.putAll(((Map)(result.getData().get(0))));
        // -----------------------------------------------------------------------------
        // 处理 application_train/test
        String[] strs = new String[]{"DaysEmployed", "ExtSourcesMax", "ExtSourcesMean", "ExtSourcesMin", "NewDocKurt",
                "ExtSourcesProd", "ExtSourcesWeighted", "AgeRange", "DocumentCount", "CreditToAnnuityRatio",
                "CreditToGoodsRatio", "AnnuityToIncomeRatio", "CreditToIncomeRatio", "IncomeToEmployedRatio",
                "EmployedToBirthRatio", "IdToBirthRatio", "CarToBirthRatio", "CarToEmployedRatio", "PhoneToBirthRatio",
                "CodeGender", "EmergencystateMode", "FlagOwnCar", "FlagOwnRealty", "FondkapremontMode", "HousetypeMode",
                "NameContractType", "NameEducationType", "NameFamilyStatus", "NameHousingType", "NameIncomeType",
                "NameTypeSuite", "OccupationType", "OrganizationType", "WallsmaterialMode", "WeekdayApprProcessStart"

        };
        Class<?> clazz;
        for(String s: strs){
            clazz = Class.forName("com.raoqiang.homecredit.calculate.application."+s);
            Calculate calculate = (Calculate) clazz.newInstance();
            calculate.labelCalculate(map);
        }
        GetValueFromMap.removeKeysFromMap(map, DropIndex.APPLICATION_DROP_INDEX);






//        String url = "http://192.168.67.136:8080/phoenix/hbase/query/detail";

        // 获取 T_CREDIT_CARD 表数据

//        String url = "http://localhost:8080/phoenix/hbase/query/detail";
//        String params = "{\n" +
//                "\t\"anthInfo\":{\n" +
//                "\t\t\"user\":\"raoqiang\",\n" +
//                "\t\t\"password\":\"123456\"\n" +
//                "\t},\n" +
//                "\t\"params\": {\n" +
//                "\t\t\"tableName\": \"T_CREDIT_CARD\",\n" +
//                "\t\t\"indexName\":\"\",\n" +
//                "\t\t\"condition\":{\n" +
//                "\t\t\t\"SK_ID_CURR\": \"100170\"\n" +
//                "\t\t},\n" +
//                "\t\t\"columns\":null\n" +
//                "\t}\n" +
//                "}";
//        JSONObject jsonObj = JSONObject.parseObject(params);
//
//        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
////        log.debug("执行结果为 -> " + result.toString());
//        Response result = null;
//
//        result = restTemplate.postForObject(url, formEntity, Response.class);
//        System.out.println(result);
        Response T_CREDIT_CARD = help(restTemplate, headers, "T_CREDIT_CARD");
//        System.out.println(T_CREDIT_CARD);

//        Response T_APPLICATION_AGG = help(restTemplate, headers, "T_APPLICATION_AGG");
//        System.out.println(T_APPLICATION_AGG);

        Response T_BUREAU_AGG = help(restTemplate, headers, "T_BUREAU_AGG");
//        System.out.println(T_BUREAU_AGG);

        Response T_INSTALLMENT_AGG = help(restTemplate, headers, "T_INSTALLMENT_AGG");
//        System.out.println(T_INSTALLMENT_AGG);

        Response T_POS_AGG = help(restTemplate, headers, "T_POS_AGG");
//        System.out.println(T_POS_AGG);

        Response T_PREVIOUS_AGG = help(restTemplate, headers, "T_PREVIOUS_AGG");
//        System.out.println(T_PREVIOUS_AGG);

//        Map map1 = new HashMap();
        if (T_CREDIT_CARD.getData().size()==1)
            map.putAll(((Map)(T_CREDIT_CARD.getData().get(0))));
        if (T_BUREAU_AGG.getData().size()==1)
            map.putAll(((Map)(T_BUREAU_AGG.getData().get(0))));
        if (T_INSTALLMENT_AGG.getData().size()==1)
            map.putAll(((Map)(T_INSTALLMENT_AGG.getData().get(0))));
        if (T_POS_AGG.getData().size()==1)
            map.putAll(((Map)(T_POS_AGG.getData().get(0))));
        if (T_PREVIOUS_AGG.getData().size()==1)
            map.putAll(((Map)(T_PREVIOUS_AGG.getData().get(0))));


        System.out.println(map);

        HashMap h = new HashMap();

        for (Object key:map.keySet()){
            if (!"NaN".equals(map.get(key)))
                h.put(key, map.get(key));
        }



        String[] str = {"PREV_LAST12M_DAYS_LAST_DUE_1ST_VERSION_MEAN", "PREV_DAYS_TERMINATION_MAX",
                "PREV_LATE_DAYS_LAST_DUE_1ST_VERSION_MEAN","PREV_LAST12M_DAYS_LAST_DUE_1ST_VERSION_MIN",
                "PREV_LATE_DAYS_LAST_DUE_1ST_VERSION_MAX", "PREV_LAST24M_DAYS_LAST_DUE_1ST_VERSION_MEAN",
                "TOTAL_REPAYMENT_RATIO", "PREV_LAST24M_DAYS_LAST_DUE_1ST_VERSION_MAX",
                "PREV_LAST12M_DAYS_LAST_DUE_1ST_VERSION_MAX"};

        GetValueFromMap.removeKeysFromMap(h, str);

        String url = "http://localhost:8081/pmml/evaluator";
        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setParams(h);

        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(modelRequest), headers);
//        log.debug("执行结果为 -> " + result.toString());

        System.out.println(restTemplate.postForObject(url, formEntity, Response.class));


    }


    public static Response help(RestTemplate restTemplate, HttpHeaders headers, String table){
        String url = "http://localhost:8080/phoenix/hbase/query/detail";
        Request request = new Request();
        Params param = new Params();
        param.setTableName(table);
        Map con = new HashMap();
        con.put("SK_ID_CURR","100001");
        param.setCondition(con);
        request.setParams(param);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(request), headers);
//        log.debug("执行结果为 -> " + result.toString());
        Response result = null;
        result = restTemplate.postForObject(url, formEntity, Response.class);
        return result;
    }
}
