package com.turing.website.exception;


import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.util.JsonResultUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author CHEN
 * @date 2020/3/6 15:17
 */
@ControllerAdvice
public class MyCustomizeExceptionHandler implements CustomizeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @Override
    public JsonResultUtil handleCustomizeException(HttpServletRequest request, Throwable ex) {
        //获取错误状态码
        HttpStatus status = getStatus(request);
        if (ex instanceof CustomizeRuntimeException) {
            CustomizeRuntimeException customizeException = (CustomizeRuntimeException) ex;
            JsonResultUtil jsonResultUtil = JsonResultUtil.errorOf(customizeException.getCode(), customizeException.getMessage());
            System.out.println(jsonResultUtil.toString());
            return jsonResultUtil;
        }
        if (status.is5xxServerError() || ex instanceof ParseException) {
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    @Override
    public JsonResultUtil handleMultipartException() {
        return JsonResultUtil.errorOf(MyCustomizeErrorCode.FILE_MAX_SIZE_EXCEPTION);
    }


    private HttpStatus getStatus(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);

    }
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    @ResponseBody
    public JsonResultUtil authorizationException(Throwable ex) {
        if (ex instanceof UnauthorizedException  ) {
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.NOT_ALLOWED);
        }

        return  JsonResultUtil.errorOf(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    @ResponseBody
    public JsonResultUtil authenticationException(Throwable ex) {
        if (ex instanceof IncorrectCredentialsException) {
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.PASS_NOT_CORRECT);
        }
        if (ex instanceof UnknownAccountException) {
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        if (ex instanceof CredentialsException) {
            return JsonResultUtil.errorOf(MyCustomizeErrorCode.NOT_LOGIN);
        }
        if (ex instanceof CustomizeAuthenticationException) {
            CustomizeAuthenticationException customizeException = (CustomizeAuthenticationException) ex;
            return JsonResultUtil.errorOf(customizeException.getCode(),customizeException.getMessage());
        }
        return JsonResultUtil.errorOf(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
    }
}
