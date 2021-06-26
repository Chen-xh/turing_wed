package com.turing.website.service.guest.impl;

import com.turing.website.dao.ResumeDao;
import com.turing.website.dto.ResumeDTO;
import com.turing.website.entity.Resume;

import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestResumeService;
import com.turing.website.util.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestResumeServiceImpl implements GuestResumeService {

    @Autowired
    ResumeDao resumeDao;

    @Override
    public void postResume(ResumeDTO resumeDTO) {

        Resume resume = new Resume();
        Resume dbResume = resumeDao.findResumeByResumeStudentId(resumeDTO.getResumeStudentId());
        if(dbResume != null){
            resumeDao.delete(dbResume);
//            throw new CustomizeRuntimeException(MyCustomizeErrorCode.RESUME_REPEAT);
        }
            BeanUtils.copyProperties(resumeDTO, resume);
            resumeDao.save(resume);

    }

    @Override
    public String uploadResume(String resumeStudentId,int type) {

        return null;
    }

    @Override
    public Resume getByStudentId(String resumeStudentId) {
        Resume r=resumeDao.findResumeByResumeStudentId(resumeStudentId);
        if(r==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.RESUME_NOT_FIND);
        }
        return r;
    }

    @Override
    public void getAllToExcel(String path) {
        String[] header = {"姓名", "学号", "联系电话", "专业班级", "方向", "自我评价", "技能水平", "期望", "项目经验", "其他"};
        ExcelUtil.creatExcel(path,header,"简历详情",resumeDao.findAll());

    }
}
