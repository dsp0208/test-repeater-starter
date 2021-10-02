package com.test.repeater.starter.filter.impl;

import com.test.repeater.starter.filter.ResultFilter;

/**
 * @author dingshangping
 * 默认结果解析器，结果均通过。
 * @date 10/1/21 7:31 下午
 */
public class DefaultResultFilter implements ResultFilter {
    @Override
    public boolean isPass(Object object) {
        return true;
    }
}
