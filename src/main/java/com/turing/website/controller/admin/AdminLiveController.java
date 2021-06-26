package com.turing.website.controller.admin;


import com.turing.website.dto.LiveDTO;
import com.turing.website.entity.Live;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminLiveService;
import com.turing.website.util.JsonResultUtil;
import com.turing.website.util.NotBlankUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@RequiresPermissions("admin")
@Api(tags = "后台管理生活信息接口")
@RestController
@RequestMapping("/backside/live")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminLiveController {

    @Autowired
    AdminLiveService adminLiveService;

    @ApiOperation(value = "添加或更新团队活动接口", notes = "不传递id时就是添加,否则就是更新(需使用postman测试此接口)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "liveId", value = "活动Id", dataType = "long"),
            @ApiImplicitParam(name = "liveName", value = "活动名字", required = true, dataType = "string"),
            @ApiImplicitParam(name = "liveContent", value = "活动内容", required = true, dataType = "string"),
            @ApiImplicitParam(name = "liveTime", value = "活动时间(yyyy-MM-dd)", required = true, dataType = "date")
    })
    @ApiResponses({
            @ApiResponse(code = 2008, message = "必须上传图片!"),
            @ApiResponse(code = 2009, message = "团队活动的信息填写不正确!请检查后重新尝试请求!"),
            @ApiResponse(code = 3001, message = "没有找到对应的团队活动记录, 请检查后再尝试请求")
    })
    @PostMapping(value = "",headers = "content-type=multipart/form-data")
    public JsonResultUtil addOrUpdateLive(  LiveDTO liveDTO,
                                          @RequestParam("livePhotos") MultipartFile[] livePhotos){

        if(livePhotos.length == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PHOTO_IS_NULL);
        }
        if(!NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(liveDTO)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LIVE_IS_NOT_CORRECT);
        }

        adminLiveService.addOrUpdateLive(liveDTO, livePhotos);

        return JsonResultUtil.success();
    }

    @ApiOperation(value = "删除团队活动")
    @ApiImplicitParam(name = "liveId", value = "活动Id",required = true, paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 3001, message = "没有找到对应的团队活动记录, 请检查后再尝试请求")
    })
    @DeleteMapping("/{liveId}")
    public JsonResultUtil deleteLive(@PathVariable(name = "liveId") Long liveId){
        adminLiveService.deleteLive(liveId);
        return JsonResultUtil.success();

    }

    @ApiOperation(value = "根据id查找团队活动记录")
    @ApiImplicitParam(name = "liveId", value = "活动Id",required = true, paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 3001, message = "没有找到对应的团队活动记录, 请检查后再尝试请求")
    })
    @GetMapping("/{liveId}")
    public JsonResultUtil getLiveById(@PathVariable(name = "liveId") Long liveId){

        Live live = adminLiveService.getLiveById(liveId);
        return JsonResultUtil.success().addObject("live", live);
        
    }

}
