package com.yijiang.mall.api;

import com.yijiang.mall.entity.po.MemberPO;
import com.yijiang.mall.entity.vo.*;
import com.yijiang.mall.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName MySQLRemoteService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 16:35
 * @Version 1.0
 */
@FeignClient("yijiang-mall-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/get/memberPO/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);


    @RequestMapping("/save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/portal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping("/get/detail/project/remote/{projectId}")
    ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

    @RequestMapping("/get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVO(@RequestParam("returnId") Integer returnId);

    @RequestMapping("/get/address/list/by/member/id/remote")
    ResultEntity<List<AddressVO>> getAddressListByMemberIdRemote(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/save/address/remote")
    ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);

    @RequestMapping("save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);
}
