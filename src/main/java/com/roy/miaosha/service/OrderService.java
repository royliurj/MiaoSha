package com.roy.miaosha.service;

import com.roy.miaosha.dao.GoodsDao;
import com.roy.miaosha.dao.OrderDao;
import com.roy.miaosha.domain.MiaoshaOrder;
import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.domain.OrderInfo;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {


    @Autowired
    OrderDao orderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
    }

    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);//未支付
        orderInfo.setUserId(user.getId());

        long orderId = orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);

        return null;
    }
}
