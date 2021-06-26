package com.turing.website.service.guest.impl;


import com.turing.website.dao.AwardDao;
import com.turing.website.entity.Award;
import com.turing.website.service.guest.GuestAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:56
 */
@Service
public class GuestAwardServiceimpl implements GuestAwardService {

    @Autowired
    AwardDao awardDao;

    @Override
    public List<Award> findAllAwards() {

        List<Award> awards = awardDao.findAll();
        return awards;

    }

    @Override
    public Award findAwardByAwardId(Long awardId) {
        Award dbAward = awardDao.getOne(awardId);
        return dbAward;
    }
}
