package com.turing.website.filter;

import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.scheduler.ResumeRunnable;
import com.turing.website.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHEN
 * @date 2020/3/7 17:52
 */
public class ResumeInterceptor implements HandlerInterceptor {
    private static final Logger PLOG = LoggerFactory.getLogger(ResumeInterceptor.class);

    @Override //进入Controller之前执行该方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(!ResumeRunnable.isResume()){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.RESUME_STOP);
        }
        return true;

    }
}
