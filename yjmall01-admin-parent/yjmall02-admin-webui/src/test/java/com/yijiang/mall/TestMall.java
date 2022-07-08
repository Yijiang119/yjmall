package com.yijiang.mall;

import com.yijiang.mall.entity.Admin;
import com.yijiang.mall.entity.Role;
import com.yijiang.mall.mapper.AdminMapper;
import com.yijiang.mall.mapper.RoleMapper;
import com.yijiang.mall.service.api.AdminService;
import com.yijiang.mall.service.api.RoleService;
import com.yijiang.mall.util.MallUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TestMall
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/19 17:08
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class TestMall {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void insertRoleData(){
        for (int i = 0; i < 119; i++) {
            roleMapper.insert(new Role(null, "role" + i));
        }
    }

    @Test
    public void insertAdminData(){
        for (int i = 0; i < 119; i++) {
            adminMapper.insert(new Admin(null, "loaginAcct" + i, "userPswd" + i, "userName" + i, "111", null));
        }
    }

    @Test
    public void testMD5(){
        String resource = "710422";
        String encoded = MallUtil.md5(resource);
        System.out.println(encoded);
    }

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "txw", "710422", "田曦薇", "txw@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testLog(){
        // 获取日志记录对象
        Logger logger = LoggerFactory.getLogger(TestMall.class);
        // 按照 Debug 级别打印日志
        logger.debug("debug Level");
        logger.debug("debug Level");
        logger.debug("debug Level");
        // 按照 Info 级别打印日志
        logger.info("info Level");
        logger.info("info Level");
        logger.info("info Level");
        // 按照 Warn 级别打印日志
        logger.warn("warn Level");
        logger.warn("warn Level");
        logger.warn("warn Level");
        // 按照 Error 级别打印日志
        logger.error("error Level");
        logger.error("error Level");
        logger.error("error Level");
    }

    @Test
    public void testInsert() {
        String resource = "119119";
        Admin admin = new Admin(null, "zls23", resource, "赵露思1", "1606280583@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testDataSource() throws SQLException {
        // 1.通过数据源对象获取数据源连接
        Connection connection = dataSource.getConnection();
        // 2.打印数据库连接
        System.out.println(connection);
    }

}
