package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DormViolationView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 9:41
 **/
@Getter
@Setter
@ToString
public class DormViolationView extends BaseView {
    private String id;

    private String dormCode;

    private String studentName;

    private String imgUrl;

    private String reason;

    private String result;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;

    private String memo;
}
