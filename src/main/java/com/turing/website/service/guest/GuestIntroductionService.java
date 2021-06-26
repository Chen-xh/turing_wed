package com.turing.website.service.guest;


import com.turing.website.entity.History;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
public interface GuestIntroductionService {
    /**
     * 获取团队历史内容
     * @return History实体类
     */
    History getIntroduction();

}
