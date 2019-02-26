package com.roy.miaosha.controller;

import com.roy.miaosha.domain.MiaoshaOrder;
import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.domain.OrderInfo;
import com.roy.miaosha.result.CodeMsg;
import com.roy.miaosha.service.GoodsService;
import com.roy.miaosha.service.MiaoshaService;
import com.roy.miaosha.service.OrderService;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @PostMapping("/do_miaosha")
    public String miaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId){

        model.addAttribute("user",user);

        if(user == null){
            return "login";
        }

        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if(stockCount<=0){
            model.addAttribute("errmsg",CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否已经秒杀到了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(miaoshaOrder != null){
            model.addAttribute("errmsg", CodeMsg.REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存、下订单、写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goodsVo);

        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goodsVo);

        return "order_detail";

    }
}
