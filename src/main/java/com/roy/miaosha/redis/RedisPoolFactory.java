package com.roy.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {


    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool JeditPoolFactory(){
        //设置JedisPoolConfig
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait());

        //实例化JedisPool
        JedisPool jp = new JedisPool(redisConfig.getHost(),redisConfig.getPort());
        return jp;
    }
}
