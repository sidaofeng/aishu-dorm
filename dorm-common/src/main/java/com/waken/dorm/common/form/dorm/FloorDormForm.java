package com.waken.dorm.common.form.dorm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName: FloorDormForm
 * @Description: 通过楼层id自动生成宿舍房间、床位
 * @Author: zhaoRong
 * @Create: 2019/12/6 22:16
 **/
@Getter
@Setter
@ToString
public class FloorDormForm {
    /**
     * 楼层id
     */
    private String floorId;
    /**
     * 需要生成宿舍数量
     */
    private Integer roomNum;
    /**
     * 每个宿舍房间的床位数量(默认是0,后面根据情况维护)
     */
    private Integer bedNum;

    /**
     * 宿舍性别（1：男生寝室 2：女生寝室）
     */
    private Integer sex;

    /**
     * 宿舍类型（1学生宿舍、2教师宿舍、3宿管宿舍、4其他宿舍
     */
    private Integer type;

}
