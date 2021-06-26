package com.turing.website.controller.admin;


import com.turing.website.dto.TeacherDTO;
import com.turing.website.service.admin.AdminTeacherService;
import com.turing.website.service.admin.impl.AdminTeacherServiceImpl;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@RequiresPermissions("admin")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping(value = "/admin/teacher")
@RestController
@Api(tags = "后台管理教师信息接口")
public class AdminTeacherController {

    @Autowired
    AdminTeacherService adminTeacherService;

    private final Logger logger = LoggerFactory.getLogger(AdminTeacherServiceImpl.class);

    @ApiOperation(value = "添加教师", notes = "不需要传递教师id")
    @PostMapping
    public JsonResultUtil addTeacher(@Validated TeacherDTO teacherDTO,
                                     BindingResult result,
                                     @RequestParam(value = "icon") MultipartFile icon
                                ){
        if(result.hasErrors()){
            JsonResultUtil jsonResultUtil = JsonResultUtil.errorOf(100, "数据格式出错!");
            result.getFieldErrors().forEach(fieldError ->
                jsonResultUtil.addObject(fieldError.getCode(),fieldError.getDefaultMessage()
            ));
            return jsonResultUtil;
        }
        adminTeacherService.addTeacher(teacherDTO, icon);
        return JsonResultUtil.success();

    }
    @ApiOperation(value = "更新教师信息")
    @PutMapping(value = "/{teacherId}")
    public JsonResultUtil updateTeacher(@Validated TeacherDTO teacherDTO,
                                        BindingResult result,
                                        @PathVariable("teacherId") Long teacherId,
                                        @RequestParam(value = "icon") MultipartFile icon){

        if(result.hasErrors()){
            JsonResultUtil jsonResultUtil = JsonResultUtil.errorOf(100, "数据格式出错!");
            result.getFieldErrors().forEach(fieldError ->
                    jsonResultUtil.addObject(fieldError.getCode(),fieldError.getDefaultMessage()
                    ));
            return jsonResultUtil;
        }

        adminTeacherService.updateTeacher(teacherDTO, icon);
        return JsonResultUtil.success();

    }
    @ApiOperation(value = "删除教师")
    @DeleteMapping(value = "/{teacherId}")
    public JsonResultUtil deleteTeacher(@PathVariable("teacherId") Long teacherId){
        adminTeacherService.deleteTeacherById(teacherId);
        return JsonResultUtil.success();
    }

}
