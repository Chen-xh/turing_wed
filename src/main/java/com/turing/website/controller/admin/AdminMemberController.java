package com.turing.website.controller.admin;


import com.turing.website.dto.MemberDTO;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminMemberService;
import com.turing.website.util.JsonResultUtil;
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
@RequestMapping("/admin/member")
@Api(tags = "后台管理团队成员接口")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;

    @ApiOperation(value = "根据成员id更新成员信息")
    @ApiResponses({
            @ApiResponse(code = 2008, message = "必须上传图片!"),
            @ApiResponse(code = 3004, message = "成员信息填写不正确!"),
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
    })
    @PutMapping(value = "/{memberId}")
    public JsonResultUtil updateMemberById(
            @PathVariable("memberId")Long memberId,
            MemberDTO memberDTO,
            @RequestParam("memberIcon") MultipartFile memberIcon){
        if(memberIcon==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PHOTO_IS_NULL);
        }
        adminMemberService.updateMember(memberId, memberDTO, memberIcon);
        return JsonResultUtil.success();

    }

    @ApiOperation(value = "添加成员")
    @ApiResponses({
            @ApiResponse(code = 2008, message = "必须上传图片!"),
            @ApiResponse(code = 3004, message = "成员信息填写不正确!"),
    })
    @PostMapping(value = "")
    public JsonResultUtil addMember( MemberDTO memberDTO,
                                    @RequestParam(value = "memberIcon")MultipartFile memberIcon){
        if(memberIcon==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.PHOTO_IS_NULL);
        }
        adminMemberService.addMember(memberDTO, memberIcon);
        return JsonResultUtil.success();

    }

    @ApiOperation(value = "根据成员id删除成员")
    @ApiImplicitParam(name = "memberId", value = "成员id", required = true, paramType = "query", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
    })
    @DeleteMapping("/{memberId}")
    public JsonResultUtil deleteMember(@PathVariable(name = "memberId") Long memberId){

        adminMemberService.deleteMember(memberId);
        return JsonResultUtil.success();

    }


}
