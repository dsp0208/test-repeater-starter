package com.test.repeater.starter.filter.impl;

import com.test.repeater.starter.filter.ActiveFilter;

/**
 * @author dingshangping
 * 默认解析'active:true' 注解
 * 可以自定义实现，比如通过注解repeater.active.flagDesc=swimlane:xxxx,需要自行实现解析逻辑
 * @date 10/1/21 7:16 下午
 */
public class DefaultActiveFilter implements ActiveFilter {
    @Override
    public boolean isActive(String flagDesc) {

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
