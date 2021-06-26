package com.turing.website.service.admin;


import com.turing.website.entity.Inform;

/**
 * @author CHEN
 * @date 2020/3/2 21:02
 */
public interface AdminInformService {

    void publishOrUpdateInform(Inform inform);

    void deleteInform(Long informId);

    Inform getInformById(Long informId);
}
