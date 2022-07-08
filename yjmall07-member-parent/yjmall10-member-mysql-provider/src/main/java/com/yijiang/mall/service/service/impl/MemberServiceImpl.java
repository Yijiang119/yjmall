package com.yijiang.mall.service.service.impl;

import com.yijiang.mall.entity.po.MemberPO;
import com.yijiang.mall.entity.po.MemberPOExample;
import com.yijiang.mall.mapper.MemberPOMapper;
import com.yijiang.mall.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MemberServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 16:42
 * @Version 1.0
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {
        // 1.创建 Example 对象
        MemberPOExample example = new MemberPOExample();
        // 2.创建 Criteria 对象
        MemberPOExample.Criteria criteria = example.createCriteria();
        // 3.封装查询条件
        criteria.andLoginacctEqualTo(loginAcct);
        // 4.执行查询
        List<MemberPO> list = memberPOMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return null;
        }
        // 5.获取结果
        return list.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }

}
