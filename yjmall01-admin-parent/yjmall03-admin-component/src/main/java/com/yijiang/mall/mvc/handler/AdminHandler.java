package com.yijiang.mall.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Admin;
import com.yijiang.mall.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/21 10:44
 * @Version 1.0
 */
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;



    @RequestMapping("/admin/update.html")
    public String update(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword){
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        // 1.根据 id（主键）查询待更新的 Admin 对象
        Admin admin = adminService.getAdminById(adminId);
        // 2.将 Admin 对象存入模型
        modelMap.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin) {
        // 执行保存
        adminService.saveAdmin(admin);
        // 重定向到分页页面，使用重定向是为了避免刷新浏览器重复提交表单
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword,
            HttpSession session
    ) {
        // 执行删除
        Admin loginUser = (Admin) session.getAttribute(MallConstant.ATTR_NAME_LOGIN_ADMIN);
        adminService.remove(adminId, loginUser);
        // 页面跳转：回到分页页面
        // 尝试方案 1：直接转发到 admin-page.jsp 会无法显示分页数据
        // return "admin-page";
        // 尝试方案 2：转发到/admin/get/page.html 地址，一旦刷新页面会重复执行删除浪费性能
        // return "forward:/admin/get/page.html";
        // 尝试方案 3：重定向到/admin/get/page.html 地址
        // 同时为了保持原本所在的页面和查询关键词再附加 pageNum 和 keyword 两个请求参数
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/get/page.html")
    public String getAdminPage(
            // 注意：页面上有可能不提供关键词，要进行适配
            // 在@RequestParam注解中设置defaultValue属性为空字符串表示浏览器不提供关键词时，keyword 变量赋值为空字符串
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 浏览器未提供 pageNum 时，默认前往第一页
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 浏览器未提供 pageSize 时，默认每页显示 5 条记录
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap
    ) {
        // 查询得到分页数据
        PageInfo<Admin> pageInfo = adminService.getAdminPage(keyword, pageNum, pageSize);
        // 将分页数据存入模型
        modelMap.addAttribute(MallConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        // 强制 Session 失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }


    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session
    ) {
        // 调用 Service 方法执行登录检查
        // 这个方法如果能够返回 admin 对象说明登录成功，如果账号、密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        // 将登录成功返回的 admin 对象存入 Session 域
        session.setAttribute(MallConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

}
