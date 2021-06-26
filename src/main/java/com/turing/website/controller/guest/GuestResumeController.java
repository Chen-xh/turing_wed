package com.turing.website.controller.guest;


import com.turing.website.dto.ResumeDTO;
import com.turing.website.entity.Resume;
import com.turing.website.service.guest.GuestResumeService;
import com.turing.website.util.ExcelUtil;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "前台投递简历接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/guest/resume")
public class GuestResumeController {
    @Autowired
    GuestResumeService resumeService;

    @PostMapping("")
    public JsonResultUtil postResume(@Valid @RequestBody ResumeDTO resumeDTO,
                                     BindingResult result){
        JsonResultUtil jsonResultUtil;
        if(!result.hasErrors()){
            resumeService.postResume(resumeDTO);
            jsonResultUtil = JsonResultUtil.success();
        }else{
            jsonResultUtil = JsonResultUtil.fail();
            int index = 1;
            for (FieldError error : result.getFieldErrors()) {
                jsonResultUtil.addObject(error.getCode()+index, error.getDefaultMessage());
                index++;
            }
        }
        return jsonResultUtil;
    }
    @PostMapping("/getResume")
    public JsonResultUtil get(String studentId){
        JsonResultUtil jsonResultUtil;
        Resume r=resumeService.getByStudentId(studentId);
        jsonResultUtil = JsonResultUtil.success().addObject("resume",r);
        return jsonResultUtil;
    }
    @PostMapping("/getResumeToExcel")
    public JsonResultUtil getResumeToExcel(){
        resumeService.getAllToExcel("D:/resume.xls");
        return JsonResultUtil.success();
    }
}
