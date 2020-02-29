package com.turing.website.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel
public class MemberDTO extends PeopleDTO {
    @ApiModelProperty(value = "成员id", hidden = true)
    private Long memberId;
    @ApiModelProperty(value = "成员姓名", required = true)
    private String memberName;
    @ApiModelProperty(value = "成员学号", required = true)
    private String memberStuId;
    @ApiModelProperty(value = "成员手机号码", required = true)
    private String telephone;
    @ApiModelProperty(value = "成员生日", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @ApiModelProperty(value = "成员毕业去向", required = true)
    private String afterGraduatedDestination;
    @ApiModelProperty(value = "成员专业", required = true)
    private String memberMajor;
    @ApiModelProperty(value = "成员班级", required = true)
    private String memberClass;
    @ApiModelProperty(value = "成员技术方向", required = true)
    private String memberTechnology;
    @ApiModelProperty(value = "成员密码", hidden = true)
    @JsonIgnore
    private String password;
    @ApiModelProperty(value = "成员身份", required = true)
    private String identity;
    @ApiModelProperty(value = "成员自我介绍", required = true)
    private String memberIntroduction;

}
