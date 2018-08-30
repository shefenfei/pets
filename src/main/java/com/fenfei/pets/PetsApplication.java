package com.fenfei.pets;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients
//开启aop支持
@EnableAspectJAutoProxy
//开启springbatch 支持
@EnableBatchProcessing
@MapperScan(basePackages = "com.fenfei.pets.dao")
@Slf4j
public class PetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsApplication.class, args);
        log.info("PetsApplication" , "开始......");
    }
}
