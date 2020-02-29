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
@Table(name = "member")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;
    @Column(name = "member_stu_id", nullable = false, length = 12)
    private String memberStuId;
    @Column(name = "member_telephone", nullable = false, length = 11)
    private String telephone;
    @Column(name = "member_birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "mem_after_graduated_destination", nullable = false, length = 50)
    private String afterGraduatedDestination;
    @Column(name = "member_major", nullable = false, length = 20)
    private String memberMajor;
    @Column(name = "member_class", nullable = false, length = 20)
    private String memberClass;
    @Column(name = "member_technology", nullable = false, length = 30)
    private String memberTechnology;
    @Column(name = "member_password", nullable = false, length = 50)
    @JsonIgnore//密码忽略
    private String password;
    @Column(name = "member_identity", nullable = false, length = 15)
    private String identity;
    /** 使用@ManyToMany注解来映射多对多关联关系
        使用@JoinTable注解来映射中间表
        1. name指向中间表的名字
        2. joinColumns映射当前类在所在的表在中间表中的外键
        2.1 name指定外键列的列名
        2.2 referencedColumnName指定外键列映射当前的表的哪一列
        3. inverseJoinColumns映射关联的类所在中间表的外键
     */
    @JoinTable(name = "member_award",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "award_id", referencedColumnName = "award_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Award> awards;
    @JoinTable(name = "project_member",
            joinColumns = {@JoinColumn(name = "member_id",referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "project_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Project> projects;
    @Column(name = "member_introduction", nullable = false)
    private String memberIntroduction;
    @Column(name = "member_icon", nullable = false)
    private String memberIcon;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Role role;

}
