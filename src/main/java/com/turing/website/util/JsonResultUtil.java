package com.turing.website.util;

import com.turing.website.enums.CustomizeErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jack
 * @date 2019-06-27-17:00
 */
@Data
public class JsonResultUtil implements Serializable {

    private Integer code;
    private String msg;
    private Map<String, Object> extended = new HashMap<>();

    public static JsonResultUtil success(){
        return new JsonResultUtil(200, "处理成功");
    }

    public static JsonResultUtil fail(){return new JsonResultUtil(100, "处理失败!");}

    public static JsonResultUtil errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static JsonResultUtil errorOf(Integer code, String message) {
        return new JsonResultUtil(code, message);
    }

    public JsonResultUtil addObject(String key, Object value){
        this.extended.put(key, value);
        return this;
    }

    public JsonResultUtil(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
