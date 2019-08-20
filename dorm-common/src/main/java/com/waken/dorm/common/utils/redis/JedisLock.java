package com.waken.dorm.common.utils.redis;

import com.waken.dorm.common.utils.SpringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 基于redis的分布锁实现
 * Created by zhaoRong on 2019/04/08.
 */
public class JedisLock implements DistributedLock {

    private static StringRedisTemplate redisTemplate;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    String lockKey; // 锁的键值
    int expireMsecs = 10 * 1000; // 锁超时，防止线程在入锁以后，无限的执行等待
    int timeoutMsecs = 10 * 1000; // 锁等待，防止线程饥饿
    boolean locked = false; // 是否已经获取锁

    /**
     * 获取指定键值的锁
     *
     * @param lockKey 锁的键值
     */
    public JedisLock(String lockKey) {
        this.lockKey = lockKey;
    }

    /**
     * 获取指定键值的锁,同时设置获取锁超时时间
     *
     * @param lockKey      锁的键值
     * @param timeoutMsecs 获取锁超时时间
     */
    public JedisLock(String lockKey, int timeoutMsecs) {
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 获取指定键值的锁,同时设置获取锁超时时间和锁过期时间
     *
     * @param lockKey      锁的键值
     * @param timeoutMsecs 获取锁超时时间
     * @param expireMsecs  锁失效时间
     */
    public JedisLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
        this.expireMsecs = expireMsecs;
    }

    public String getLockKey() {
        return lockKey;
    }

    public synchronized boolean acquire() {
        int timeout = timeoutMsecs;
        if (redisTemplate == null) {
            redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
        }
        try {
            while (timeout >= 0) {
                long expires = System.currentTimeMillis() + expireMsecs + 1;
                String expiresStr = String.valueOf(expires); // 锁到期时间

                if (redisTemplate.opsForValue().setIfAbsent(lockKey, expiresStr)) {
                    // lock acquired
                    locked = true;
                    return true;
                }
                String currentValueStr = redisTemplate.opsForValue().get(lockKey); // redis里的时间
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    // 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                    // lock is expired
                    String oldValueStr = redisTemplate.opsForValue().getAndSet(lockKey, expiresStr);
                    // 获取上一个锁到期时间，并设置现在的锁到期时间，
                    // 只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        // 如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                        // lock acquired
                        locked = true;
                        return true;
                    }
                }
                timeout -= 100;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            logger.error("释放锁失败，原因：", e);
        }
        return false;
    }

    /**
     * 释放锁
     */
    public synchronized void release() {
        if (redisTemplate == null) {
            redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
        }
        try {
            if (locked) {
                String currentValueStr = redisTemplate.opsForValue().get(lockKey); // redis里的时间
                // 校验是否超过有效期，如果不在有效期内，那说明当前锁已经失效，不能进行删除锁操作
                if (currentValueStr != null && Long.parseLong(currentValueStr) > System.currentTimeMillis()) {
                    redisTemplate.delete(lockKey);
                    locked = false;
                }
            }
        } catch (Exception e) {
            logger.error("释放锁失败，原因：", e);
        }
    }

}
