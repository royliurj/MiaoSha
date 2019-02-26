package com.roy.miaosha.dao;

import com.roy.miaosha.domain.Goods;
import com.roy.miaosha.domain.MiaoshaGoods;
import com.roy.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.*, mg.miaosha_price, mg.stock_count, mg.start_date, mg.end_date from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*, mg.miaosha_price, mg.stock_count, mg.start_date, mg.end_date from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoById(@Param("goodsId") Long id);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    void reduceStock(MiaoshaGoods g);
}
