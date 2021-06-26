package com.turing.website.service.guest;

import com.turing.website.entity.Award;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestAwardService {

    /**
     * 获取所有奖项
     * @return
     */
    List<Award> findAllAwards();

    Award findAwardByAwardId(Long awardId);
}
