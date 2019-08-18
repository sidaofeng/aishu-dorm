package com.waken.dorm.common.entity.student;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName StudentInfo
 * @Description StudentInfo
 * @Author zhaoRong
 * @Date 2019/3/30 14:02
 **/
@Getter
@Setter
@ToString
public class StudentInfo extends BaseEntity {
    private static final long serialVersionUID = -6872689417624493481L;
    private String studentId;

    private String studentName;

    private Integer studentNum;

    private String mobile;

    private Integer gender;

    private String email;

    private String imgUrl;

    private Date createTime;

    private String studentToken;
}
