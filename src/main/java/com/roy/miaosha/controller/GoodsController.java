package com.roy.miaosha.controller;

import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;

    @GetMapping("/to_list")
    public String goodsList(Model model, HttpServletResponse response, MiaoshaUser user){


        model.addAttribute("user", user);
        return "goods_list";
    }

//    @GetMapping("/to_detail")
//    public String detail(Model model, HttpServletResponse response,
//                            @CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
//                            @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String paramToken){
//        return "";
//    }
}
