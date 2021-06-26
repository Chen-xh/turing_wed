package com.turing.website.controller.guest;

import com.turing.website.entity.Award;
import com.turing.website.service.guest.GuestAwardService;
import com.turing.website.util.JsonResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */
@Api(tags = "获取奖项信息接口")
@RestController
@RequestMapping("/guest/award")
@CrossOrigin
public class GuestAwardController {

    @Autowired
    GuestAwardService awardService;

    @ApiOperation(value = "查询所有奖项信息", notes = "此接口不会查询出获奖的同学")
    @GetMapping(value = "")
    public JsonResultUtil findAllAwards(){

        List<Award> awards = awardService.findAllAwards();
        JsonResultUtil jsonResultUtil = JsonResultUtil.success();
        jsonResultUtil.addObject("awards", awards);
        return jsonResultUtil;

    }

    @ApiOperation(value = "根据awardId查询奖项详细信息")
    @GetMapping(value = "/{awardId}")
    public JsonResultUtil findAwardById(@PathVariable Long awardId){

        Award award = awardService.findAwardByAwardId(awardId);
        JsonResultUtil jsonResultUtil = JsonResultUtil.success();
        jsonResultUtil.addObject("award", award);
        return jsonResultUtil;

    }
}

