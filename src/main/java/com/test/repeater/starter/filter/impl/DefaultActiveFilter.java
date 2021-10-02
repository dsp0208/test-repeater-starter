package com.test.repeater.starter.filter.impl;

import com.test.repeater.starter.filter.ActiveFilter;

/**
 * @author dingshangping
 * @date 10/1/21 7:16 下午
 */
public class DefaultActiveFilter implements ActiveFilter {
    @Override
    public boolean isActive(String flagDesc) {
        //默认解析'active:true'
        String[] flagDescSplit = flagDesc.split(":");
        if (!flagDescSplit[0].equals("active")) {
            return true;
        }
        if (flagDescSplit[1].equals("true")) {
            return true;
        }
        return false;
    }
}
