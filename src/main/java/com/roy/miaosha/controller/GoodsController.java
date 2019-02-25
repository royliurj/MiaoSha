package com.roy.miaosha.controller;

import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.service.GoodsService;
import com.roy.miaosha.service.MiaoshaUserService;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/to_list")
    public String goodsList(Model model, HttpServletResponse response, MiaoshaUser user){

        List<GoodsVo> list = goodsService.listGoodsVo();

        model.addAttribute("goodsList", list);
        return "goods_list";
    }


    @GetMapping("/to_detail/{id}")
    public String goodsDetail(Model model, MiaoshaUser user, @PathVariable("id") Long id){

        GoodsVo goodsVo = goodsService.getGoodsVoById(id);

        //snowflake

        long startTime = goodsVo.getStartDate().getTime();
        long endTime = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if(now < startTime){
            //没有开始
            miaoshaStatus = 0;
            remainSeconds = (int)((startTime - now)/1000);

        } else if(now > endTime){
            //已经结束
            miaoshaStatus = 2;
            remainSeconds = 0;

        } else {
            //正在进行
            miaoshaStatus = 1;
            remainSeconds = 0;
        }


        model.addAttribute("goods", goodsVo);
        model.addAttribute("user", user);
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_detail";
    }



//    @GetMapping("/to_detail")
//    public String detail(Model model, HttpServletResponse response,
//                            @CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
//                            @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String paramToken){
//        return "";
//    }
}
