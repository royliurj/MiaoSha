package com.roy.miaosha.redis;

public class MiaoshaUserKey extends BasePrefix{
    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
