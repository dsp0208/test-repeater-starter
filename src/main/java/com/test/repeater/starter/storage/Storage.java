package com.test.repeater.starter.storage;

/**
 * @author dingshangping
 * @date 10/1/21 9:34 上午
 */
public interface Storage {
    /**
     * 设置值
     * @return
     */
    int setValue(String key, Object object);

    /**
     * 获取记录值
     * @param key
     * @return
     */
    Object getValue(String key);
}
