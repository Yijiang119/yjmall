package com.yijiang.mall.service.api;

import com.yijiang.mall.entity.vo.AddressVO;
import com.yijiang.mall.entity.vo.OrderProjectVO;
import com.yijiang.mall.entity.vo.OrderVO;

import java.util.List;

/**
 * @InterfaceName OrderService
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 19:01
 * @Version 1.0
 */
public interface OrderService {
    OrderProjectVO getOrderProjectVO(Integer returnId);

    List<AddressVO> getAddressListVOByMemberId(Integer memberId);

    void saveAddressPO(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
