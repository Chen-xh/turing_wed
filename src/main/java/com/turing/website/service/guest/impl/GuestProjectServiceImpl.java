package com.turing.website.service.guest.impl;

import com.turing.website.dao.ProjectDao;
import com.turing.website.entity.Project;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestProjectServiceImpl implements GuestProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public Project findProjectById(Long id) {

        Project project = projectDao.findById(id).get();
        if(project == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PROJECT_NOT_FOUND);
        }
        return project;

    }

    @Override
    public List<Project> findAllProjects() {

        List<Project> projects = projectDao.findAll();
        return projects;

    }
}
