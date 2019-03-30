package com.roy.miaosha.controller;

import com.roy.miaosha.domain.Goods;
import com.roy.miaosha.domain.MiaoshaUser;
import com.roy.miaosha.redis.GoodsKey;
import com.roy.miaosha.redis.RedisService;
import com.roy.miaosha.service.GoodsService;
import com.roy.miaosha.service.MiaoshaUserService;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController{


    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    GoodsService goodsService;

    @GetMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String goodsList(Model model, HttpServletResponse response, HttpServletRequest request, MiaoshaUser user){

        List<GoodsVo> list = goodsService.listGoodsVo();

        model.addAttribute("goodsList", list);

        //1, 获取缓存
        String html = redisService.get(GoodsKey.getGoodsList,"",String.class);
        //2, 如果存在，直接返回
        if(!StringUtils.isEmpty(html)){

            return html;
        }
        //3, 如果不存在，手动渲染
        SpringWebContext context = new SpringWebContext(request,response,
                request.getServletContext(),
                request.getLocale(),
                model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        if(!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;
    }


    @GetMapping("/to_detail/{id}")
    public String goodsDetail(Model model, MiaoshaUser user, @PathVariable("id") Long id){

        GoodsVo goodsVo = goodsService.getGoodsVoById(id);

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
            remainSeconds = -1;

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
