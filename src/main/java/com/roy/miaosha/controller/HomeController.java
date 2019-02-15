package com.roy.miaosha.controller;


import com.roy.miaosha.domain.User;
import com.roy.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/miaosha")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/test")
    public String thymeleaf(Model model){
        model.addAttribute("name","Roy");
        return "test";
    }


    @GetMapping("/db")
    @ResponseBody
    public User db(Model model){
        User user = userService.getUserById(1);
        return user;
    }
}
