package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DormRepairView
 * @Description DormRepairView
 * @Author zhaoRong
 * @Date 2019/4/3 14:08
 **/
@Getter
@Setter
public class DormRepairView extends BaseView {
    private String id;

    private String dormCode;

    private Integer type;

    private String description;

    private String imgUrl;

    private String studentName;

    private String studentMobile;

    private Integer status;

    private BigDecimal cost;

    private String billUrl;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;

}
