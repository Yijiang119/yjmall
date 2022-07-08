package com.yijiang.mall.handler;

import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.po.MemberPO;
import com.yijiang.mall.service.api.MemberService;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MemberProviderHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 16:39
 * @Version 1.0
 */
@RestController
public class MemberProviderHandler {


    @Autowired
    private MemberService memberService;

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO) {
        try {
            memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(MallConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_EXIST);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/memberPO/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct) {
        try {
            // 1.调用本地 Service 完成查询 
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginAcct);
            // 2.如果没有抛异常，那么就返回成功的结果
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            // 3.如果捕获到异常则返回失败的结果 
            return ResultEntity.failed(e.getMessage());
        }

    }

}
