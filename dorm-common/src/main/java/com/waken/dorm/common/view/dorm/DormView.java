package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName DormView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 12:19
 **/
@Getter
@Setter
public class DormView extends BaseView {
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;
    /**
     * 校区
     */
    private String campusName;

    /**
     * 建筑物
     */
    private String buildingName;

    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 宿舍性别（1：男生寝室 2：女生寝室）
     */
    private Integer dormSex;

    /**
     * 宿舍状态（1使用，2闲置，3不可用）
     */
    private Integer status;

    /**
     * 宿舍类型（1学生宿舍、2教师宿舍、3宿管宿舍、4其他宿舍
     */
    private Integer type;

    /**
     * 床位数量
     */
    private Integer bedNum;

    /**
     * 备注
     */
    private String memo;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;
}
