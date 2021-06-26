package com.turing.website.service.admin;

import com.turing.website.dto.TeacherDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminTeacherService {

    void addTeacher(TeacherDTO teacherDTO, MultipartFile teacherIcon);

    void updateTeacher(TeacherDTO teacherDTO, MultipartFile teacherIcon);

    void deleteTeacherById(Long id);

}