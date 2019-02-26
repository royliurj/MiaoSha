package com.roy.miaosha.service;

import com.roy.miaosha.domain.Goods;
import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.domain.OrderInfo;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goodsVo) {
        //减库存
        goodsService.reduceStock(goodsVo);

        //下订单,写入秒杀订单
        OrderInfo orderInfo = orderService.createOrder(user, goodsVo);
        return orderInfo;

    }
}
