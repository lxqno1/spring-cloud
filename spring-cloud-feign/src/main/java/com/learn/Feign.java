package com.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching //开启缓存注解
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients // 消费者
public class Feign {

    public static void main(String[] args) {
        SpringApplication.run(Feign.class, args);
    }

}
