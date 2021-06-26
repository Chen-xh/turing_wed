package com.turing.website.service.guest.impl;

import com.turing.website.dao.HistoryDao;
import com.turing.website.entity.History;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.guest.GuestIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Service
public class GuestIntroductionServiceImpl implements GuestIntroductionService {

    @Autowired
    HistoryDao historyDao;

    @Override
    public History getIntroduction() {
        List<History> histories = historyDao.findAll();
        if(histories == null | histories.size() == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.HISTORY_NOT_FOUND);
        }
        return histories.get(0);
    }
}
