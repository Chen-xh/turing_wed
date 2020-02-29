package com.turing.website.dao;


import com.turing.website.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */

public interface LiveDao extends JpaRepository<Live, Long> {
}

