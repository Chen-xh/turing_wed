package com.turing.website.service.admin;

import com.turing.website.dto.AwardDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminAwardService {

    void addOrUpdateAward(AwardDTO awardDTO, MultipartFile[] awardPhotos);

    void deleteAward(Long awardId);

    AwardDTO findAwardWithMembers(Long id);

}
