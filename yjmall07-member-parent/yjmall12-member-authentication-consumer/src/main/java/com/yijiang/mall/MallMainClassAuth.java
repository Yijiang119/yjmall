package com.yijiang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MallMainClass
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 15:11
 * @Version 1.0
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MallMainClassAuth {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassAuth.class, args);
    }

}
