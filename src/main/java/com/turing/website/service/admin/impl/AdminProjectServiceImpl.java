package com.turing.website.service.admin.impl;


import com.turing.website.dao.MemberDao;
import com.turing.website.dao.ProjectDao;
import com.turing.website.dao.ProjectPhotoDao;
import com.turing.website.dto.ProjectDTO;
import com.turing.website.entity.Member;
import com.turing.website.entity.Project;
import com.turing.website.entity.ProjectPhoto;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminProjectService;
import com.turing.website.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminProjectServiceImpl implements AdminProjectService {

    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    MemberDao memberDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ProjectPhotoDao projectPhotoDao;

    @Override
    public void addOrUpdateProject(ProjectDTO projectDTO, MultipartFile[] projectPhotos) {
        String[] projectPhotosNameArr = fileUploadUtil.getFileNewNames(projectPhotos);
        //获取到所有的项目成员
        Set<String> projectMembersNameSet = projectDTO.getProjectMember();
        //项目图片的Set集合,用于绑定到项目实体中
        Set<ProjectPhoto> projectPhotoSet = new HashSet<>();
        //项目成员的Set集合, 用于绑定到项目实体中
        Set<Member> memberSet = new HashSet<>();
        //将所有项目成员名字转为成员实体
        projectMembersNameSet.forEach(memberName->{
            Member dbMember;
            try {
                dbMember = memberDao.findByMemberNameEquals(memberName);
                memberSet.add(dbMember);
            }catch (Exception ex) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
            }
        });

        if(projectDTO.getProjectId() == null){
            //创建
            Project project = new Project();
            //将所有照片映射为ProjectPhoto实体
            for (String projectPhotoName : projectPhotosNameArr) {
                ProjectPhoto projectPhoto = new ProjectPhoto();
                projectPhoto.setProject(project);
                projectPhoto.setProjectPhotoLoc(projectPhotoName);
                projectPhotoSet.add(projectPhoto);
            }

            project.setProjectMember(memberSet);
            project.setProjectPhotos(projectPhotoSet);
            project.setProjectName(projectDTO.getProjectName());
            project.setProjectContent(projectDTO.getProjectContent());
            projectDao.save(project);
            //上传项目图片到磁盘路径
            fileUploadUtil.uploadFiles(projectPhotos, projectPhotosNameArr);
        }else{
            //更新
            Project dbProject;
            try {
                dbProject = projectDao.findById(projectDTO.getProjectId()).get();
            }catch (Exception ex){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.PROJECT_NOT_FOUND);
            }
            //将所有照片映射为ProjectPhoto实体
            for (String projectPhotoName : projectPhotosNameArr) {
                ProjectPhoto projectPhoto = new ProjectPhoto();
                projectPhoto.setProject(dbProject);
                projectPhoto.setProjectPhotoLoc(projectPhotoName);
                projectPhotoSet.add(projectPhoto);
            }

            //获取数据库中的照片id并删除
            Set<ProjectPhoto> dbProjectPhotosSet = dbProject.getProjectPhotos();
            //解除图片和项目的依赖关系,否则无法删除,因为有mappedBy属性
            dbProject.setProjectPhotos(null);
            dbProjectPhotosSet.forEach(dbProjectPhoto->{
                Long dbProjectPhotoId = dbProjectPhoto.getProjectPhotoId();
                projectPhotoDao.deleteById(dbProjectPhotoId);
                //删除本地磁盘的图片
                String projectPhotoLoc = dbProjectPhoto.getProjectPhotoLoc();
                fileUploadUtil.deletePhoto(projectPhotoLoc);
            });
            dbProject.setProjectPhotos(projectPhotoSet);
            dbProject.setProjectMember(memberSet);
            dbProject.setProjectContent(projectDTO.getProjectContent());
            dbProject.setProjectName(projectDTO.getProjectName());
            projectDao.save(dbProject);
            //上传项目图片到磁盘路径
            fileUploadUtil.uploadFiles(projectPhotos, projectPhotosNameArr);
        }

    }

    @Override
    public void deleteProject(Long projectId) {

        //更新
        Project dbProject;
        try {
            dbProject = projectDao.findById(projectId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PROJECT_NOT_FOUND);
        }

        projectDao.deleteById(projectId);
        //删除磁盘中的图片
        dbProject.getProjectPhotos().stream().forEach(projectPhoto -> {
            String projectPhotoLoc = projectPhoto.getProjectPhotoLoc();
            fileUploadUtil.deletePhoto(projectPhotoLoc);
        });
    }

    @Override
    public Project getProjectById(Long projectId) {

        Project dbProject;
        try {
            dbProject = projectDao.findById(projectId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PROJECT_NOT_FOUND);
        }
        return dbProject;

    }
}
