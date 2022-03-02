package com.luban;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动入口
 * Created on 2022/2/24.
 */
@Slf4j
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        log.warn("auth-server开始启动！");
        SpringApplication.run(AuthApplication.class, args);
        log.warn("auth-server启动结束！");
    }
}
