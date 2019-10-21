package com.waken.dorm.common.utils;

import com.waken.dorm.common.base.UpdateStatusEntity;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.enums.CodeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Getter
public class DormUtil {

    public static final String pkId = "pkId";

    public static final String targetId = "targetId";

    public static final String toDelPkIds = "toDelPkIds";

    public static final String toAddTargetIds = "toAddTargetIds";




    /**
     * 通过id得到需要更新的map对象
     *
     * @param ids
     * @param userId
     */
    public static Map<String, Object> getToUpdateStatusMap(List<String> ids, String userId) {
        Map<String, Object> param = new HashMap<>();
        Date curDate = DateUtils.getCurrentDate();
        List<UpdateStatusEntity> updateList = new ArrayList<>();// 接收需要修改的id和状态码
        for (String id : ids) {
            UpdateStatusEntity statusEntity = new UpdateStatusEntity();
            statusEntity.setId(id);
            statusEntity.setStatus(CodeEnum.DELETE.getCode());
            statusEntity.setLastModifyTime(curDate);
            statusEntity.setLastModifyUserId(userId);
            updateList.add(statusEntity);
        }
        param.put("list", updateList);
        return param;
    }

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value))
            return value;
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0)
            return value;
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1)
                result.append(arr[i]).append(Constant.SPLIT_UNDERLINE);
            else
                result.append(arr[i]);
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     *
     * @param targetIds 目标id集合
     * @param targetIdAndPkIdList 目标id与对应的主键id
     * @return toDelPkIds 需要被删除的关联id
     * @return toAddTargetIds 需要新增的目标id
     *
     */
    public static Map<String,List<String>> getToDelAndTargetIds(List<String> targetIds, List<Map<String, String>> targetIdAndPkIdList) {

        Map<String,List<String>> resMap = new HashMap<>(4);

        if (targetIdAndPkIdList!=null && !targetIdAndPkIdList.isEmpty()) {
            // 接收已经存在关联的资源id
            List<String> existIds = new ArrayList<>();
            // 接收需要删除的关联主键id
            List<String> toDelPkIds = new ArrayList<>();
            for (Map<String,String> targetIdAndPkIdMap : targetIdAndPkIdList) {
                if (targetIds == null || targetIds.isEmpty()){
                    toDelPkIds.add(targetIdAndPkIdMap.get(DormUtil.pkId));
                }
                if (targetIds.contains(targetIdAndPkIdMap.get(DormUtil.targetId))) {
                    existIds.add(targetIdAndPkIdMap.get(DormUtil.targetId));
                } else {
                    toDelPkIds.add(targetIdAndPkIdMap.get(DormUtil.pkId));
                }
            }
            if (existIds != null && !existIds.isEmpty()){
                targetIds.removeAll(existIds);
            }

            if (toDelPkIds != null && !toDelPkIds.isEmpty()) {
                resMap.put(DormUtil.toDelPkIds,toDelPkIds);
            }

        }
        if (null != targetIds && !targetIds.isEmpty()){
            resMap.put(DormUtil.toAddTargetIds,targetIds);
        }

        return resMap;
    }

}
