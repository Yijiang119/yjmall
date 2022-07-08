package com.yijiang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MallMainClassPay
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 19:30
 * @Version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class MallMainClassPay {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassPay.class,args);
    }
}
