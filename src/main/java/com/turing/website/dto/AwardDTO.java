package com.turing.website.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
public class AwardDTO {

    private Long awardId;

    private String awardName;

    private List<String> members;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date awardTime;

}
