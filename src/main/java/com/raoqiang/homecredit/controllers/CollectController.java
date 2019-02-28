package com.raoqiang.homecredit.controllers;


import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.entry.AppRequest;
import com.raoqiang.homecredit.entry.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@RestController
@RequestMapping("/home-credit")
public class CollectController {

    @Value("${kafka.hc.top}")
    private String top;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/appSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Response appSubmit(@RequestBody AppRequest appInfo) {
        Date now = new Date( );
        int a = new Random().nextInt(8999) + 1000;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        String hcId = ft.format(now) + a + 1;
        appInfo.getParams().put("HC_ID", hcId);
        try {
            kafkaTemplate.send(top, JSONObject.toJSONString(appInfo));
            logger.info("发送kafka成功."+appInfo);
            return new Response(1, "处理成功", true);
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return new Response(0, "发送kafka失败", false);
        }
    }
}
