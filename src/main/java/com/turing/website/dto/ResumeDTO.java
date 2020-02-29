package com.turing.website.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
public class ResumeDTO {

    private Integer resumeId;
    @NotBlank(message = "简历名字不能为空")
    private String resumeName;
    @Size(min=12, max=12, message="学号必须为12位!")
    private String resumeStudentId;
    @Pattern(regexp =
            "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$",
            message = "手机号码格式不正确!")
    private String resumeTelephone;
    @NotBlank(message = "必须填写专业班级")
    private String resumeMajor;
    @NotBlank(message = "必须填写面试方向!")
    private String resumeDirect;
    @NotBlank(message = "必须填写自我评价!")
    private String resumeEvaluation;
    @NotBlank(message = "掌握技能若无,则填暂无!")
    private String resumeSkills;
    @NotBlank(message = "项目经验若无,则填暂无!")
    private String resumeExp;
    @NotBlank(message = "必须填写未来期望!")
    private String resumeExpect;
    @NotBlank(message = "其它优势若无, 则填暂无!")
    private String resumeOther;

}
