package com.luban;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: 伯乐
 * @Date: 2020/6/11 20:54
 */
@Slf4j
@SpringBootApplication
public class ResourceOrderApplication {
    public static void main(String[] args) {
        log.warn("resource-order开始启动！");
        SpringApplication.run(ResourceOrderApplication.class,args);
        log.warn("resource-order启动结束！");
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();

    }
}
