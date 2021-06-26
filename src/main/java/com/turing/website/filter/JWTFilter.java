package com.turing.website.filter;


import com.turing.website.util.JWTToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author CHEN
 * @date 2020/3/1 16:47
    BasicHttpAuthenticationFilter继承 AuthenticatingFilter 过滤器
 * 其能够自动地进行基于所述传入请求的认证尝试。
 *    BasicHttpAuthenticationFilter 基本访问认证过滤器
 *   此实现是每个基本HTTP身份验证规范的Java实现
 *   通过此过滤器得到HTTP请求资源获取Authorization传递过来的token参数
 *   获取subject对象进行身份验证
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private static final Logger PLOG = LoggerFactory.getLogger(JWTFilter.class);
    /**
     * 判断是否带TOKEN请求
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return !StringUtils.isEmpty(authorization);
    }
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("token");

        JWTToken token = new JWTToken(authorization);
        getSubject(request, response).login(token);

        return true;
    }
    /**
     * 这里控制通过与否
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // OPTIONS 预请求 忽略
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        // 如果不带TOKEN请求，直接阻止
        if (!isLoginAttempt(request, response)) {
            return false;
        }
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            PLOG.error("JWT >> " + e);
            responseError(request, response);
            return false;
        }

        return true;
    }
    /**
     * 将非法请求跳转到 /ign/error
     */
    private void responseError(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/user/ign/error");
        } catch (IOException e) {
            PLOG.error("JWT >> " + e);
        }
    }
}

