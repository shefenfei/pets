package com.fenfei.pets.controller;

import com.fenfei.pets.drools.DroolRuleService;
import com.fenfei.pets.models.CustomTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * drools demo controller
 */
@RestController
@RequestMapping("/drools")
public class DroolsDemoController {

    @Autowired
    private DroolRuleService droolRuleService;


    @GetMapping("/testDrools")
    public String testDrools(String message , Integer status) {
        return droolRuleService.findRule(message, status);
    }

    @GetMapping("/queryTemById")
    public Map<String, Object> queryTemplateByEventId(Integer id) {
        Map<String, Object> resp = new HashMap<>();
        CustomTemplate template = droolRuleService.queryById(id);
        resp.put("data" , template);
        return resp;
    }
}
