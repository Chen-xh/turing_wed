package com.turing.website.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
public class ProjectDTO {

    private Long projectId;
    private String projectName;
    private String projectContent;
    private Set<String> projectMember;

}
