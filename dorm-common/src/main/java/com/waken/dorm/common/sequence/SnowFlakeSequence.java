package com.waken.dorm.common.sequence;

/**
 * 雪花算法生成Id
 *
 * @author aishu
 *
 */
public class SnowFlakeSequence {

    private static SnowFlakeGenerator generator;

    private synchronized static void init(){
        if(generator!=null)return;
        //分布式时需要从配置文件中配置数据中心id与服务id！目前是单服务模式，设置一个默认值就OK
        Long dataCenterId = 1L;
        Long serverId = 1L;
        generator = new SnowFlakeGenerator.Factory().create(dataCenterId, serverId);
    }

    public static Long next(){
        if(generator==null){
            init();
        }
        return generator.nextId();
    }

    public static void main(String[] args){
//        generator = new SnowFlakeGenerator.Factory().create(0, 0);
//        for(int i=0;i<1;i++){
//            System.out.println(generator.nextId());
//        }
        System.out.println(SnowFlakeSequence.next());
    }
}
