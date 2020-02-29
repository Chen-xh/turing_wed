package com.turing.website.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
public class PeopleDTO {
    @ApiModelProperty(hidden = true)
    private String roleName;

}
