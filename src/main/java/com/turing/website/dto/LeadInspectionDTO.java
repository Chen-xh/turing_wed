package com.turing.website.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadInspectionDTO {
    @ApiModelProperty(value = "领导视察id", hidden = true)
    private Long id;
    @ApiModelProperty(value = "领导视察标题", required = true)
    private String title;
    @ApiModelProperty(value = "领导视察时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @ApiModelProperty(value = "领导视察内容", required = true)
    private String content;

}
