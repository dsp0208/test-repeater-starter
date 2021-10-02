package com.test.repeater.starter.context;

import java.util.Map;

/**
 * @author dingshangping
 * @date 10/1/21 3:28 下午
 */
public interface LoadContext {
    Object put(String key, Object value);

    Object get(String key);

    Object remove(String key);

    Map<String, Object> entries();
}
