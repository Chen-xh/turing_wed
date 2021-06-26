package com.turing.website.controller.admin;


import com.turing.website.dto.AwardDTO;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminAwardService;
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
@RequestMapping("/backside/award")
@Api(tags = "后台管理奖项信息接口")
public class AdminAwardController {

    @Autowired
    AdminAwardService adminAwardService;

    @ApiOperation(value = "删除奖项")
    @ApiImplicitParam(name = "awardId", value = "奖项Id", paramType = "path", dataType = "long", required = true)
    @ApiResponses({
            @ApiResponse(code = 2002, message = "没有找到对应的奖项,请检查后重新尝试请求!")
    })
    @DeleteMapping("/{awardId}")
    public JsonResultUtil delteAward(@PathVariable Long awardId){

        adminAwardService.deleteAward(awardId);
        return JsonResultUtil.success();

    }

    @ApiOperation(value = "添加或更新奖项",notes = "不传递Id时就是添加,否则就是更新,(需使用postman测试此接口)")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "奖项信息填写不正确!"),
            @ApiResponse(code = 2008, message = "必须上传图片!"),
            @ApiResponse(code = 500, message = "服务器冒烟了...要不等它降降温后再来访问?"),
            @ApiResponse(code = 404, message = "你要请求的页面好像暂时飘走了...要不试试请求其它页面?"),
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
    })
    @PostMapping(value = "")
    public JsonResultUtil addOrUpdateAward( AwardDTO awardDTO,
                                           @ApiParam(name = "awardPhotos", value = "获奖图片(Postman测试)", required = true, allowMultiple = true)
                                      @RequestParam(name = "awardPhotos")MultipartFile[] awardPhotos){
        //检查参数合法性
        if(awardDTO.getAwardId() == null){
            if(!NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(awardDTO)){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.AWARD_IS_NOT_CORRECT);

            }
        }else{
            if(!NotBlankUtil.checkObjAllFieldsIsNull(awardDTO)){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.AWARD_IS_NOT_CORRECT);
            }
        }

        if(awardPhotos.length == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PHOTO_IS_NULL);
        }
        adminAwardService.addOrUpdateAward(awardDTO, awardPhotos);
        return JsonResultUtil.success();

    }
//
//    @ApiOperation(value = "根据奖项id查询某一个奖项(查看奖项详细信息,包括获奖的同学信息)")
//    @ApiImplicitParam(name = "awardId", value = "奖项Id", paramType = "path", dataType = "long", required = true)
//    @ApiResponses({
//            @ApiResponse(code = 2002, message = "没有找到对应的奖项,请检查后重新尝试请求!")
//    })
//    @GetMapping("/{awardId}")
//    public JsonResultUtil findAwardById(@PathVariable Long awardId){
//
//        AwardDTO awardDTO = adminAwardService.findAwardWithMembers(awardId);
//        return JsonResultUtil.success().addObject("award", awardDTO);
//
//    }



}
