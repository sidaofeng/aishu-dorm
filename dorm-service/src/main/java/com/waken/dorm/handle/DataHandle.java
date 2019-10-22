package com.waken.dorm.handle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description: 用于业务层一些业务逻辑的数据处理、转换等
 * @author: aishu
 * @create: 2019-10-22 15:55
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataHandle {

    /**
     * 处理目标数据：
     * 需求：绑定数据，
     * 将需要绑定的数据做如下处理：
     * 1.判断pkIdAndTargetIdMap是否存在值，若有值，表示之前存在关联，我们需要将之前已经存在的绑定的数据和现在需要绑定的数据做对比
     * 排除掉共同的数据，找出已经存在的绑定的数据中不包含现在的需要绑定的数据，然后删除，
     * 找出现在的需要绑定的数据中不包含已经存在的绑定的数据,然后新增
     *
     * @param targetIds          目标id集合
     * @param pkIdAndTargetIdMap 对应的关联表的主键id与目标id的map<k=pkId,v=targetId>
     */
    public static List<String> handleToDelAndTargetIds(List<String> targetIds, Map<String, String> pkIdAndTargetIdMap) {

        if (pkIdAndTargetIdMap != null && !pkIdAndTargetIdMap.isEmpty()) {
            // 接收已经存在关联的资源id
            Set<String> existIds = new HashSet<>();
            // 接收需要删除的关联主键id
            List<String> toDelPkIds = new ArrayList<>();
            /**
             * 如果目标id为空，表示删除所有关联，即将所有的关联主键id全部返回删除
             */
            if (targetIds == null || targetIds.isEmpty()) {
                toDelPkIds.addAll(pkIdAndTargetIdMap.keySet());
                return toDelPkIds;
            }
            for (Map.Entry<String, String> entry : pkIdAndTargetIdMap.entrySet()) {
                if (targetIds.contains(entry.getValue())) {
                    existIds.add(entry.getValue());
                } else {
                    toDelPkIds.add(entry.getKey());
                }
            }
            if (existIds != null && !existIds.isEmpty()) {
                targetIds.removeAll(existIds);
            }
            return toDelPkIds;
        }
        return null;
    }

}