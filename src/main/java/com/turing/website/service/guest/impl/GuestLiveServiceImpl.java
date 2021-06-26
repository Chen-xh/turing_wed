package com.turing.website.service.guest.impl;

import com.turing.website.dao.LiveDao;
import com.turing.website.entity.Live;
import com.turing.website.service.guest.GuestLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestLiveServiceImpl implements GuestLiveService {

    @Autowired
    LiveDao liveDao;

    @Override
    public List<Live> findAllLives() {

        List<Live> lives = liveDao.findAll();
        return lives;

    }

    @Override
    public Live findLiveByLiveId(Long liveId) {
        return liveDao.getOne(liveId);
    }

}
