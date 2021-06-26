package com.turing.website.controller.guest;


import com.turing.website.entity.History;
import com.turing.website.service.guest.GuestIntroductionService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "获取团队简介介绍接口")
@RestController
@RequestMapping("/guest/introduction")
@CrossOrigin
public class GuestIntroductionController {

    @Autowired
    GuestIntroductionService introductionService;

    @ApiOperation(value = "查询团队简介介绍")
    @GetMapping(value = "")
    public JsonResultUtil getIntroduction(){

        History history = introductionService.getIntroduction();
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("history", history);
        return jsonResultUtil;

    }

}
