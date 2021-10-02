package com.test.repeater.starter.aspect;

import com.test.repeater.starter.context.impl.ThreadLocalLoadContext;
import com.test.repeater.starter.storage.Storage;
import com.test.repeater.starter.storage.StorageFactory;
import com.test.repeater.starter.util.JsonTool;
import com.test.repeater.starter.util.Md5Util;
import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

/**
 * @author dingshangping
 * @date 10/1/21 1:45 下午
 */
public abstract class AbstractAspect {

    public void put(String key, Object object) {
        ThreadLocalLoadContext.put(key, object);
    }

    protected Object getMock(String key) {
        Object result;
        List<Storage> storageList = StorageFactory.getStorage();
        for (Storage storage : storageList) {
            result = storage.getValue(key);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    protected String buildKey(MethodInvocation invocation) {
        String key = String.format("%s_%s_%s_%s"
                , ThreadLocalLoadContext.get(ThreadLocalLoadContext.ID)
                , invocation.getClass().getSimpleName()
                , invocation.getMethod().getName()
                , Md5Util.getMD5(JsonTool.writeToString(invocation.getArguments()))
        );
        return getIndex(key);
    }

    protected String getIndex(String key){
        Object indexObj = ThreadLocalLoadContext.get(key + ThreadLocalLoadContext.KEY_INDEX);
        if (indexObj == null) {
            key = key + "_1";
        } else {
            key = key + "_" + (Integer.valueOf(String.valueOf(indexObj)) + 1);
        }
        return key;
    }
}
