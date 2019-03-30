package com.roy.miaosha.redis;

public class GoodsKey extends BasePrefix{

    /**
     * 设置缓存过期时间
     * @param expireSeconds
     * @param prefix
     */
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
}
