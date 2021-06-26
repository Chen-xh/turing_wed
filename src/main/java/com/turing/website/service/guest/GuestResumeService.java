package com.turing.website.service.guest;


import com.turing.website.dto.ResumeDTO;
import com.turing.website.entity.Resume;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestResumeService {

    void postResume(ResumeDTO resumeDTO);
    String uploadResume( String resumeStudentId,int type);

    Resume getByStudentId(String resumeStudentId);
    void getAllToExcel(String path);

}
