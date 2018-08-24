package com.fenfei.pets.mq.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"shefenfei"})
    public void receiveMessage(String message) {
        System.out.println(message);
    }
}
