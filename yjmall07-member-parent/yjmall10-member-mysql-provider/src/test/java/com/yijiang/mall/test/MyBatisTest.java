package com.yijiang.mall.test;

import com.yijiang.mall.entity.po.MemberPO;
import com.yijiang.mall.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName MyBatisTest
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 15:59
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    private DataSource dataSource;

//    private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
    }

    @Test
    public void testMapper() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String source = "123123";
        String encode = passwordEncoder.encode(source);
        MemberPO memberPO = new MemberPO(null, "jack", encode, " 杰 克 ",
                "jack@qq.com", 1, 1, "杰克", "123123", 2);
        memberPOMapper.insert(memberPO);
    }


}
