package com.waken.dorm.common.form.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName: EditButtonsForm
 * @Description: 权限表单
 * @Author: zhaoRong
 * @Create: 2019/11/20 20:09
 **/
@Getter
@Setter
@ToString
public class EditButtonsForm {

    private String parentId;

    private List<ButtonResources> editButtonsList;
}
