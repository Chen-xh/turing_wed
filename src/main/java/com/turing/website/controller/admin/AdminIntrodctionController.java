package com.turing.website.controller.admin;


import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.service.admin.AdminIntroductionService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@RequiresPermissions("admin")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
@Api(tags = "后台管理团队简介信息接口")
@RequestMapping(value = "/backside/introduction")
public class AdminIntrodctionController {

    @Autowired
    AdminIntroductionService adminIntroductionService;

    @ApiOperation(value = "更新团队简介接口",notes = "更新团队简介信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "introductionId", value = "历史记录id", required = true, paramType = "path", dataType = "long"),
            @ApiImplicitParam(name = "introductionInfo", value = "内容", required = true, paramType = "query", dataType = "string"),
    })
    @ApiResponses({
            @ApiResponse(code = 2004, message = "团队历史介绍信息不能为空!")
    })
    @PutMapping("/{introductionId}")
    public JsonResultUtil updateIntroduction(@PathVariable Long introductionId,
                                             @RequestParam("introductionInfo") String introductionInfo){

        if("".equals(introductionInfo.trim())){
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.HISTORY_INFO_IS_NULL);
        }
        adminIntroductionService.updateIntroduction(introductionId, introductionInfo);
        return JsonResultUtil.success();

    }

}
