package com.test.repeater.starter.util;

import com.test.repeater.starter.annotation.DataCollect;
import com.test.repeater.starter.annotation.TestRepeater;

import java.lang.reflect.Method;

/**
 * @author dingshangping
 * @date 10/1/21 2:36 下午
 */
public class AnnotationUtils {
    public static boolean existsDataCollectionAnnotation(Class<?>[] classes) {
        if (classes == null || classes.length <= 0) {
            return false;
        }
        for (Class<?> clazz : classes) {
            if (clazz == null) {
                continue;
            }
            if (hasDataCollectAnnotationFromClass(clazz)) {
                return true;
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (hasDataCollectAnnotationFromMethod(method)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasRepeaterAnnotation(Class<?>[] classes){
        if (classes == null || classes.length <= 0) {
            return false;
        }
        for(Class<?> clazz:classes){
            if(clazz == null){
                continue;
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (hasRepeaterAnnotationFromMethod(method)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean hasDataCollectAnnotationFromClass(Class<?> clazz) {
        DataCollect dataCollectAnnotation = clazz.getAnnotation(DataCollect.class);
        if (dataCollectAnnotation != null) {
            return true;
        }
        return false;
    }

    public static boolean hasDataCollectAnnotationFromMethod(Method method) {
        DataCollect dataCollectAnnotation = method.getAnnotation(DataCollect.class);
        if (dataCollectAnnotation != null) {
            return true;
        }
        return false;
    }

    public static boolean hasRepeaterAnnotationFromMethod(Method method) {
        TestRepeater testRepeaterAnnotation = method.getAnnotation(TestRepeater.class);
        if (testRepeaterAnnotation != null) {
            return true;
        }
        return false;
    }
}
