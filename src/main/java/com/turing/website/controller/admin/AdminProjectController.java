package com.turing.website.controller.admin;


import com.turing.website.dto.ProjectDTO;
import com.turing.website.entity.Project;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminProjectService;
import com.turing.website.util.JsonResultUtil;
import com.turing.website.util.NotBlankUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@RequiresPermissions("admin")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/backside/project")
@Api(tags = "后台管理项目信息接口")
public class AdminProjectController {

    @Autowired
    AdminProjectService adminProjectService;

    @ApiOperation(value = "添加或更新项目",notes = "不传递Id时就是添加,否则就是更新,(需使用postman测试此接口)")
    @ApiResponses({
            @ApiResponse(code = 3002, message = "项目的信息不正确!请检查后重新尝试请求!"),
            @ApiResponse(code = 2008, message = "必须上传图片!"),
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
            @ApiResponse(code = 3003, message = "没有找到对应项目, 请重新检查后再尝试请求!")
    })
    @PostMapping(value = "", headers = "content-type=multipart/form-data")
    public JsonResultUtil addOrUpdateProject(  ProjectDTO projectDTO,
                                             @ApiParam(name = "projectPhotos", value = "项目图片(Postman测试)", required = true, allowMultiple = true)
                                        @RequestParam("projectPhotos") MultipartFile[] projectPhotos){

        if(!NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(projectDTO)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PROJECT_IS_NOT_CORRECT);
        }
        if(projectPhotos.length == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PHOTO_IS_NULL);
        }

        adminProjectService.addOrUpdateProject(projectDTO, projectPhotos);
        return JsonResultUtil.success();
    }
    @ApiOperation(value = "根据项目id删除某一个项目")
    @ApiImplicitParam(name = "projectId", value = "项目Id", paramType = "path", dataType = "long",defaultValue = "1", required = true)
    @ApiResponses({
            @ApiResponse(code = 3003, message = "没有找到对应项目, 请重新检查后再尝试请求!")
    })
    @DeleteMapping("/{projectId}")
    public JsonResultUtil deleteProject(@PathVariable Long projectId){

        adminProjectService.deleteProject(projectId);
        return JsonResultUtil.success();

    }
    @ApiOperation(value = "根据项目id查询某一个项目(查看项目详细信息)")
    @ApiImplicitParam(name = "projectId", value = "项目Id", paramType = "path", dataType = "long",defaultValue = "1", required = true)
    @ApiResponses({
            @ApiResponse(code = 3003, message = "没有找到对应项目, 请重新检查后再尝试请求!")
    })
    @GetMapping("/{projectId}")
    public JsonResultUtil getProjectById(@PathVariable Long projectId){

        Project project = adminProjectService.getProjectById(projectId);
        return JsonResultUtil.success().addObject("project", project);
    }

}
