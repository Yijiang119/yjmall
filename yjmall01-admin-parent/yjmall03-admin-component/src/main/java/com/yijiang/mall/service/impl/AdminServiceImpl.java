package com.yijiang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Admin;
import com.yijiang.mall.entity.AdminExample;
import com.yijiang.mall.exception.*;
import com.yijiang.mall.mapper.AdminMapper;
import com.yijiang.mall.service.api.AdminService;
import com.yijiang.mall.util.MallUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/19 19:20
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveAdmin(Admin admin) {
        // 生成当前系统时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);
        // 针对登录密码进行加密
        String sourcePassword = admin.getUserPswd();
        //String encoded = MallUtil.md5(source);
        String encodedPassword = bCryptPasswordEncoder.encode(sourcePassword);
        admin.setUserPswd(encodedPassword);
        // 执行保存，如果账户被占用会抛出异常
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 类型说明是账号重复导致的
            if (e instanceof DuplicateKeyException) {
                // 抛出自定义的 LoginAcctAlreadyInUseException 异常
                throw new LoginAcctAlreadyInUseForAddException(MallConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            // 为了不掩盖问题，如果当前捕获到的不是 DuplicateKeyException 类型的异常，则把当前捕获到的异常对象继续向上抛出
            throw e;
        }
    }

    @Override
    public List<Admin> getAll() {
        //给一个空的不带条件就是查询全部
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询 Admin 对象
        // ①创建 AdminExample 对象
        AdminExample adminExample = new AdminExample();
        // ②创建 Criteria 对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // ③在 Criteria 对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // ④调用 AdminMapper 的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        // 2.判断 Admin 对象是否为 null
        if (list == null || list.size() == 0) {
            throw new LoginFailedException(MallConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size() > 1) {
            throw new RuntimeException(MallConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = list.get(0);
        // 3.如果 Admin 对象为 null 则抛出异常
        if (admin == null) {
            throw new LoginFailedException(MallConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果 Admin 对象不为 null 则将数据库密码从 Admin 对象中取出
        String userPswdDB = admin.getUserPswd();
        // 5.将表单提交的明文密码进行加密
        String userPswdForm = MallUtil.md5(userPswd);
        // 6.对密码进行比较
        if (!Objects.equals(userPswdDB, userPswdForm)) {
            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(MallConstant.MESSAGE_LOGIN_FAILED);
        }
        // 8.如果一致则返回 Admin 对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.查询 Admin 数据
        List<Admin> adminList = adminMapper.selectAdminListByKeyword(keyword);
        // ※辅助代码：打印 adminList 的全类名
        Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
        logger.debug("adminList 的全类名是：" + adminList.getClass().getName());
        // 3.为了方便页面使用将 adminList 封装为 PageInfo
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return pageInfo;
    }

    @Override
    public void remove(Integer adminId, Admin loginUser) {
        if (loginUser != null) {
            if (loginUser.getId() == adminId) {
                throw new DeleteException(MallConstant.MESSAGE_DELEDTE_FAILED);
            } else {
                //删除用户关联的角色
                adminMapper.deleteOLdRelationship(adminId);
                adminMapper.deleteByPrimaryKey(adminId);
            }
        } else {
            throw new AccessForbiddenException(MallConstant.MESSAGE_ACCESS_FORBIDEN);
        }
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void update(Admin admin) {
        //"Selective"表示有选择的更新，对null值的字段不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(MallConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            throw e;
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        // 1.根据 adminId 删除旧的关联关系数据
        adminMapper.deleteOLdRelationship(adminId);
        // 2.根据 roleIdList 和 adminId 保存新的关联关系
        if (roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }

    }

    @Override
    public Admin getAdminByLoginAcct(String username) {
        if (username == null && username.length() == 0) {
            throw new RuntimeException(MallConstant.MESSAGE_STRING_INVALIDATE);
        }
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        Admin admin = list.get(0);
        return admin;
    }
}
