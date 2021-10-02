package com.test.repeater.starter.annotation;

import com.test.repeater.starter.aspect.TestRepeaterAspect;
import com.test.repeater.starter.util.AnnotationUtils;
import org.springframework.aop.TargetSource;
import org.springframework.beans.BeansException;

/**
 * @author dingshangping
 * @date 10/1/21 8:10 下午
 */
public class TestRepeaterScanner extends AbstractScanner {

    public boolean skipFlag;

    public TestRepeaterScanner(boolean active) {
        //激活则不能跳过拦截,未激活则跳过
        this.skipFlag = !active;
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        return new Object[]{new TestRepeaterAspect()};
    }

    @Override
    protected boolean shouldSkip(Class<?> beanClass, String beanName) {
        return skipFlag ? true : super.shouldSkip(beanClass, beanName);
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        if (!AnnotationUtils.hasRepeaterAnnotation(new Class<?>[]{bean.getClass()})) {
            return bean;
        }
        return super.wrapIfNecessary(bean, beanName, cacheKey);
    }


}
