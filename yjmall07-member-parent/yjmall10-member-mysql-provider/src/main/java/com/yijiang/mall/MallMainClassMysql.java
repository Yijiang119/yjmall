package com.yijiang.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName MallMainClass
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 15:11
 * @Version 1.0
 */
@MapperScan("com.yijiang.mall.mapper")
@SpringBootApplication
public class MallMainClassMysql {

    public static void main(String[] args) {
        SpringApplication.run(MallMainClassMysql.class, args);
    }

}
