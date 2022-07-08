package com.yijiang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName MallMainClass
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 15:11
 * @Version 1.0
 */

@EnableEurekaServer
@SpringBootApplication
public class MallMainClassEureka {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassEureka.class, args);
    }

}
