package com.turing.website.controller.guest;


import com.turing.website.entity.LeadInspection;
import com.turing.website.service.guest.GuestLeadInspectionService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "获取领导视察内容接口")
@RestController
@RequestMapping("/guest/leadInspection")
public class GuestLeadInspectionController {

    @Autowired
    GuestLeadInspectionService inspectionService;

    @GetMapping("")
    public JsonResultUtil findAllLeadInspcetions(){
        List<LeadInspection> inspections = inspectionService.findAllInspections();
        return JsonResultUtil.success().addObject("inspections", inspections);
    }
    @GetMapping("/{id}")
    public JsonResultUtil findLeadInspectionById(@PathVariable Long id){
        LeadInspection inspection = inspectionService.findInspectionById(id);
        return JsonResultUtil.success().addObject("inspection", inspection);
    }

}
