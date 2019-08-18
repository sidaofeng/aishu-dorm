package com.waken.dorm.common.utils.redis;

/**
 * 基于redis的分布式锁依赖于系统时钟，需要保证各个竞争者的时钟的一致性，否则会出现一个参与者获得锁，而另一个参与者的时钟判断其已过期，导致分布式锁失效；
 * 需要保证redis节点的高可用，建议使用哨兵机制；
 * 在使用分布式锁之前，考虑是否可以通过乐观锁或无锁解决并发同步问题，毕竟使用锁的代价很是比较高昂的
 * Created by zhaoRong on 2019/04/08.
 */
public interface DistributedLock {

    /**
     * 获取锁
     *
     * @author lihaiqiang
     * @time 20180828
     */
    public boolean acquire();

    /**
     * 释放锁
     *
     * @author lihaiqiang
     * @time 2010828
     */
    public void release();
}
