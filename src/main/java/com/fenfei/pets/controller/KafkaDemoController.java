package com.fenfei.pets.controller;

import com.fenfei.pets.mq.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class KafkaDemoController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("send")
    public Map<String , String> send(String message) {
        Map<String , String> resp = new HashMap<>();
        kafkaSender.sendChannelMessage("shefenfei" , message);
        resp.put("data" , "success");
        return resp;
    }
}
