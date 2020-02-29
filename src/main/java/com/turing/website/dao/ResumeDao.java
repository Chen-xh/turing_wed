package com.turing.website.dao;


import com.turing.website.entity.Resume;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
public interface ResumeDao extends JpaRepository<Resume, Long> {

    Resume findResumeByResumeStudentId(@ApiParam(name = "resumeStudentId") String ResumeStudentId);

}
