package com.turing.website.controller.guest;


import com.turing.website.entity.Project;
import com.turing.website.service.guest.GuestProjectService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "获取团队项目展示信息接口")
@RestController
@RequestMapping("/guest/project")
@CrossOrigin
public class GuestProjectController {

    @Autowired
    GuestProjectService projectService;

    @ApiOperation(value = "根据项目id获取项目信息")
    @ApiImplicitParam(name = "id", value = "项目id", required = true, paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 3003, message = "没有找到对应项目, 请重新检查后再尝试请求!")
    })
    @GetMapping("/{id}")
    public JsonResultUtil getProjectById(@PathVariable Long id){

        Project project = projectService.findProjectById(id);
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("project", project);
        return jsonResultUtil;

    }
    @ApiOperation(value = "获取全部项目信息")
    @GetMapping("")
    public JsonResultUtil findAllProjects(){

        List<Project> projects = projectService.findAllProjects();
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("projects", projects);
        return jsonResultUtil;

    }

}
