package com.yijiang.mall.service.api;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Admin;

import java.util.List;

/**
 * @InterfaceName AdminService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/19 19:20
 * @Version 1.0
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId, Admin loginUser);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct(String username);
}
