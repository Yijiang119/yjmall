package com.yijiang.mall.handler;

import com.yijiang.mall.api.MySQLRemoteService;
import com.yijiang.mall.api.RedisRemoteService;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.po.MemberPO;
import com.yijiang.mall.entity.vo.MemberLoginVO;
import com.yijiang.mall.entity.vo.MemberVO;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MemberHandler
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 19:04
 * @Version 1.0
 */
@Controller
public class MemberHandler {

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:http://localhost/";
    }

    @RequestMapping("/auth/member/do/login")
    public String login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        ModelMap modelMap,
                        HttpSession session) {
        ResultEntity<MemberPO> resultEntity = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        if (ResultEntity.FAILED.equals(resultEntity.getOperationResult())) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, resultEntity.getOperationMessage());
            return "member-login";
        }
        MemberPO queryData = resultEntity.getQueryData();
        if (queryData == null) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, MallConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        String userpswdByDB = queryData.getUserpswd();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //要用matches方法去比较，因为盐值加密每次加密的结果都不一样
        if (!bCryptPasswordEncoder.matches(userpswd, userpswdByDB)) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, MallConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        MemberLoginVO memberLoginVO = new MemberLoginVO(queryData.getUsername(), queryData.getEmail(), queryData.getId());
        session.setAttribute(MallConstant.ATTR_NAME_LOGIN_MEMBER, memberLoginVO);
        return "redirect:http://localhost/auth/member/to/center/page.html";
    }

    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO, ModelMap modelMap) {
        String phoneNum = memberVO.getPhoneNum();
        String key = MallConstant.REDIS_CODE_PREFIX + phoneNum;
        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);
        String result = resultEntity.getOperationResult();
        if (ResultEntity.FAILED.equals(result)) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, resultEntity.getOperationMessage());
            return "member-reg";
        }
        String redisCode = resultEntity.getQueryData();
        if (redisCode == null) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, MallConstant.MESSAGE_CODE_NOT_EXIST);
            return "member-reg";
        }
        String formCode = memberVO.getCode();
        if (!Objects.equals(redisCode, formCode)) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, MallConstant.MESSAGE_CODE_ERROR);
            return "member-reg";
        }
        redisRemoteService.removeRedisKeyRemote(key);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userpswd = memberVO.getUserpswd();
        String encodePassword = bCryptPasswordEncoder.encode(userpswd);
        memberVO.setUserpswd(encodePassword);
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO, memberPO);
        ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMember(memberPO);
        if (ResultEntity.FAILED.equals(saveMemberResultEntity.getOperationResult())) {
            modelMap.addAttribute(MallConstant.ATTR_NAME_MESSAGE, saveMemberResultEntity.getOperationMessage());
            return "member-reg";
        }
        return "redirect:http://localhost/auth/member/to/login/page.html";
    }

    @ResponseBody
    @RequestMapping("auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {
        // 因为只有公司或企业才能申请第三方短信接口，先用随机数生成验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        if (code == null || code.length() < 6) {
            return ResultEntity.failed(MallConstant.MESSAGE_CODE_INVALIDATE);
        }
        System.out.println(code);
        String key = MallConstant.REDIS_CODE_PREFIX + phoneNum;
        ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code.toString(), 2, TimeUnit.MINUTES);
        if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getOperationResult())) {
            return ResultEntity.successWithoutData();
        } else {
            return saveCodeResultEntity;
        }
    }

}
