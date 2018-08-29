package com.fenfei.pets.controller;

import com.fenfei.pets.models.Image;
import com.fenfei.pets.mq.kafka.KafkaSender;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kafka")
@Api(value = "Kafka样例Controller" , tags = {"用户操作接口"})
public class KafkaDemoController {

    @Autowired
    private KafkaSender kafkaSender;

    @ApiParam(name = "message")
    @ApiOperation(value = "send", httpMethod = "get", nickname = "getGreeting")
    @GetMapping("send")
    public Map<String, String> send(String message) {
        Map<String, String> resp = new HashMap<>();
        kafkaSender.sendChannelMessage("shefenfei", message);
        resp.put("data", "success");
        return resp;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Map.class, responseContainer = "List")})
    @PostMapping("sendList")
    public Map<String, String> sendList(@RequestBody List<Image> images) {
        Map<String, String> resp = new HashMap<>();
        images.forEach(System.out::println);


        List<Image> others = new ArrayList<>();
        BeanUtils.copyProperties(images, others);

//        kafkaSender.sendChannelMessage("shefenfei" , message);
        resp.put("data", "success");
        return resp;
    }
}
