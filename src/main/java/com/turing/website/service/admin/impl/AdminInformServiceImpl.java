package com.turing.website.service.admin.impl;


import com.turing.website.dao.InformDao;
import com.turing.website.entity.Inform;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminInformService;
import com.turing.website.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminInformServiceImpl implements AdminInformService {

    @Autowired
    InformDao informDao;

    @Override
    public void publishOrUpdateInform(Inform inform) {

        if("".equals(inform.getInformUsername().trim())&&"".equals(inform.getInformContent().trim())){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.INFORM_IS_NOT_CORRECT);
        }

        Date currentTime = DateUtil.getCurrentDateTime();
        if(inform.getInformId() == null){
            //创建
            inform.setInformCreateTime(currentTime);
            informDao.save(inform);
        }else{
            //更新
            Inform dbInform;
            try {
                dbInform = informDao.findById(inform.getInformId()).get();
            }catch (Exception ex){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.INFORM_NOT_FOUND);
            }
            dbInform.setInformContent(inform.getInformContent());
            dbInform.setInformCreateTime(currentTime);
            dbInform.setInformUsername(inform.getInformUsername());
            informDao.save(dbInform);
        }

    }

    @Override
    public void deleteInform(Long informId) {

        try {
            informDao.findById(informId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.INFORM_NOT_FOUND);
        }
        informDao.deleteById(informId);

    }

    @Override
    public Inform getInformById(Long informId) {

        Inform dbInform;
        try {
            dbInform = informDao.findById(informId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.INFORM_NOT_FOUND);

        }
        return dbInform;

    }
}
