package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Table(name = "inform")
@Entity
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Inform implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inform_id")
    private Long informId;
    @Column(name = "inform_title", length = 50)
    private String informTitle;
    @Column(name = "inform_content", nullable = false)
    private String informContent;
    @Column(name = "inform_create_time", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date informCreateTime;
    @Column(name = "inform_username", nullable = false, length = 20)
    private String informUsername;

}
