package com.turing.website.realm;

import com.turing.website.dao.MemberDao;
import com.turing.website.dao.TeacherDao;
import com.turing.website.entity.Member;
import com.turing.website.entity.Permission;
import com.turing.website.entity.Teacher;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeAuthenticationException;

import com.turing.website.util.JWTToken;
import com.turing.website.util.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * @author CHEN
 * @date 2020/2/29 17:50
 * 创建shiro的自定义的Realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    MemberDao memberDao;
    @Autowired
    TeacherDao teacherDao;

    private Map<String,String> usertype;
    private static final Logger PLOG = LoggerFactory.getLogger(CustomRealm.class);


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        
        PLOG.info("Shiro >> 身份认证");
        String tokens=(String) token.getCredentials();
        if (tokens == null) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_LOGIN);
        }

        String username =JWTUtil.getUsername(tokens);
        String type =JWTUtil.getType(tokens);
        if (username == null||type==null) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_LOGIN);
        }

        String password ;
        if(type.equals("student")) {
//         从数据库获取对应用户名密码的用户
            Member member = memberDao.findByMemberStuIdEquals(username);
            if (null == member) {
                throw new CustomizeAuthenticationException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
            }
            password = member.getPassword();
        }else if(type.equals("teacher")){
            Teacher teacher = teacherDao.findTeacherByTeacherEmailEquals(username);
            if (null == teacher) {
                throw new CustomizeAuthenticationException(MyCustomizeErrorCode.TEACHER_NOT_FOUND);
            }
            password = teacher.getTeacherPassword();
        }else{
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
        }

        if (!JWTUtil.verify(tokens, username, password)) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.PASS_NOT_CORRECT);
//            throw new IncorrectCredentialsException();
        }
        usertype=new HashMap<>();
        usertype.put(username,type);
        return new SimpleAuthenticationInfo(username,tokens, getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        PLOG.info("Shiro >> 权限认证");
        String username = principalCollection.toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        List<Permission> power;
        if(usertype.get(username).equals("student")) {
            Member member = memberDao.findByMemberStuIdEquals(username);
            if (null == member) {
                throw new CustomizeAuthenticationException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
            }
            power = member.getRole().getPermissions();
        }else if(usertype.get(username).equals("teacher")){
            Teacher teacher = teacherDao.findTeacherByTeacherEmailEquals(username);
            if (null == teacher) {
                throw new CustomizeAuthenticationException(MyCustomizeErrorCode.TEACHER_NOT_FOUND);
            }
            power = teacher.getRole().getPermissions();
        }else{
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
        }

//        Set<String> set = new HashSet<>();
//        for (Permission s:power) {
//            set.add(s.getPermission());
//        }
//        set.add("user");
//        info.setRoles(set);

        for (Permission p : power) {
            info.addStringPermission(p.getPermission());
         }
        info.addStringPermission("user");
        return info;
    }


}

