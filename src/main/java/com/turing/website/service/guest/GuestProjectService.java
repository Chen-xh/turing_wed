package com.turing.website.service.guest;


import com.turing.website.entity.Project;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestProjectService {
    /**
     * 根据项目id查找项目
     * @param id 项目id号
     * @return
     */
    Project findProjectById(Long id);

    /**
     * 查找所有项目
     * @return
     */
    List<Project> findAllProjects();

}
