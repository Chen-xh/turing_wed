package com.turing.website.controller.admin;

import com.turing.website.dao.MemberDao;
import com.turing.website.dao.TeacherDao;
import com.turing.website.dto.MemberDTO;
import com.turing.website.dto.PeopleDTO;
import com.turing.website.dto.TeacherDTO;
import com.turing.website.entity.Member;
import com.turing.website.entity.Teacher;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.util.JWTToken;
import com.turing.website.util.JWTUtil;
import com.turing.website.util.JsonResultUtil;
import com.turing.website.util.MD5Util;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CHEN
 * @date 2020/3/2 20:55
 */
@Api(tags = "管理员登录接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminLoginController  {
    @Autowired
    MemberDao memberDao;
    @Autowired
    TeacherDao teacherDao;

    private static final Logger PLOG = LoggerFactory.getLogger(AdminLoginController.class);

    @ApiOperation(value = "团队成员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(学号)",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
            @ApiResponse(code = 3005, message = "密码不正确!请重新尝试!")
    })
    @PostMapping("/teamMember/login")
    public JsonResultUtil teamMemberLogin(@RequestParam(name = "username") String username,
                                          @RequestParam(name = "password") String password){

        String passwords = MD5Util.getHexPassword(password);
        System.out.println(passwords);
        JWTToken token=new JWTToken(JWTUtil.sign(username, passwords,"student"));
//        JWTToken token=new JWTToken(JWTUtil.sign(username, passwords));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);
        Member dbMember = memberDao.findByMemberStuIdEquals(username);
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(dbMember, memberDTO);
        memberDTO.setRoleName(dbMember.getRole().getRoleName());


        PLOG.info("UserController >> login · 获取Token");

        return JsonResultUtil.success().addObject("member", memberDTO).addObject("token", token.getPrincipal());

    }
    @ApiOperation(value = "教师登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "教师用户名(邮箱)",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",required = true, paramType = "query", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 2001, message = "找不到对应成员, 请检查后重新尝试请求!"),
            @ApiResponse(code = 3005, message = "密码不正确!请重新尝试!")
    })
    @PostMapping("/teamTeacher/login")
    public JsonResultUtil teamTeacherLogin(@RequestParam(name = "username")String username,
                                           @RequestParam(name = "password")String password){

        String passwords = MD5Util.getHexPassword(password);
        JWTToken token=new JWTToken(JWTUtil.sign(username, passwords,"teacher"));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);

        Teacher dbTeacher = teacherDao.findTeacherByTeacherEmailEquals(username);
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(dbTeacher, teacherDao);
        teacherDTO.setRoleName(dbTeacher.getRole().getRoleName());

        PLOG.info("UserController >> login · 获取Token");
        return JsonResultUtil.success().addObject("teacher", teacherDTO).addObject("token", token.getPrincipal());

    }

    @ApiOperation(value = "退出登录")
    @ApiResponses(
            @ApiResponse(code = 3006, message = "尚未登录")
    )
    @PostMapping("/logout")
    public JsonResultUtil logout(HttpServletRequest request){
        PeopleDTO member = (PeopleDTO) request.getSession().getAttribute("member");
        if(member == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_LOGIN);
        }
        return JsonResultUtil.success();
    }
}

