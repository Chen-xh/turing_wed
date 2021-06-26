package com.turing.website.service.admin.impl;


import com.turing.website.dao.LeadInspectionDao;
import com.turing.website.dto.LeadInspectionDTO;
import com.turing.website.entity.LeadInspection;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminLeadInspectionService;
import com.turing.website.util.FileUploadUtil;
import com.turing.website.util.NotBlankUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminLeadInspectionServiceImpl implements AdminLeadInspectionService {
    @Autowired
    LeadInspectionDao leadInspectionDao;
    @Autowired
    FileUploadUtil fileUploadUtil;

    public void addLeadInspection(LeadInspectionDTO leadInspectionDTO, MultipartFile img){
        if (NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(leadInspectionDTO)) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LEADINSPECTION_IS_NOT_FOUND);
        }
        LeadInspection leadInspection = new LeadInspection();
        BeanUtils.copyProperties(leadInspectionDTO, leadInspection);
        String newFileName = fileUploadUtil.getNewFileName(img);
        fileUploadUtil.uploadOneFile(img, newFileName);
        leadInspection.setImg(newFileName);
        leadInspectionDao.save(leadInspection);
    }

    @Override
    public void updateLeadInspection(LeadInspectionDTO leadInspectionDTO, MultipartFile img) {
        LeadInspection leadInspection = leadInspectionDao.findById(leadInspectionDTO.getId()).get();
        if(!NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(leadInspectionDTO)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LEADINSPECTION_IS_NOT_FOUND);
        }
        String dbImg = leadInspection.getImg();
        fileUploadUtil.deletePhoto(dbImg);
        String newImgName = fileUploadUtil.getNewFileName(img);
        BeanUtils.copyProperties(leadInspectionDTO, leadInspection);
        leadInspection.setImg(newImgName);
        fileUploadUtil.uploadOneFile(img, newImgName);
        leadInspectionDao.saveAndFlush(leadInspection);
    }

    @Override
    public void deleteLeadInspection(Long id) {
        LeadInspection leadInspection = leadInspectionDao.findById(id).get();
        fileUploadUtil.deletePhoto(leadInspection.getImg());
        leadInspectionDao.delete(leadInspection);
    }

}
