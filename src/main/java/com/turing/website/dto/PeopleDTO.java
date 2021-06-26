package com.turing.website.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDTO {
    @ApiModelProperty(hidden = true)
    private String roleName;

}
