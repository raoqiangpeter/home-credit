package com.raoqiang.homecredit.kafkaListen;

import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.entry.AppRequest;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import java.lang.reflect.Type;

public class AppSubmitListen {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationEvaluate applicationEvaluate;


    @KafkaListener(topics = {"HC00_APPLICATION_SUBMIT"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
        try {
            applicationEvaluate.kafkaConsumer(((AppRequest)(JSONObject.parseObject(record.value().toString(), (Type) AppRequest.class))).getParams());
            logger.info("kafka处理成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
