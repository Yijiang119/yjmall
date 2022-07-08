package com.yijiang.mall.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Member;
import com.yijiang.mall.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName MemberHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 20:19
 * @Version 1.0
 */
@Controller
public class MemberHandler {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/member/get/page.html")
    public String getAdminPage(
            @RequestParam(value="keyword", defaultValue="") String keyword,
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
            ModelMap modelMap
    ) {
        PageInfo<Member> pageInfo = memberService.getPageInfo(pageNum,pageSize,keyword);
        modelMap.addAttribute(MallConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "member-page";
    }

    @RequestMapping("/member/to/edit/page")
    public String toEditPage(@RequestParam("memberId") Integer memberId, ModelMap modelMap) {
        // 1.根据 id（主键）查询待更新的 Admin 对象
        Member member = memberService.getMemberById(memberId);
        // 2.将 Admin 对象存入模型
        modelMap.addAttribute("member", member);
        return "member-edit";
    }

    @RequestMapping("/member/update.html")
    public String update(Member member, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword){
        memberService.update(member);
        return "redirect:/member/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }


}
