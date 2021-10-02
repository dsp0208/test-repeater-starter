package com.test.repeater.starter.aspect;

import com.test.repeater.starter.context.impl.ThreadLocalLoadContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author dingshangping
 * @date 10/1/21 8:11 下午
 */
public class TestRepeaterAspect implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ThreadLocalLoadContext.start(invocation.getArguments());
        Object result = null;
        try{
            result = invocation.proceed();
        } catch (Throwable throwable){
            throw throwable;
        } finally {
            ThreadLocalLoadContext.end(result);
        }
        return result;
    }
}
