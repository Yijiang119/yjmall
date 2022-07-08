package com.yijiang.mall.mvc.handler;

import com.yijiang.mall.entity.Admin;
import com.yijiang.mall.service.api.AdminService;
import com.yijiang.mall.util.MallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName TestHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/20 10:37
 * @Version 1.0
 */
@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String doTest(Model model, HttpServletRequest request) {

        boolean judgeRequestType = MallUtil.judgeRequestType(request);

//        Logger logger = Logger.getLogger("TestHandler.Class");
//        logger.info("judgeRequestType=" + judgeRequestType);
        System.out.println(judgeRequestType);

        List<Admin> adminList = adminService.getAll();
        model.addAttribute("adminList", adminList);
//        int m = 10/0;
        return "target";
    }

}
