package com.turing.website.dao;


import com.turing.website.entity.LivePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Repository
public interface LivePhotoDao extends JpaRepository<LivePhoto, Long> {
}
