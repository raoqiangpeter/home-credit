package com.raoqiang.homecredit.kafkaListen;

import com.alibaba.fastjson.JSONObject;
import com.raoqiang.homecredit.entry.AppRequest;
import com.raoqiang.homecredit.services.ApplicationEvaluate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.lang.reflect.Type;

public class AppSubmitListen {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationEvaluate applicationEvaluate;

    private final static String TPOIC = "HC00_APPLICATION_SUBMIT";


//    @KafkaListener(topics = {"HC00_APPLICATION_SUBMIT"})
    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = TPOIC, partitions = { "1" }) })
    public void listen0(ConsumerRecord<?, ?> record) {
        listen(record);
    }


    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = TPOIC, partitions = { "0" }) })
    public void listen2(ConsumerRecord<?, ?> record) {
        listen(record);
    }



    private void listen(ConsumerRecord<?, ?> record){
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
        applicationEvaluate.kafkaConsumer(((AppRequest)(JSONObject.parseObject(record.value().toString(), (Type) AppRequest.class))).getParams());
        logger.info("kafka处理成功");
    }
}
