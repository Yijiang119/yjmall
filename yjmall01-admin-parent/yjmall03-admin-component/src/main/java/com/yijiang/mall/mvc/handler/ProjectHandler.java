package com.yijiang.mall.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Project;
import com.yijiang.mall.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ProjectHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 21:21
 * @Version 1.0
 */
@Controller
public class ProjectHandler {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/project/get/page.html")
    public String getAdminPage(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap
    ) {
        PageInfo<Project> pageInfo = projectService.getPageInfo(pageNum, pageSize, keyword);
        modelMap.addAttribute(MallConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "project-page";
    }

    @RequestMapping("project/to/agree/page.html")
    public String agreeProject(@RequestParam("projectId") Integer projectId, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword) {
        projectService.agreeProject(projectId);
        return "redirect:/project/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
