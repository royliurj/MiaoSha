package com.roy.miaosha.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/miaosha")
public class HomeController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/test")
    public String thymeleaf(Model model){
        model.addAttribute("name","Roy");
        return "test";
    }
}
