package com.waken.dorm.common.entity.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
public class StudentInfo implements Serializable {
    private static final long serialVersionUID = -6872689417624493481L;
    private String id;

    private String name;

    private String code;

    private Integer sex;

    private String tel;

    private String email;

    private String imgUrl;

    private Date createTime;

    private String studentToken;
}
