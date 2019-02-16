package com.roy.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {


    @Autowired
    JedisPool jedisPool;

    /**
     * 获取Key的值
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            String s = jedis.get(getRealKey(prefix, key));

            T t = stringToBean(s, clazz);

            return t;

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置Key的值
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() == 0){
                return false;
            }
            String k = getRealKey(prefix, key);
            int seconds = prefix.expireSeconds();
            if(seconds <= 0){
                jedis.set(k,str);
            } else {
                jedis.setex(k,seconds, str);
            }

            return true;

        }finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }

        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return "" + value;
        } else if(clazz == String.class){
            return (String) value;
        } else if(clazz == long.class || clazz == Long.class){
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String s, Class<T> clazz) {

        if(s == null || s.length() == 0){
            return null;
        }

        if(clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(s);
        } else if(clazz == String.class){
            return (T) s;
        } else if(clazz == long.class || clazz == Long.class){
            return (T) Long.valueOf(s);
        } else {
            return JSON.toJavaObject(JSON.parseObject(s),clazz);
        }
    }

    /**
     * 返回到连接池，不是关闭
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }

    /**
     * 判断Key是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            String k = getRealKey(prefix, key);

            return jedis.exists(k);

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增长
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            String k = getRealKey(prefix, key);

            return jedis.incr(k);

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 获取真正的Key
     * @param prefix
     * @param key
     * @return
     */
    private String getRealKey(KeyPrefix prefix, String key) {
        return prefix.getPrefix() + ":" + key;
    }

    /**
     * 减少
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            String k = getRealKey(prefix, key);

            return jedis.decr(k);

        }finally {
            returnToPool(jedis);
        }
    }
}
