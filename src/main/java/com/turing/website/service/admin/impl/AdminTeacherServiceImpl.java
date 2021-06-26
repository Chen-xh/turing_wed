package com.turing.website.service.admin.impl;


import com.turing.website.dao.TeacherDao;
import com.turing.website.dto.TeacherDTO;
import com.turing.website.entity.Role;
import com.turing.website.entity.Teacher;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminTeacherService;
import com.turing.website.util.FileUploadUtil;
import com.turing.website.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminTeacherServiceImpl implements AdminTeacherService {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    public void addTeacher(TeacherDTO teacherDTO, MultipartFile teacherIcon) {

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        teacher.setTeacherPassword(MD5Util.getHexPassword(teacherDTO.getTeacherPassword()));
        Role role = new Role();
        role.setRoleName("教师");
        role.setRoleId(1L);
        teacher.setRole(role);
        String fileName = fileUploadUtil.getNewFileName(teacherIcon);
        fileUploadUtil.uploadOneFile(teacherIcon, fileName);
        teacher.setTeacherImg(fileName);
        teacherDao.save(teacher);

    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO, MultipartFile teacherIcon) {

        Long teacherId = teacherDTO.getTeacherId();
        if(!isExistTeacherInDB(teacherId)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.TEACHER_NOT_FOUND);
        }

        Teacher dbTeacher = teacherDao.findById(teacherId).get();
        String dbTeacherImg = dbTeacher.getTeacherImg();
        fileUploadUtil.deletePhoto(fileUploadUtil.getUploadDir() + dbTeacherImg);
        String newFileName = fileUploadUtil.getNewFileName(teacherIcon);
        fileUploadUtil.uploadOneFile(teacherIcon, newFileName);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        teacher.setTeacherPassword(MD5Util.getHexPassword(teacherDTO.getTeacherPassword()));
        teacher.setRole(dbTeacher.getRole());
        teacher.setTeacherImg(newFileName);
        teacherDao.saveAndFlush(teacher);

    }

    @Override
    public void deleteTeacherById(Long id) {
        Teacher dbTeacher = teacherDao.findById(id).get();
        if(dbTeacher == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.TEACHER_NOT_FOUND);
        }
        fileUploadUtil.deletePhoto(fileUploadUtil.getUploadDir() + dbTeacher.getTeacherImg());
        teacherDao.deleteById(id);
    }

    private boolean isExistTeacherInDB(Long teacherId) {
        return (teacherDao.findById(teacherId).get() != null);
    }

}
