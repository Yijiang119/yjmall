package com.yijiang.mall.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Role;
import com.yijiang.mall.service.api.RoleService;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName RoleHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/22 11:28
 * @Version 1.0
 */
@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity<String> removeByRoleIdAarry(@RequestBody List<Integer> roleIdList) {
        roleService.removeRole(roleIdList);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(Role role) {
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role) {
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        // 调用 Service 方法获取分页数据
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        // 封装到 ResultEntity 对象中返回（如果上面的操作抛出异常，交给异常映射机制处理）
        return ResultEntity.successWithData(pageInfo);
    }

}
