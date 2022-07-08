package com.yijiang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Member;
import com.yijiang.mall.exception.LoginAcctAlreadyInUseForUpdateException;
import com.yijiang.mall.mapper.MemberMapper;
import com.yijiang.mall.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MemberServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 20:14
 * @Version 1.0
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageInfo<Member> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Member> memberList = memberMapper.selectMemberListByKeyword(keyword);
        // 3.封装为 PageInfo 对象返回
        return new PageInfo<>(memberList);

    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public void update(Member member) {
        try {
            memberMapper.updateByPrimaryKeySelective(member);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(MallConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            throw e;
        }
    }
}
