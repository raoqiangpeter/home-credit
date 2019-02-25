package com.raoqiang.homecredit.services;

import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.StringUtils;
import com.raoqiang.homecredit.calculate.application.AgeRange;
import com.raoqiang.homecredit.calculate.constant.DropIndex;
import com.raoqiang.homecredit.entry.ModelRequest;
import com.raoqiang.homecredit.entry.Params;
import com.raoqiang.homecredit.entry.Request;
import com.raoqiang.homecredit.entry.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ApplicationEvaluate {

    private static final Log LOG = LogFactory.getLog(ApplicationEvaluate.class);

    @Value("${model_url}")
    private String model_url;

    @Value("${phoenix_url}")
    private String phoenix_url;

    @Autowired
    RestTemplate restTemplate;

    public void kafkaConsumer(Map appData) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        HttpHeaders headers = getHttpHeaders();
//        // 读取 application
        Map appCondition = new HashMap();

        appCondition.put("SK_ID_CURR", appData.get("SK_ID_CURR")+"");
        LOG.info("组装 hbase 查询条件 -> " + appCondition);

        // ------------------------------------------------------------------------------
        // 读取 T_APPLICATION_AGG 数据，需要先计算 AGE_RANGE 值
        Calculate calculate = new AgeRange();
        calculate.labelCalculate(appData);
//        System.out.println(appData.get("AGE_RANGE"));
        Map appAggCondition = new HashMap();
        LOG.info("查询Application 聚合数据");
        appAggCondition.put("NAME_EDUCATION_TYPE",appData.get("NAME_EDUCATION_TYPE"));
        appAggCondition.put("OCCUPATION_TYPE",appData.get("OCCUPATION_TYPE"));
        appAggCondition.put("AGE_RANGE",appData.get("AGE_RANGE")+"");
        appAggCondition.put("CODE_GENDER",appData.get("CODE_GENDER"));
        appAggCondition.put("ORGANIZATION_TYPE",appData.get("ORGANIZATION_TYPE"));
        Response appAggResponse = getTD(restTemplate, headers, "T_APPLICATION_AGG", appAggCondition);

        // 合并 map
        if(appAggResponse.getData().size() == 1)
            appData.putAll(((Map)(appAggResponse.getData().get(0))));

        // --------------------------------------------------------------------------------
        // 字段处理
        // 处理 application_train/test
        String[] strs = new String[]{"DaysEmployed", "DaysLastPhoneChange", "ExtSourcesMax", "ExtSourcesMean", "ExtSourcesMin", "NewDocKurt",
                "ExtSourcesProd", "ExtSourcesWeighted", "AgeRange", "DocumentCount", "CreditToAnnuityRatio",
                "CreditToGoodsRatio", "AnnuityToIncomeRatio", "CreditToIncomeRatio","IncomeToBirthRatio",
                "IncomeToEmployedRatio", "EmployedToBirthRatio", "IdToBirthRatio", "CarToBirthRatio",
                "CarToEmployedRatio", "PhoneToBirthRatio", "CodeGender", "EmergencystateMode", "FlagOwnCar",
                "FlagOwnRealty", "FondkapremontMode", "HousetypeMode", "NameContractType", "NameEducationType",
                "NameFamilyStatus", "NameHousingType", "NameIncomeType", "NameTypeSuite", "OccupationType",
                "OrganizationType", "WallsmaterialMode", "WeekdayApprProcessStart", "ExtSourcesNanmedian",
                "ExtSourcesVar"
        };
        LOG.info("对申请数据进行清洗处理。 ");
        Class<?> clazz;
        for(String s: strs){
            clazz = Class.forName("com.raoqiang.homecredit.calculate.application."+s);
            calculate = (Calculate) clazz.newInstance();
            calculate.labelCalculate(appData);
        }
        // 移除栏位信息
        LOG.info("移除非必要栏位 -> " + Arrays.toString(DropIndex.APPLICATION_DROP_INDEX));
        GetValueFromMap.removeKeysFromMap(appData, DropIndex.APPLICATION_DROP_INDEX);


        // ------------------------------------------------------------------------------------
        // 添加 T_BUREAU_AGG特征
        LOG.info("查询获取 T_BUREAU_AGG 聚合数据");
        Response bureauAgg = getTD(restTemplate, headers, "T_BUREAU_AGG", appCondition);
        if(bureauAgg.getData().size() == 1)
            appData.putAll(((Map)(bureauAgg.getData().get(0))));


        // -----------------------------------------------------------------------------------
        // 添加 PREVIOUS_APPLICATIONS 聚合特征 T_PREVIOUS_AGG
        LOG.info("查询获取 T_PREVIOUS_AGG 聚合数据");
        Response previousAgg = getTD(restTemplate, headers, "T_PREVIOUS_AGG", appCondition);
        if(previousAgg.getData().size() == 1)
            appData.putAll(((Map)(previousAgg.getData().get(0))));

        // TODO TOTAL_REPAYMENT_RATIO 栏位错误  BUREAU_DEBT_OVER_CREDIT BUREAU_ACTIVE_DEBT_OVER_CREDIT

//        appData.remove("TOTAL_REPAYMENT_RATIO");
        appData.remove("BUREAU_DEBT_OVER_CREDIT");
        appData.remove("BUREAU_ACTIVE_DEBT_OVER_CREDIT");

        // -----------------------------------------------------------------------------------
        // 添加 POS_CASH 聚合特征 T_POS_AGG
        LOG.info("查询获取 T_POS_AGG 聚合数据");
        Response posCashAgg = getTD(restTemplate, headers, "T_POS_AGG", appCondition);
        if(posCashAgg.getData().size() == 1)
            appData.putAll(((Map)(posCashAgg.getData().get(0))));

        // -----------------------------------------------------------------------------------
        // 添加 INSTALLMENT_PAYMENTS 聚合特征 	T_INSTALLMENT_AGG
        LOG.info("查询获取 T_INSTALLMENT_AGG 聚合数据");
        Response insAgg = getTD(restTemplate, headers, "T_INSTALLMENT_AGG", appCondition);
        if(insAgg.getData().size() == 1)
            appData.putAll(((Map)(insAgg.getData().get(0))));

        // -----------------------------------------------------------------------------------
        // 添加 CREDIT_CARD 聚合特征 	T_CREDIT_CARD
        LOG.info("查询获取 T_CREDIT_CARD 聚合数据");
        Response creditAgg = getTD(restTemplate, headers, "T_CREDIT_CARD", appCondition);
        if(creditAgg.getData().size() == 1)
            appData.putAll(((Map)(creditAgg.getData().get(0))));


        LOG.info("特殊栏位映射调整。");
        Map t = new HashMap();
        for (Object s: appData.keySet()){
            t.put((s+"").replace("AABLAAA", "_"), appData.get(s));
        }
        appData = t;

        // -----------------------------------------------------------------------------------
        // 由于字段名特殊字符转换
        int co = 0;
        String[] tmp = StringUtils.standard.split("\n");
        Map newMap = new HashMap();
        List list = new LinkedList();

        for (String s:tmp){
            if (appData.containsKey(toHbase(s))){
                newMap.put(s, appData.get(toHbase(s)));
                list.add(s);
                co++;
            }else {
                newMap.put(s, null);
            }
        }
        int c = 0;

        for(Object key : appData.keySet()){
            if(appData.get(key)==null || appData.get(key).equals("NaN") || appData.get(key).equals("") || appData.get(key).equals("null")){
                appData.put(key, null);
            }else {
                c++;
            }

        }

        // 清洗，去除栏位为 空 / null / NaN
        for(Object key : newMap.keySet()){
            if(newMap.get(key)==null || newMap.get(key).equals("NaN") || newMap.get(key).equals("") || newMap.get(key).equals("null")){
                newMap.put(key, null);
            }

        }

        // --------------------------------------------------------------------------------
        // 字段处理 agg
        // 处理 application_train/test

        String[] strAgg = new String[]{"BureauActiveCreditToIncomeRatio", "BureauIncomeCreditRatio",
                "CtaCreditToAnnuityMaxRatio", "CtaCreditToAnnuityMeanRatio", "CurrentToApprovedAnnuityMaxRatio",
                "CurrentToApprovedAnnuityMeanRatio","CurrentToApprovedCreditMaxRatio", "CurrentToApprovedCreditMeanRatio",
                "CurrentToApprovedCreditMinRatio", "DaysCreditMeanToBirth", "DaysCreditMeanToEmployed", "DaysDecisionMeanToBirth",
                "DaysDecisionMeanToEmployed", "PaymentMaxToAnnuityRatio", "PaymentMeanToAnnuityRatio", "PaymentMinToAnnuityRatio"
        };
        LOG.info("再次进行数据处理");
        for(String s: strAgg){
            clazz = Class.forName("com.raoqiang.homecredit.calculate.aggRatio."+s);
            calculate = (Calculate) clazz.newInstance();
            calculate.labelCalculate(newMap);
        }
        // ------------------------------------------------------------------------------------
        // 评估结果

        Response result = evaluate(restTemplate, headers, newMap);
        LOG.info("评分结果 ->" + result);
        // TODO 结果数据入MySQL数据库处理
    }


    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }


    private Response getTD(RestTemplate restTemplate, HttpHeaders headers, String table, Map condition){
        Request request = new Request();
        Params param = new Params();
        param.setTableName(table);
        param.setCondition(condition);
        request.setParams(param);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(request), headers);
        LOG.info("发送请求 -> " + phoenix_url + ", data -> " + formEntity);
        return restTemplate.postForObject(phoenix_url, formEntity, Response.class);
    }

    private Response evaluate(RestTemplate restTemplate, HttpHeaders headers, Map para){
        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setParams(para);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(modelRequest), headers);
        LOG.info("发送请求 -> " + model_url + ", data -> " + formEntity);
        Response response = restTemplate.postForObject(model_url, formEntity, Response.class);
        return response;

    }

    private String toHbase(String resource){
        return resource.replace(" ", "AABLAAA")
                .replace(":", "AACOMAA")
                .replace(",", "AACOMAA")
                .replace("/", "AADEVAA")
                .replace("+", "AAADDAA")
                .replace("-", "AADECAA")
                .replace("(", "AALEFTAA")
                .replace(")", "AARIGHTAA").toUpperCase();
    }

}

