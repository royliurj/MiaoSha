package com.roy.miaosha.controller;


import com.roy.miaosha.domain.User;
import com.roy.miaosha.redis.RedisService;
import com.roy.miaosha.redis.UserKey;
import com.roy.miaosha.result.Result;
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

    @Autowired
    RedisService redisService;

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

    @GetMapping("/redis/get")
    @ResponseBody
    public Result<Integer> redisGet(){
        Integer foo = redisService.get(UserKey.getById,"foo", int.class);
        return Result.success(foo);
    }

    @GetMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet(){

        User user = new User();
        user.setId(1);
        user.setName("1");

        redisService.set(UserKey.getById,"1", user);
        User result = redisService.get(UserKey.getById,"1", User.class);
        return Result.success(result);
    }
}
