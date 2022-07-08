package com.yijiang.mall.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName test
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 17:06
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSet() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("apple", "red");
    }


}
