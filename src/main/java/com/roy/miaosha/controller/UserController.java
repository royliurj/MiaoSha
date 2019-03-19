package com.roy.miaosha.controller;

import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> goodsList(Model model, MiaoshaUser user){

        return Result.success(user);
    }
}
