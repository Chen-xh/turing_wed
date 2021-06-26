package com.turing.website.exception;

import com.turing.website.util.JsonResultUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jack
 * @date 2019-07-27-18:12
 */
public interface CustomizeExceptionHandler {

    JsonResultUtil handleCustomizeException(HttpServletRequest request, Throwable ex);

    JsonResultUtil handleMultipartException();

}
