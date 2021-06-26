package com.turing.website.service.guest.impl;

import com.turing.website.dao.MemberDao;
import com.turing.website.entity.Member;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:56
 */
@Service
public class GuestMemberServiceImpl implements GuestMemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public Member findMemberById(Long id) {
        Member member = memberDao.findById(id).get();
        if(member == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        return member;

    }

    @Override
    public Page<Member> findAllMembers(Pageable pageRequest) {
        Page<Member> members = memberDao.findAll(pageRequest);
        return members;
    }

    @Override
    public List<Member> findAllMembers() {
        return memberDao.findAll();
    }

    @Override
    public Page<Member> findMemberByTech(String technology, Pageable pageRequest) {
        Page<Member> members = memberDao.findMembersByMemberTechnology(technology, pageRequest);
        return members;
    }
}
