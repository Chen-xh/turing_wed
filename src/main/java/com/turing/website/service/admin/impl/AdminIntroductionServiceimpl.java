package com.turing.website.service.admin.impl;


import com.turing.website.dao.HistoryDao;
import com.turing.website.entity.History;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminIntroductionService;
import com.turing.website.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminIntroductionServiceimpl implements AdminIntroductionService {

    @Autowired
    HistoryDao historyDao;

    @Override
    public void updateIntroduction(Long introductionId, String introductionInfo) {

        History dbHistory;
        try {
            dbHistory = historyDao.findById(introductionId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.HISTORY_NOT_FOUND);
        }

        Date currentTime = DateUtil.getCurrentDateTime();
        dbHistory.setIntroductionEditTime(currentTime);
        dbHistory.setIntroductionInfo(introductionInfo);

        historyDao.save(dbHistory);

    }

}
