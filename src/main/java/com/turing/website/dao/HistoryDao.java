package com.turing.website.dao;


import com.turing.website.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jack
 * @date 2019-06-27-16:59
 */
@Repository
public interface HistoryDao extends JpaRepository<History, Long> {

}
