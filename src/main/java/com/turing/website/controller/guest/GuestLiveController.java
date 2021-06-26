package com.turing.website.controller.guest;


import com.turing.website.entity.Live;
import com.turing.website.service.guest.GuestLiveService;
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
@Api(tags = "获取团队活动接口")
@RestController
@RequestMapping("/guest/live")
@CrossOrigin
public class GuestLiveController {

    @Autowired
    GuestLiveService liveService;

    @ApiOperation(value = "查询所有团队活动")
    @GetMapping(value = "")
    public JsonResultUtil findAllLives(){

        List<Live> lives = liveService.findAllLives();
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("lives",lives);
        return jsonResultUtil;
    }

    @ApiOperation(value = "根据liveId查询团队活动详细信息")
    @GetMapping(value = "/{liveId}")
    public JsonResultUtil findLiveByLiveId(@PathVariable Long liveId){

        Live live = liveService.findLiveByLiveId(liveId);
        JsonResultUtil jsonResultUtil = JsonResultUtil.success().addObject("live", live);
        return jsonResultUtil;

    }

}
