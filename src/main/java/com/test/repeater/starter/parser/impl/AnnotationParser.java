package com.test.repeater.starter.parser.impl;

import com.test.repeater.starter.util.AnnotationUtils;
import com.test.repeater.starter.aspect.AnnotationAspect;
import com.test.repeater.starter.parser.ServiceParser;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author dingshangping
 * @date 10/1/21 2:34 下午
 */
public class AnnotationParser implements ServiceParser {

    @Override
    public boolean isParser(Object object, String serviceName) {
        return AnnotationUtils.existsDataCollectionAnnotation(new Class<?>[]{object.getClass()});
    }

    @Override
    public MethodInterceptor getMethodIntercept() {
        return AnnotationAspect.get();
    }
}
