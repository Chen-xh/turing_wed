package com.turing.website.service.guest;


import com.turing.website.entity.Inform;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestInformService {

    List<Inform> findAllInforms();

    List<Inform> findTop7Informs();

    Inform findInformById(Long id);
}
