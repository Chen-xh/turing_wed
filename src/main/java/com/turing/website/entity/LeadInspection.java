package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "leadInspection")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class LeadInspection{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;
    @Column(name = "img", nullable = false)
    private String img;
}
