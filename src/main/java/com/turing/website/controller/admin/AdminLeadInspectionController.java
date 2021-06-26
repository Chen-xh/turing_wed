package com.turing.website.controller.admin;


import com.turing.website.dto.LeadInspectionDTO;
import com.turing.website.service.admin.AdminLeadInspectionService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@RequiresPermissions("admin")
@Api(tags = "后台管理领导视察接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/backside/leadInspection")
public class AdminLeadInspectionController {
    @Autowired
    AdminLeadInspectionService leadInspectionService;
    @ApiOperation(value = "添加领导视察内容")
    @PostMapping("")
    public JsonResultUtil addLeadInspection(LeadInspectionDTO leadInspectionDTO,
                                            @RequestParam(name = "img") MultipartFile img){
        leadInspectionService.addLeadInspection(leadInspectionDTO, img);
        return JsonResultUtil.success();
    }
    @ApiOperation(value = "更新领导视察内容")
    @PutMapping("/{id}")
    public JsonResultUtil updateLeadInspection(@PathVariable Long id,  LeadInspectionDTO leadInspectionDTO,
                                               @RequestParam(name = "img") MultipartFile img){
        leadInspectionService.updateLeadInspection(leadInspectionDTO, img);
        return JsonResultUtil.success();
    }
    @ApiOperation(value = "删除领导视察内容")
    @DeleteMapping("/{id}")
    public JsonResultUtil deleteLeadInspection(@PathVariable Long id){
        leadInspectionService.deleteLeadInspection(id);
        return JsonResultUtil.success();
    }

}
