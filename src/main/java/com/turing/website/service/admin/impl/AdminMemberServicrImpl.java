package com.turing.website.service.admin.impl;

import com.turing.website.dao.MemberDao;
import com.turing.website.dto.MemberDTO;
import com.turing.website.entity.Member;
import com.turing.website.entity.Role;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminMemberService;
import com.turing.website.util.FileUploadUtil;
import com.turing.website.util.MD5Util;
import com.turing.website.util.NotBlankUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminMemberServicrImpl implements AdminMemberService {
    @Autowired
    MemberDao memberDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Override
    public void updateMember(Long memberId, MemberDTO memberDTO, MultipartFile memberIcon) {
        if(!NotBlankUtil.checkObjAllFieldsWithoutPasswordIsNull(memberDTO)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_IS_NOT_CORRECT);
        }
        Member dbMember = memberDao.findById(memberId).get();
        if(dbMember == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        dbMember.setMemberName(memberDTO.getMemberName());
        dbMember.setMemberClass(memberDTO.getMemberClass());
        dbMember.setMemberIntroduction(memberDTO.getMemberIntroduction());
        dbMember.setMemberTechnology(memberDTO.getMemberTechnology());
        dbMember.setMemberMajor(memberDTO.getMemberMajor());
        dbMember.setBirthday(memberDTO.getBirthday());
        dbMember.setMemberStuId(memberDTO.getMemberStuId());
        String dbMemberIconName = dbMember.getMemberIcon();
        fileUploadUtil.deletePhoto(dbMemberIconName);
        String newIconName = fileUploadUtil.getNewFileName(memberIcon);
        fileUploadUtil.uploadOneFile(memberIcon, newIconName);
        dbMember.setMemberIcon(newIconName);
        memberDao.saveAndFlush(dbMember);
    }

    @Override
    public void addMember(MemberDTO memberDTO, MultipartFile memberIcon) {
        if(!NotBlankUtil.checkObjAllFieldsWithoutIdIsNull(memberDTO)){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_IS_NOT_CORRECT);
        }

        Member member = new Member();
        BeanUtils.copyProperties(memberDTO, member);
        //MD5加密成员密码
        String hexPassword = MD5Util.getHexPassword(memberDTO.getPassword());
        member.setPassword(hexPassword);
        //设置成员头像
        String memberIconName = fileUploadUtil.getNewFileName(memberIcon);
        member.setMemberIcon(memberIconName);
        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("学生");
        member.setRole(role);
        memberDao.save(member);
        fileUploadUtil.uploadOneFile(memberIcon, memberIconName);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member;
        try {
            member = memberDao.findById(memberId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        fileUploadUtil.deletePhoto(fileUploadUtil.getUploadDir() + member.getMemberIcon());
        memberDao.deleteById(memberId);
    }
}
