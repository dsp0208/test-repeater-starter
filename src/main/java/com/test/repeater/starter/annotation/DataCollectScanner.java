package com.test.repeater.starter.annotation;

import com.test.repeater.starter.parser.ParserFactory;
import com.test.repeater.starter.parser.ServiceParser;
import com.test.repeater.starter.util.AnnotationUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.TargetSource;
import org.springframework.beans.BeansException;

/**
 * @author dingshangping
 * @date 10/1/21 9:53 上午
 */
@Slf4j
public class DataCollectScanner extends AbstractScanner {

    public MethodInterceptor methodInterceptor;

    public boolean skipFlag;

    public DataCollectScanner(boolean active) {
        //激活则不能跳过拦截,未激活则跳过
        this.skipFlag = !active;
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        return new Object[]{methodInterceptor};
    }

    @Override
    protected boolean shouldSkip(Class<?> beanClass, String beanName) {
        return skipFlag ? true : super.shouldSkip(beanClass, beanName);
    }

    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        if (!AnnotationUtils.existsDataCollectionAnnotation(new Class<?>[]{bean.getClass()})) {
            return bean;
        }
        //获取解析器
        ServiceParser parser = ParserFactory.get().parser(bean, beanName);
        if (parser == null) {
            return bean;
        }
        methodInterceptor = parser.getMethodIntercept();
        return super.wrapIfNecessary(bean, beanName, cacheKey);
    }
}
