package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "teacher")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "teacher_name",nullable = false, length = 50)
    private String teacherName;
    @Column(name = "teacher_email", nullable = false, length = 100)
    private String teacherEmail;
    @Column(name = "teacher_born", nullable = false, length = 20)
    private String teacherBorn;
    @Column(name = "teacher_position", nullable = false,length = 50)
    private String teacherPosition;
    @Column(name = "teacher_job", nullable = false, length = 50)
    private String teacherJob;
    @Column(name = "teacher_graduation", nullable = false, length = 50)
    private String teacherGraduation;
    @Column(name = "teacher_research", nullable = false)
    private String teacherResearch;
    @Column(name = "teacher_scientific_research", nullable = false)
    private String teacherScientificResearch;
    @Column(name = "teacher_award_introduction", nullable = false)
    private String teacherAwardIntroduction;
    @Column(name = "teacher_img", nullable = false)
    private String teacherImg;
    @Column(name = "teacher_password")
    @JsonIgnore
    private String teacherPassword;
    @JoinColumn(name = "role_id")
    @ManyToOne
    @JsonIgnore
    private Role role;
}
