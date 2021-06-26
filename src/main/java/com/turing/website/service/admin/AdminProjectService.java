package com.turing.website.service.admin;


import com.turing.website.dto.ProjectDTO;
import com.turing.website.entity.Project;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminProjectService {

    void addOrUpdateProject(ProjectDTO projectDTO, MultipartFile[] projectPhotos);

    void deleteProject(Long projectId);

    Project getProjectById(Long projectId);

}
