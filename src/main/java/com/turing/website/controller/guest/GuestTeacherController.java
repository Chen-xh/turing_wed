package com.turing.website.controller.guest;


import com.turing.website.entity.Teacher;
import com.turing.website.service.guest.GuestTeacherService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "获取教师信息接口")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping(value = "/guest/teacher")
public class GuestTeacherController {

    @Autowired
    GuestTeacherService teacherService;

    @ApiOperation(value = "查询所有教师")
    @GetMapping
    public JsonResultUtil findAllTeachers(){

        List<Teacher> allTeachers = teacherService.findAllTeachers();
        return JsonResultUtil.success().addObject("allTeachers", allTeachers);

    }

    @ApiOperation(value = "根据教师id查询教师")
    @GetMapping("/{id}")
    public JsonResultUtil findTeacherById(@PathVariable(value = "id") Long id){

        Teacher teacher = teacherService.findTeacherById(id);
        return JsonResultUtil.success().addObject("teacher", teacher);

    }

}
