package com.turing.website.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author CHEN
 * @date 2020/3/2 14:57
 */
public class MD5Util {
    public static String getHexPassword(String password){
        return new Md5Hash(password, "holle", 2).toString();
    }
}
