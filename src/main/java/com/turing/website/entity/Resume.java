package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
@ToString
@Entity
@Table(name = "resume")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@ApiModel(value = "简历")
public class Resume {
    @Column(name = "resume_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;
    @Column(name = "resumeName", nullable = false, length = 50)
    private String resumeName;
    @Column(name = "resumeStudentId", nullable = false, length = 50)
    private String resumeStudentId;
    @Column(name = "resume_telephone",nullable = false, length = 11)
    private String resumeTelephone;
    @Column(name = "resume_major", nullable = false, length = 60)
    private String resumeMajor;
    @Column(name = "resume_direct", nullable = false, length = 20)
    private String resumeDirect;
    @Column(name = "resume_evaluation", nullable = false, columnDefinition = "text")
    private String resumeEvaluation;
    @Column(name = "resume_skills", nullable = false, columnDefinition = "text")
    private String resumeSkills;
    @Column(name = "resume_experience", nullable = false, columnDefinition = "text")
    private String resumeExp;
    @Column(name = "resume_expect", nullable = false, columnDefinition = "text")
    private String resumeExpect;
    @Column(name = "resume_other", nullable = false, columnDefinition = "text")
    private String resumeOther;
}