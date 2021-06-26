package com.turing.website.service.admin;

import com.turing.website.dto.MemberDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminMemberService {
    void updateMember(Long memberId, MemberDTO memberDTO, MultipartFile memberIcon);

    void addMember(MemberDTO memberDTO, MultipartFile memberIcon);

    void deleteMember(Long memberId);
}
