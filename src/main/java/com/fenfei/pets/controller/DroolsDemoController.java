package com.fenfei.pets.controller;

import com.fenfei.pets.drools.DroolRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
