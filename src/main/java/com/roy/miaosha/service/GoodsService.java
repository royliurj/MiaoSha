package com.roy.miaosha.service;

import com.roy.miaosha.dao.GoodsDao;
import com.roy.miaosha.domain.Goods;
import com.roy.miaosha.domain.MiaoshaGoods;
import com.roy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoById(Long id) {
        return goodsDao.getGoodsVoById(id);
    }

    public void reduceStock(GoodsVo goodsVo) {
        MiaoshaGoods  g = new MiaoshaGoods();
        g.setGoodsId(goodsVo.getId());
//        g.setStockCount(goodsVo.getStockCount() - 1);
        goodsDao.reduceStock(g);
    }
}
