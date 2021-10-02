package com.test.repeater.starter.parser;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author dingshangping
 * 2021.10.01
 */
public interface ServiceParser {

    /**
     * 是否解析成功
     * @param object
     * @param serviceName
     * @return
     */
    boolean isParser(Object object,String serviceName);

    /**
     * 拦截切面装配
     * @return
     */
    MethodInterceptor getMethodIntercept();;

}
