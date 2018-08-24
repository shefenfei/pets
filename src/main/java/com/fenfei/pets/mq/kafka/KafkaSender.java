package com.fenfei.pets.mq.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendChannelMessage(String topic, String message) {
        kafkaTemplate.send(topic ,"she-key",  message);
    }
}
