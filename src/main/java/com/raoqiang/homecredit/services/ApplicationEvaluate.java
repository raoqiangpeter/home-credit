package com.raoqiang.homecredit.services;

import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.StringUtils;
import com.raoqiang.homecredit.calculate.application.AgeRange;
import com.raoqiang.homecredit.calculate.constant.DropIndex;
import com.raoqiang.homecredit.dao.HcResultDao;
import com.raoqiang.homecredit.dao.HcStreamDao;
import com.raoqiang.homecredit.entry.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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

    @Autowired
    private HcStreamDao hcStreamDao;

    @Autowired
    private HcResultDao hcResultDao;

    public void kafkaConsumer(Map appData){
        HttpHeaders headers = getHttpHeaders();
//        // 读取 application
//        System.out.println(appData.get("HC_ID"));
        // --------------------------流水记录-------------------------------------------- 申请开始
        String hcId = (String) appData.get("HC_ID");
        HcStream hcStream = new HcStream();
        hcStream.setHcId(hcId);
        hcStream.setHcStatus("A01"); // A01 -> 案件进去
        hcStream.setHcResult("P"); // P -> 处理中
        hcStreamDao.insertStream(hcStream);


        // ---------------------------封装查询条件
        Map appCondition = new HashMap();
        String skId = appData.get("SK_ID_CURR")+"";
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
        // --------------------第一次查询 hbase-----------------------
        hcStream.setHcStatus("H01");
        hcStreamDao.insertStream(hcStream);
        Response appAggResponse = null;
        try {
            appAggResponse = getTD(restTemplate, headers, "T_APPLICATION_AGG", appAggCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_APPLICATION_AGG  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }
        //
        // 合并 map
        if(appAggResponse!=null&&appAggResponse.getData().size() == 1)
            appData.putAll(((Map)(appAggResponse.getData().get(0))));

        // --------------------------------------------------------------------------------
        // 字段处理
        // 处理 application_train/test
        hcStream.setHcStatus("Y01");
        hcStreamDao.insertStream(hcStream);

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
            try {
                clazz = Class.forName("com.raoqiang.homecredit.calculate.application."+s);
                calculate = (Calculate) clazz.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                calculate = null;
                LOG.error("第一次执行特征计算异常 -> "+e.getMessage());
                hcStream.setHcResult("E");
            }
            if (calculate == null){
                continue;
            }
            calculate.labelCalculate(appData);
        }

        // 流程异常处理 -> 将流水设置为E -> 异常状态
        if ("E".equals(hcStream.getHcResult())){
            hcStreamDao.insertStream(hcStream);
            // 流程继续 -> 流水置为P
            hcStream.setHcResult("P");
        }
        // 移除栏位信息
        LOG.info("移除非必要栏位 -> " + Arrays.toString(DropIndex.APPLICATION_DROP_INDEX));
        GetValueFromMap.removeKeysFromMap(appData, DropIndex.APPLICATION_DROP_INDEX);



        // ------------------------------------------------------------------------------------
        // 添加 T_BUREAU_AGG特征
        hcStream.setHcStatus("H02");
        hcStreamDao.insertStream(hcStream);
        LOG.info("查询获取 T_BUREAU_AGG 聚合数据");
        Response bureauAgg =null;
        try {
            bureauAgg = getTD(restTemplate, headers, "T_BUREAU_AGG", appCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_BUREAU_AGG  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }

        if(bureauAgg!=null&&bureauAgg.getData().size() == 1)
            appData.putAll(((Map)(bureauAgg.getData().get(0))));


        // -----------------------------------------------------------------------------------
        // 添加 PREVIOUS_APPLICATIONS 聚合特征 T_PREVIOUS_AGG
        hcStream.setHcStatus("H03");
        hcStreamDao.insertStream(hcStream);
        LOG.info("查询获取 T_PREVIOUS_AGG 聚合数据");
        Response previousAgg = null;
        try {
            previousAgg = getTD(restTemplate, headers, "T_PREVIOUS_AGG", appCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_PREVIOUS_AGG  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }

        if(previousAgg!=null&&previousAgg.getData().size() == 1)
            appData.putAll(((Map)(previousAgg.getData().get(0))));

        // TODO TOTAL_REPAYMENT_RATIO 栏位错误  BUREAU_DEBT_OVER_CREDIT BUREAU_ACTIVE_DEBT_OVER_CREDIT

//        appData.remove("TOTAL_REPAYMENT_RATIO");
        appData.remove("BUREAU_DEBT_OVER_CREDIT");
        appData.remove("BUREAU_ACTIVE_DEBT_OVER_CREDIT");

        // -----------------------------------------------------------------------------------
        // 添加 POS_CASH 聚合特征 T_POS_AGG
        hcStream.setHcStatus("H04");
        hcStreamDao.insertStream(hcStream);
        LOG.info("查询获取 T_POS_AGG 聚合数据");
        Response posCashAgg=null;
        try {
            posCashAgg = getTD(restTemplate, headers, "T_POS_AGG", appCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_POS_AGG  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }

        if(posCashAgg!=null&&posCashAgg.getData().size() == 1)
            appData.putAll(((Map)(posCashAgg.getData().get(0))));

        // -----------------------------------------------------------------------------------
        // 添加 INSTALLMENT_PAYMENTS 聚合特征 	T_INSTALLMENT_AGG
        hcStream.setHcStatus("H05");
        hcStreamDao.insertStream(hcStream);
        LOG.info("查询获取 T_INSTALLMENT_AGG 聚合数据");
        Response insAgg=null;
        try {
            insAgg = getTD(restTemplate, headers, "T_INSTALLMENT_AGG", appCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_INSTALLMENT_AGG  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }

        if(insAgg!=null&&insAgg.getData().size() == 1)
            appData.putAll(((Map)(insAgg.getData().get(0))));

        // -----------------------------------------------------------------------------------
        // 添加 CREDIT_CARD 聚合特征 	T_CREDIT_CARD
        hcStream.setHcStatus("H06");
        hcStreamDao.insertStream(hcStream);
        LOG.info("查询获取 T_CREDIT_CARD 聚合数据");
        Response creditAgg = null;
        try {
            creditAgg = getTD(restTemplate, headers, "T_CREDIT_CARD", appCondition);
        }catch (RestClientException e){
            LOG.error("执行查询 T_CREDIT_CARD  失败 -> " + e.getMessage());
            hcStream.setHcResult("E");
            hcStreamDao.insertStream(hcStream);
            // 继续执行
            hcStream.setHcResult("P");
        }

        if(creditAgg!=null&&creditAgg.getData().size() == 1)
            appData.putAll(((Map)(creditAgg.getData().get(0))));


        LOG.info("特殊栏位映射调整。");
        Map t = new HashMap();
        for (Object s: appData.keySet()){
            t.put((s+"").replace("AABLAAA", "_"), appData.get(s));
        }
        appData = t;

        // -----------------------------------------------------------------------------------
        // 由于字段名特殊字符转换
        String[] tmp = StringUtils.standard.split("\n");
        Map newMap = new HashMap();
        List list = new LinkedList();

        for (String s:tmp){
            if (appData.containsKey(toHbase(s))){
                newMap.put(s, appData.get(toHbase(s)));
                list.add(s);
            }else {
                newMap.put(s, null);
            }
        }

        // 清洗，去除栏位为 空 / null / NaN
        mapUtils(appData);
        mapUtils(newMap);

        // --------------------------------------------------------------------------------
        // 字段处理 agg
        // 处理 application_train/test
        hcStream.setHcStatus("Y02");
        hcStreamDao.insertStream(hcStream);
        String[] strAgg = new String[]{"BureauActiveCreditToIncomeRatio", "BureauIncomeCreditRatio",
                "CtaCreditToAnnuityMaxRatio", "CtaCreditToAnnuityMeanRatio", "CurrentToApprovedAnnuityMaxRatio",
                "CurrentToApprovedAnnuityMeanRatio","CurrentToApprovedCreditMaxRatio", "CurrentToApprovedCreditMeanRatio",
                "CurrentToApprovedCreditMinRatio", "DaysCreditMeanToBirth", "DaysCreditMeanToEmployed", "DaysDecisionMeanToBirth",
                "DaysDecisionMeanToEmployed", "PaymentMaxToAnnuityRatio", "PaymentMeanToAnnuityRatio", "PaymentMinToAnnuityRatio"
        };
        LOG.info("再次进行数据处理");
        for(String s: strAgg){
            try {
                clazz = Class.forName("com.raoqiang.homecredit.calculate.aggRatio."+s);
                calculate = (Calculate) clazz.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                calculate = null;
                LOG.error("第二次执行特征计算异常 -> "+e.getMessage());
                hcStream.setHcResult("E");
            }
            if (calculate == null){
                continue;
            }
            calculate.labelCalculate(appData);
        }

        // 流程异常处理 -> 将流水设置为E -> 异常状态
        if ("E".equals(hcStream.getHcResult())){
            hcStreamDao.insertStream(hcStream);
            // 流程继续 -> 流水置为P
            hcStream.setHcResult("P");
        }

        // ------------------------------------------------------------------------------------
        // 评估结果

        hcStream.setHcStatus("M01");
        hcStreamDao.insertStream(hcStream);
        Response result;
        HcResult hcResult = new HcResult();
        hcResult.setHcId(hcId);
        hcResult.setHcSkId(skId);
        try {
            result = evaluate(restTemplate, headers, newMap);
            hcResult.setHcScore(new BigDecimal(((Map)result.getData().get(0)).get("1")+""));
            hcStream.setHcResult("A"); // A -> 结束

        }catch (RestClientException e){
            LOG.error("执行模型评估失败 -> " + e.getMessage());
            result = new Response();
            result.setSuccess(false);
            result.setMessage("执行模型评估失败");
            hcResult.setHcScore(new BigDecimal("-1"));
            hcStream.setHcResult("E"); // E -> 异常
        }

        LOG.info("评分结果 ->" + result);
        hcStream.setHcStatus("A99");
        hcStreamDao.insertStream(hcStream);
        hcResultDao.insertResult(hcResult);


    }


    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }


    private Response getTD(RestTemplate restTemplate, HttpHeaders headers, String table, Map condition) throws RestClientException{
        Request request = new Request();
        Params param = new Params();
        param.setTableName(table);
        param.setCondition(condition);
        request.setParams(param);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(request), headers);
        LOG.info("发送请求 -> " + phoenix_url + ", data -> " + formEntity);
        return restTemplate.postForObject(phoenix_url, formEntity, Response.class);
    }

    private Response evaluate(RestTemplate restTemplate, HttpHeaders headers, Map para) throws RestClientException{
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

    private void mapUtils(Map appData){
        for(Object key : appData.keySet()){
            if(appData.get(key)==null || appData.get(key).equals("NaN") || appData.get(key).equals("") || appData.get(key).equals("null")){
                appData.put(key, null);
            }
        }
    }

}

