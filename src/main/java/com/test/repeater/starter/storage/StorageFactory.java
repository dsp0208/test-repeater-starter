package com.test.repeater.starter.storage;

import com.google.common.collect.Lists;
import com.test.repeater.starter.context.impl.ThreadLocalLoadContext;
import com.test.repeater.starter.filter.FilterFactory;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dingshangping
 * @date 10/1/21 1:25 下午
 */
public class StorageFactory {

    private static List<Storage> storageList = Lists.newArrayList();

    static {
        ServiceLoader<Storage> storageServiceLoader = ServiceLoader.load(Storage.class);
        for (Storage storage : storageServiceLoader) {
            storageList.add(storage);
        }
    }

    public static List<Storage> getStorage() {
        return storageList;
    }

    /**
     * 存储到磁盘
     *
     * @return
     */
    public static int flush() {
        //落盘之前校验是否需要保存该数据
        if (!FilterFactory.get().resultCheck(ThreadLocalLoadContext.get(ThreadLocalLoadContext.RESULT))) {
            return 0;
        }
        List<Storage> storageList = StorageFactory.getStorage();
        AtomicInteger sum = new AtomicInteger(0);
        for (Storage storage : storageList) {
            Map<String, Object> entries = ThreadLocalLoadContext.entries();
            entries.entrySet().forEach(entry -> {
                storage.setValue(entry.getKey(), entry.getValue());
                sum.getAndAdd(1);
            });
        }
        return sum.get();
    }
}
