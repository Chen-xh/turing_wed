package com.turing.website.service.admin;


import com.turing.website.dto.LiveDTO;
import com.turing.website.entity.Live;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminLiveService {

    void addOrUpdateLive(LiveDTO liveDTO, MultipartFile[] livePhotos);

    void deleteLive(Long liveId);

    Live getLiveById(Long liveId);
}
