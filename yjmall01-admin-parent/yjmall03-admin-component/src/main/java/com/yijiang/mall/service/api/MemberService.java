package com.yijiang.mall.service.api;

import com.github.pagehelper.PageInfo;
import com.yijiang.mall.entity.Member;


/**
 * @ClassName MemberService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/31 20:14
 * @Version 1.0
 */
public interface MemberService {

    PageInfo<Member> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    Member getMemberById(Integer memberId);

    void update(Member member);
}
