package com.turing.website.service.admin;

import com.turing.website.dto.LeadInspectionDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminLeadInspectionService {

    void addLeadInspection(LeadInspectionDTO leadInspectionDTO, MultipartFile img);

    void updateLeadInspection(LeadInspectionDTO leadInspectionDTO, MultipartFile img);

    void deleteLeadInspection(Long id);

}
