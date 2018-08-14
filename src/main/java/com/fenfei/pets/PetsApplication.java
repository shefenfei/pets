package com.fenfei.pets;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.fenfei.pets.dao")
@Slf4j
public class PetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsApplication.class, args);
        log.info("PetsApplication" , "开始......");
    }
}
