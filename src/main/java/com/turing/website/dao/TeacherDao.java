package com.turing.website.dao;


import com.turing.website.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Jack
 * @date 2019-10-29-22:24
 */
public interface TeacherDao extends JpaRepository<Teacher, Long> {

    Teacher findTeacherByTeacherEmailEquals(@Param("teacherEmail") String teacherEmail);

}
