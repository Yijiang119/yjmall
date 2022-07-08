package com.yijiang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MallMainClassOrder
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 18:45
 * @Version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class MallMainClassOrder {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassOrder.class,args);
    }
}
