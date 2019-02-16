package com.roy.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 0 代表永远不过期
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }


    /**
     * 保证各个模块到前缀都不一样
     * @return
     */
    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        return simpleName + ":" + prefix;
    }
}
