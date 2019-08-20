package com.waken.dorm.common.utils;

import com.waken.dorm.common.base.UpdateStatusEntity;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class DormUtil {


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

}
