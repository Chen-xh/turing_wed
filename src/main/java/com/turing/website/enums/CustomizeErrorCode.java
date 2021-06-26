package com.turing.website.enums;

/**
 * @author Jack
 * @date 2019-07-27-18:08
 */
public interface CustomizeErrorCode {
    /**
     * 获取错误状态码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();

}
