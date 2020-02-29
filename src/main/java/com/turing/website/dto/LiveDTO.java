package com.turing.website.dto;

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
public class LiveDTO {

    private Long liveId;

    private String liveName;

    private String liveContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date liveTime;

}
