package com.turing.website.service.guest;

import com.turing.website.entity.Teacher;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestTeacherService {

    List<Teacher> findAllTeachers();

    Teacher findTeacherById(Long id);

}
