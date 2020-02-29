package com.turing.website.dao;


import com.turing.website.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
public interface MemberDao extends JpaRepository<Member, Long>, PagingAndSortingRepository<Member, Long> {

    Page<Member> findMembersByMemberTechnology(@Param("technology") String technology, Pageable pageable);

    Member findByMemberNameEquals(@Param("memberName") String memberName);

    Member findByMemberStuIdEquals(@Param("memberStuId") String memberStuId);
}
