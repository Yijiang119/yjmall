package com.yijiang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Role;
import com.yijiang.mall.entity.RoleExample;
import com.yijiang.mall.mapper.RoleMapper;
import com.yijiang.mall.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/22 11:26
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        // 3.封装为 PageInfo 对象返回
        return new PageInfo<>(roleList);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        //delete from t_role where id in (5,8,12)
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(example);
        //删除角色关联的权限,和inner_admin_role中关联该角色的字段
        for (int i = 0; i < roleIdList.size(); i++) {
            roleMapper.deleteOldRelationship(roleIdList.get(i));
            roleMapper.deleteInInnerAdminRole(roleIdList.get(i));
        }
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }
}
