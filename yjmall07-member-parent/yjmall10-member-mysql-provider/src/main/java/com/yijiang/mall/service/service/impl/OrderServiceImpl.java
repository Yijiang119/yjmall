package com.yijiang.mall.service.service.impl;

import com.yijiang.mall.entity.po.AddressPO;
import com.yijiang.mall.entity.po.AddressPOExample;
import com.yijiang.mall.entity.po.OrderPO;
import com.yijiang.mall.entity.po.OrderProjectPO;
import com.yijiang.mall.entity.vo.AddressVO;
import com.yijiang.mall.entity.vo.OrderProjectVO;
import com.yijiang.mall.entity.vo.OrderVO;
import com.yijiang.mall.mapper.AddressPOMapper;
import com.yijiang.mall.mapper.OrderPOMapper;
import com.yijiang.mall.mapper.OrderProjectPOMapper;
import com.yijiang.mall.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 19:02
 * @Version 1.0
 */
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;


    @Override
    public OrderProjectVO getOrderProjectVO(Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAddressListVOByMemberId(Integer memberId) {

        AddressPOExample example = new AddressPOExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);

        List<AddressVO> addressVOList = new ArrayList<>();
        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO,addressVO);
            addressVOList.add(addressVO);
        }

        return addressVOList;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveAddressPO(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);
        addressPOMapper.insert(addressPO);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {
        // 创建OrderPO对象
        OrderPO orderPO = new OrderPO();
        // 从传入的OrderVO给OrderPO赋值
        BeanUtils.copyProperties(orderVO,orderPO);
        // 将OrderPO存入数据库
        orderPOMapper.insert(orderPO);
        // 得到存入后自增产生的order id
        Integer orderId = orderPO.getId();
        // 得到orderProjectVO
        OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
        // 创建OrderProjectPO对象
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        // 赋值
        BeanUtils.copyProperties(orderProjectVO,orderProjectPO);
        // 给orderProjectPO设置orderId
        orderProjectPO.setOrderId(orderId);
        // 存入数据库
        orderProjectPOMapper.insert(orderProjectPO);
    }

}
