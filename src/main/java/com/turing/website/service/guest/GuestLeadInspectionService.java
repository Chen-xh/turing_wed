package com.turing.website.service.guest;


import com.turing.website.entity.LeadInspection;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestLeadInspectionService {

    List<LeadInspection> findAllInspections();

    LeadInspection findInspectionById(Long id);

}
