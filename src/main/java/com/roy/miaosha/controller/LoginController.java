package com.roy.miaosha.controller;

import com.roy.miaosha.result.CodeMsg;
import com.roy.miaosha.result.Result;
import com.roy.miaosha.service.MiaoshaUserService;
import com.roy.miaosha.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/to_login")
    public String login(){
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVO loginVO, HttpServletResponse response){

        logger.info(loginVO.toString());

        miaoshaUserService.login(response, loginVO);

        return Result.success(true);
    }
}
