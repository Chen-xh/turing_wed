package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "role")
@Data
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Teacher> teachers;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Member> members;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"permissions","members","teachers"})
    @JsonIgnore
    @JoinTable(name = "role_permissions",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName="role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName="permission_id")})
    private List<Permission> permissions;
}
