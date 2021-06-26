package com.turing.website.service.guest.impl;

import com.turing.website.dao.TeacherDao;
import com.turing.website.entity.Teacher;
import com.turing.website.service.guest.GuestTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestTeacherServiceImpl implements GuestTeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher findTeacherById(Long id) {
        Teacher teacher = teacherDao.findById(id).get();
        System.out.println(teacher.getRole().getRoleName());
        return teacher;
    }
}
