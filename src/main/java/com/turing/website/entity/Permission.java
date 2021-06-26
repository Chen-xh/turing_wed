package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private Long permissionId;//主键.
    @Column(name = "permission_name")
    private String name;//名称.
    @Column(name = "permission_permission")
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    @ManyToMany(fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties(value = {"permissions","members","teachers"})
    @JsonIgnore
    @JoinTable(name = "role_permissions",
            joinColumns = {@JoinColumn(name = "permission_id",referencedColumnName="permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName="role_id")})
    private List<Role> roles;

}
