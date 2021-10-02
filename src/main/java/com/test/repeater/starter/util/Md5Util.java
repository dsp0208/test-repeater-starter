package com.test.repeater.starter.util;

import org.springframework.util.DigestUtils;

/**
 * @author dingshangping
 * @date 10/1/21 2:59 下午
 */
public class Md5Util {

    private static final String slat = "23sfs**";

    public static String getMD5(String param) {
        String base = param + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
