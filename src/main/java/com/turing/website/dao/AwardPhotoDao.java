package com.turing.website.dao;


import com.turing.website.entity.Award;
import com.turing.website.entity.AwardPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
public interface AwardPhotoDao extends JpaRepository<AwardPhoto,Long>, JpaSpecificationExecutor<Award> {
}
