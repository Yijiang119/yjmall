package com.yijiang.mall.service.api;

import com.yijiang.mall.entity.po.MemberPO;

/**
 * @InterfaceName MemberService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 16:42
 * @Version 1.0
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);
}
