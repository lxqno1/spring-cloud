package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCaching //开启缓存注解
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients // 消费者
@EnableHystrix //断路器
@EnableHystrixDashboard // 开启数据监控注解
public class Rest {

    public static void main(String[] args) {
        System.out.println("testgit");
        SpringApplication.run(Rest.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){ return new RestTemplate();}
}
