package com.yijiang.mall.service.api;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Role;

import java.util.List;

/**
 * @InterfaceName RoleService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/22 11:26
 * @Version 1.0
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
