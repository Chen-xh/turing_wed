package com.turing.website.service.guest.impl;


import com.turing.website.dao.InformDao;
import com.turing.website.entity.Inform;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestInformServiceImpl implements GuestInformService {

    @Autowired
    InformDao informDao;

    @Override
    public Inform findInformById(Long id) {
        Inform inform = informDao.findById(id).get();
        if(inform == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.INFORM_NOT_FOUND);
        }
        return inform;
    }

    @Override
    public List<Inform> findAllInforms() {
        List<Inform> allInforms = informDao.findAllInformsOrOrderByInformCreateTimeDESC();
        return allInforms;
    }

    @Override
    public List<Inform> findTop7Informs() {
        List<Inform> allInforms = informDao.findInformsOrderByInformCreateTimeDESC();
        List<Inform> top7Informs = new ArrayList<>();
        int index = 0;
        while(index < allInforms.size()){
            top7Informs.add(allInforms.get(index));
            if(++index == 7){
                break;
            }
        }
        return top7Informs;
    }
}
