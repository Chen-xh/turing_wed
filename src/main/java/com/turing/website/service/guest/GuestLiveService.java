package com.turing.website.service.guest;


import com.turing.website.entity.Live;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestLiveService {

    /**
     * 查询所有团队生活
     * @return
     */
    List<Live> findAllLives();

    Live findLiveByLiveId(Long liveId);
}
