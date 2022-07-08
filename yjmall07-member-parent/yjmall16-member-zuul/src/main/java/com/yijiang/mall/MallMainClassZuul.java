package com.yijiang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName MallMainClassZuul
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 17:59
 * @Version 1.0
 */
@EnableZuulProxy
@SpringBootApplication
public class MallMainClassZuul {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassZuul.class, args);
    }


}
