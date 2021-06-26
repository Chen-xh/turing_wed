package com.turing.website.service.guest.impl;


import com.turing.website.dao.LeadInspectionDao;
import com.turing.website.entity.LeadInspection;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestLeadInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestLeadInspectionServiceImpl implements GuestLeadInspectionService {
    @Autowired
    LeadInspectionDao leadInspectionDao;

    @Override
    public List<LeadInspection> findAllInspections() {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        List<LeadInspection> leadInspectionList = leadInspectionDao.findAll(sort);
        return leadInspectionList;
    }

    @Override
    public LeadInspection findInspectionById(Long id) {
        LeadInspection leadInspection = leadInspectionDao.findById(id).get();
        if(leadInspection == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LEADINSPECTION_NOT_FOUND);
        }
        return leadInspection;
    }
}
