package com.turing.website.controller.guest;


import com.turing.website.entity.Inform;
import com.turing.website.service.guest.GuestInformService;
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
@Api(tags = "获取通告内容接口")
@RestController
@RequestMapping(value = "/guest/inform")
@CrossOrigin
public class GuestInformController {

    @Autowired
    GuestInformService informService;

    @ApiOperation(value = "查询所有通告")
    @GetMapping(value = "")
    public JsonResultUtil findAllInforms(){
        List<Inform> allInforms = informService.findAllInforms();
        return JsonResultUtil.success().addObject("informs", allInforms);
    }
    @ApiOperation(value = "查询前7条最新通告")
    @GetMapping(value = "/top7Informs")
    public JsonResultUtil findTop7Informs(){
        List<Inform> top7Informs = informService.findTop7Informs();
        return JsonResultUtil.success().addObject("informs", top7Informs);
    }
    @ApiOperation(value = "根据id查询某一条通告信息")
    @GetMapping(value = "/{id}")
    public JsonResultUtil findInformById(@PathVariable Long id){
        Inform inform = informService.findInformById(id);
        return JsonResultUtil.success().addObject("inform", inform);
    }
}



