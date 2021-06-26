package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;
    @Column(name = "project_content", columnDefinition = "text", nullable = false)
    private String projectContent;
    @JoinTable(name = "project_member",
            joinColumns = {@JoinColumn(name = "project_id",referencedColumnName = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"projects", "awards"})
    private Set<Member> projectMember;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "project")
    private Set<ProjectPhoto> projectPhotos;
    @Column(name = "project_gif", length = 100)
    private String projectGif;

}
