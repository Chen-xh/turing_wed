package com.turing.website.controller.admin;


import com.turing.website.scheduler.ResumeRunnable;
import com.turing.website.util.DateUtil;
import com.turing.website.util.JsonResultUtil;
import com.turing.website.util.ResumeTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ScheduledFuture;

/**
 * @author CHEN
 * @date 2020/3/4 16:55
 */

@Api(tags = "后台投递简历接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/admin/resume")
public class AdminResumeController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @PostMapping("/start")
    @ApiOperation("打开简历接口")
    public JsonResultUtil startResume(){
        try {
            ResumeRunnable.setResume(true);
        }catch (Exception e){
            JsonResultUtil.fail();
        }
        return JsonResultUtil.success();
    }
    @PostMapping("/stop")
    @ApiOperation("关闭简历接口")
    public JsonResultUtil stopResume(){
        try {
            if (future != null) {
                future.cancel(true);
            }
            ResumeRunnable.setResume(false);
        }catch (Exception e){
            JsonResultUtil.fail();
        }
        return JsonResultUtil.success();
    }

    @PostMapping("/startCron")
    @ApiImplicitParam(name = "starTtime", value = "持续时间")
    @ApiOperation("开始定时打开接口")
    public JsonResultUtil startCron( ResumeTimeUtil starTtime) {
        try {
            String corn1 = DateUtil.getCornByTime(starTtime.getTime());
            ResumeRunnable.setResume(true);
            future = threadPoolTaskScheduler.schedule(new ResumeRunnable(),
                    triggerContext -> new CronTrigger(corn1).nextExecutionTime(triggerContext));
        }catch (Exception e){
            return JsonResultUtil.fail();
        }
        return JsonResultUtil.success();

    }

}
