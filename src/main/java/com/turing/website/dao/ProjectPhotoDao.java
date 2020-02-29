package com.turing.website.dao;


import com.turing.website.entity.ProjectPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */

public interface ProjectPhotoDao extends JpaRepository<ProjectPhoto, Long> {
}
