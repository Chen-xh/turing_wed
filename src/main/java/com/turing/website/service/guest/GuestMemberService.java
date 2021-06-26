package com.turing.website.service.guest;

import com.turing.website.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestMemberService {
    /**
     * 根据成员id查找成员
     * @param id 成员id号
     * @return
     */
    Member findMemberById(Long id);

    /**
     * 分页查询所有团队成员
     * @return
     * @param pageRequest
     */
    Page<Member> findAllMembers(Pageable pageRequest);

    /**
     * 查询所有团队队员
     * @return
     */
    List<Member> findAllMembers();

    /**
     * 根据技术方向查找团队成员
     * @param technology 技术方向
     * @param pageRequest
     * @return
     */
    Page<Member> findMemberByTech(String technology, Pageable pageRequest);
}
