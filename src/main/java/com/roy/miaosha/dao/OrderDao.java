package com.roy.miaosha.dao;

import com.roy.miaosha.domain.MiaoshaOrder;
import com.roy.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);

    @Insert("insert order_info(user_id,goods_id,goods_name,goods_count,goods_price, order_channel,status,create_date) " +
            "values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert miaosha_order (user_id, goods_id, order_id) values(#{userId}, #{goodsId}, #{orderId})")
    long insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
