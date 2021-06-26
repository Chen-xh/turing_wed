package com.turing.website.controller.admin;



import com.turing.website.entity.Inform;
import com.turing.website.service.admin.AdminInformService;
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
@Api(tags = "后台管理通告信息接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/backside/inform")
public class AdminInformController  {

    @Autowired
    AdminInformService adminInformService;

    @ApiOperation(value = "发布或更新通告", notes = "不传递id时为发布, 否则为更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informCreateTime", value = "通告创建时间(不用传递,后台自动生成)", dataType = "date"),
            @ApiImplicitParam(name = "informUsername", value = "发布人,必须传递", dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 2006, message = "通告的信息填写不正确!请检查后重新尝试请求!"),
            @ApiResponse(code = 2007, message = "没有找到对应的通告, 请检查后再尝试!")
    })
    @PostMapping("")
    public JsonResultUtil publishOrUpdateInform(Inform inform){
        adminInformService.publishOrUpdateInform(inform);
        return JsonResultUtil.success();

    }
    @ApiOperation(value = "删除通告")
    @ApiImplicitParam(name = "informId", value = "通告id", paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 2007, message = "没有找到对应的通告, 请检查后再尝试!")
    })
    @DeleteMapping(value = "/{informId}")
    public JsonResultUtil deleteInform(@PathVariable(name = "informId") Long informId){

        adminInformService.deleteInform(informId);
        return JsonResultUtil.success();

    }

    @ApiOperation(value = "根据id获取通告详细信息")
    @ApiImplicitParam(name = "informId", value = "通告id", paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 2007, message = "没有找到对应的通告, 请检查后再尝试!")
    })
    @GetMapping("/{informId}")
    public JsonResultUtil getInformById(@PathVariable(name = "informId") Long informId){

        Inform inform = adminInformService.getInformById(informId);
        return JsonResultUtil.success().addObject("inform", inform);
    }

}
