package com.turing.website.exception;


import com.turing.website.enums.CustomizeErrorCode;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @author Jack
 * @date 2019-07-27-18:36
 */
public class CustomizeRuntimeException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomizeRuntimeException(CustomizeErrorCode customizeErrorCode) {

        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();

    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
