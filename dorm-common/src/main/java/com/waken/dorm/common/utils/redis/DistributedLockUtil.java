package com.waken.dorm.common.utils.redis;

/**
 * Created by zhaoRong on 2019/04/08.
 */
public class DistributedLockUtil {

    /**
     * 获取分布式锁 默认获取锁10s超时，锁过期时间60s
     */
    public static DistributedLock getDistributedLock(String lockKey) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(lockKey);
        return lock;
    }

    /**
     * 正式环境、测试环境共用一个redis时，避免key相同造成影响
     *
     * @param lockKey
     * @return
     * @author lihaiqiang
     */
    private static String assembleKey(String lockKey) {
        return String.format("lock_%s", lockKey);
    }

    /**
     * 获取分布式锁 默认获取锁10s超时，锁过期时间60s
     *
     * @return
     * @author lihaiqiang
     * 指定获取锁超时时间
     */
    public static DistributedLock getDistributedLock(String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(lockKey, timeoutMsecs);
        return lock;
    }

    /**
     * 获取分布式锁 默认获取锁10s超时，锁过期时间60s
     *
     * @param lockKey      锁的key
     * @param timeoutMsecs 指定获取锁超时时间
     * @param expireMsecs  指定锁过期时间
     * @return
     * @author lihaiqiang
     */
    public static DistributedLock getDistributedLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(lockKey, expireMsecs, timeoutMsecs);
        return lock;
    }

    /**
     * 实例。。。
     */
//    DistributedLock lock = DistributedLockUtil.getDistributedLock(key);
//    try {
//        if (lock.acquire()) {
//            //获取锁成功业务代码
//        } else { // 获取锁失败
//            //获取锁失败业务代码
//    } finally {
//        if (lock != null) {
//            lock.release();
//        }
//    }
}
