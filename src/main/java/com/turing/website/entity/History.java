package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "history")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long introductionId;
    @Column(name = "history_info", nullable = false, length = 255)
    private String introductionInfo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "history_edit_time", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)//实体类会封装成完整的时间
    private Date introductionEditTime;
    @Column(name = "history_video", length = 50)
    private String introductionVideo;

}
