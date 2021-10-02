package com.test.repeater.starter.aspect;

import com.test.repeater.starter.context.impl.ThreadLocalLoadContext;
import com.test.repeater.starter.util.AnnotationUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

import static com.test.repeater.starter.context.impl.ThreadLocalLoadContext.COLLECT;
import static com.test.repeater.starter.context.impl.ThreadLocalLoadContext.REPEATER;

/**
 * @author dingshangping
 * @date 10/1/21 2:33 下午
 */
public class AnnotationAspect extends AbstractAspect implements MethodInterceptor {

    protected static class SingletonHolder {
        private static final AnnotationAspect INSTANCE = new AnnotationAspect();
    }

    public static MethodInterceptor get() {
        return AnnotationAspect.SingletonHolder.INSTANCE;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        if (!AnnotationUtils.hasDataCollectAnnotationFromMethod(method)
                && !AnnotationUtils.hasDataCollectAnnotationFromClass(invocation.getThis().getClass())) {
            return invocation.proceed();
        }
        //构建key
        String key = buildKey(invocation);
        Object mock;
        //数据回放
        if (ThreadLocalLoadContext.getSwitch(REPEATER)) {
            mock = super.getMock(key);
            if (mock != null) {
                return mock;
            }
        }
        //数据采集
        mock = invocation.proceed();
        if (ThreadLocalLoadContext.getSwitch(COLLECT)) {
            super.put(key, mock);
        }
        return mock;
    }


}
