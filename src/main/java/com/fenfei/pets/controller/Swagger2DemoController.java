package com.fenfei.pets.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/swagger2")
@Api(value = "swagger2 Demo" , tags = "swagger2")
public class Swagger2DemoController {

    @ApiOperation(value = "/swaggerGet" , httpMethod = "POST")
    @RequestMapping(value = "/swaggerGet" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String , Object> swaggerGet(
            @ApiParam(value = "username" , required = true , example = "shefenfei") String username ,
            @ApiParam(value = "password" , required = true , example = "123456") String password ) {
        return null;
    }
}
