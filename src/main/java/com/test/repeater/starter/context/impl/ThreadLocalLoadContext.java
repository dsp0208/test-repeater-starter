package com.test.repeater.starter.context.impl;

import com.google.common.collect.Maps;
import com.test.repeater.starter.generator.IdGeneratorFactory;
import com.test.repeater.starter.storage.StorageFactory;
import com.test.repeater.starter.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author dingshangping
 * @date 10/1/21 3:19 下午
 */
@Slf4j
public class ThreadLocalLoadContext {

    public static final String ID = "ID";
    public static final String RESULT = "RESULT";
    public static final String KEY_INDEX = "_INDEX";
    public static final String COLLECT = "COLLECT";
    public static final String REPEATER = "REPEATER";
    private static Map<String, Boolean> allSwitch = Maps.newHashMap();
    private static ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    public static String start(Object[] params) {
        String id = (String) Optional.ofNullable(threadLocal.get().get(ID)).orElse("");
        if (!StringUtils.isEmpty(id)) {
            return id;
        }
        String defaultId = IdGeneratorFactory.get().getId(params);
        threadLocal.get().put(ID, defaultId);
        return defaultId;
    }

    public static void end(Object o) {
        threadLocal.get().put(RESULT, o);
        StorageFactory.flush();
        log.info("all cache:{}", JsonTool.writeToString(entries()));
        threadLocal.remove();
    }

    public static Object put(String key, Object value) {
        return threadLocal.get().put(key, value);
    }

    public static Object get(String key) {
        return threadLocal.get().get(key);
    }

    public static Map<String, Object> entries() {
        return threadLocal.get();
    }

    public static Object putSwitch(String key, Boolean value) {
        return allSwitch.put(key, value);
    }

    public static boolean getSwitch(String key) {
        return Optional.ofNullable(allSwitch.get(key)).orElse(false);
    }


}
