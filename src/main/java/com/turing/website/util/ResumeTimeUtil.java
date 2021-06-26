package com.turing.website.util;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/3/11 17:27
 */
@Data
public class ResumeTimeUtil {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;
}
