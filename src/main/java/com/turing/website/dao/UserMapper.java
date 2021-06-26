package com.turing.website.dao;


import com.turing.website.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<Member, Integer> {
    Member findByMemberStuId(String username);
}
