package com.turing.website.controller.guest;

import com.turing.website.entity.Member;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestMemberService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/2 20:55
 * 前台团队成员接口
 */
@Api(tags = "获取团队成员信息接口")
@RestController
@RequestMapping("/guest/member")
@CrossOrigin
public class GuestMemberController {

    @Autowired
    GuestMemberService memberService;

    @ApiOperation(value = "根据成员id查询成员的具体信息")
    @ApiImplicitParam(name = "id", value = "成员id", required = true, paramType = "path", dataType = "long")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!")
    })
    @GetMapping(value = "/{id}")
    public JsonResultUtil findMemberById(@PathVariable Long id){

        Member member = memberService.findMemberById(id);
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("member",member);
        return jsonResultUtil;

    }
    @ApiOperation(value = "查询所有团队成员")
    @GetMapping(value = "")
    public JsonResultUtil findAllMembers(){
        List<Member> members = memberService.findAllMembers();
        return JsonResultUtil.success().addObject("members", members);
    }

    @ApiOperation(value = "根据技术方向查询队员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "technology", value = "技术方向",required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页(从0开始)", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "size", value = "每页显示几条信息(默认5条)",paramType = "query", dataType = "long"),
    })
    @GetMapping(value = "/tech")
    public JsonResultUtil findMembersByTechnology(@RequestParam(name = "technology") String technology,
                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size", defaultValue = "5") Integer size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Member> members = memberService.findMemberByTech(technology, pageRequest);
        if(members.getContent().size() == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("members", members);
        return jsonResultUtil;

    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页(从0开始)", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示几条信息(默认5条)",paramType = "query"),
    })
    @GetMapping(value = "/page")
    public JsonResultUtil findAllMembersByPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Member> members = memberService.findAllMembers(pageRequest);
        if(members.getContent().size() == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("members", members);
        return jsonResultUtil;
    }



}
