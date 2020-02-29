package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "live")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Live implements Serializable {

    @Id
    @Column(name = "live_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long liveId;

    @Column(name = "live_name", length = 50, nullable = false)
    private String liveName;

    @Column(name = "live_content", columnDefinition = "text", nullable = false)
    private String liveContent;

    @Column(name = "live_time", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date liveTime;
    /**
     * cascade = CascadeType.ALL
     *删除Live后也删除了LivePhoto
     */
    @OneToMany(mappedBy = "live",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "live")
    private Set<LivePhoto> livePhotos;

}
