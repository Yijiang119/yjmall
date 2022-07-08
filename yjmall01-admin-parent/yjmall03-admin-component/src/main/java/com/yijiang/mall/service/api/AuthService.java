package com.yijiang.mall.service.api;

import com.yijiang.mall.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuthService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/23 16:23
 * @Version 1.0
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);

}
