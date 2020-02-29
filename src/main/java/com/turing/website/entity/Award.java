package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "award")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Award implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long awardId;

    @Column(name = "award_name", nullable = false, length = 100)
    private String awardName;

    /**
     * 指定Member中的awards不被序列化, 以防止在以Award方向上获取awardMember时
     * Member中的awards又会被序列化, 此时又会访问Award, 造成无限循环
     * project同理
     */
    @JsonIgnoreProperties(value = {"awards", "projects"})
    @JoinTable(name = "member_award",
            joinColumns = {@JoinColumn(name = "award_id", referencedColumnName = "award_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Member> awardMember;

    @Column(name = "award_time", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date awardTime;

    @JsonIgnoreProperties(value = "award")
    @OneToMany(mappedBy = "award",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AwardPhoto> awardPhoto;

}