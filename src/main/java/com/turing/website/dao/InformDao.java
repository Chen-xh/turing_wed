package com.turing.website.dao;


import com.turing.website.entity.Inform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
public interface InformDao extends JpaRepository<Inform, Long> {

    @Query(value = "SELECT * FROM inform ORDER BY inform_create_time DESC", nativeQuery = true)
    List<Inform> findAllInformsOrOrderByInformCreateTimeDESC();
    @Query(value = "SELECT * FROM inform ORDER BY inform_create_time DESC LIMIT 1", nativeQuery = true)
    Inform findInformByInformCreateTime();
    @Query(value = "SELECT * FROM inform ORDER BY inform_create_time DESC LIMIT 7", nativeQuery = true)
    List<Inform> findInformsOrderByInformCreateTimeDESC();

}
