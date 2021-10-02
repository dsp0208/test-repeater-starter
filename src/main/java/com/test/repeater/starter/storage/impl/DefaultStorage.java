package com.test.repeater.starter.storage.impl;

import com.google.common.collect.Maps;
import com.test.repeater.starter.storage.Storage;
import com.test.repeater.starter.util.JsonTool;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author dingshangping
 * 默认实现为单击缓存，数据缓存在内存中。分布式环境下需要自定义实现缓存，并通过spi注入
 * @date 10/1/21 1:23 下午
 */
@Slf4j
public class DefaultStorage implements Storage {

    Map<String, Object> cache = Maps.newHashMap();

    @Override
    public int setValue(String key, Object object) {
        cache.put(key, object);
        log.info("DefaultStorage setCache", JsonTool.writeToString(cache));
        return 1;
    }

    @Override
    public Object getValue(String key) {
        Object o = cache.get(key);
        return o;
    }
}
