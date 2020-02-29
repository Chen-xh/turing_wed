package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "project_photo")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ProjectPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_photo_id")
    private Long projectPhotoId;
    @Column(name = "project_photo_loc", nullable = false)
    private String projectPhotoLoc;
    @JoinColumn(name = "project_id")
    @ManyToOne
    @JsonIgnoreProperties(value = "projectPhotos")
    private Project project;

}
