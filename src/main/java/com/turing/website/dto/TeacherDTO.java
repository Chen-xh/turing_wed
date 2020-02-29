package com.turing.website.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel
public class TeacherDTO extends PeopleDTO{
    @ApiModelProperty(value = "教师id")
    private Long teacherId;
    @ApiModelProperty(value = "教师姓名")
    private String teacherName;
    @ApiModelProperty(value = "教师邮箱")
    @Email(message = "邮箱格式不正确！")
    private String teacherEmail;
    @ApiModelProperty(value = "教师出生地")
    private String teacherBorn;
    @ApiModelProperty(value = "教师职称")
    private String teacherJob;
    @ApiModelProperty(value = "教师毕业院校")
    private String teacherGraduation;
    @ApiModelProperty(value = "教师主要研究方向")
    private String teacherResearch;
    @ApiModelProperty(value = "教师科技研究方向")
    private String teacherScientificResearch;
    @ApiModelProperty(value = "教师获奖情况")
    private String teacherAwardIntroduction;
    @ApiModelProperty(value = "教师密码")
    @JsonIgnore
    private String teacherPassword;
    @ApiModelProperty(value = "教师职位")
    private String teacherPosition;
}
