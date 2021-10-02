package com.test.repeater.starter.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author dingshangping
 * @date 10/1/21 9:26 下午
 */
@Slf4j
public abstract class AbstractScanner extends AbstractAutoProxyCreator {

    protected AdvisedSupport getAdvisedSupport(Object proxy) throws Exception {
        Field h;
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            h = proxy.getClass().getSuperclass().getDeclaredField("h");
        } else {
            h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        }
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return (AdvisedSupport) advised.get(dynamicAdvisedInterceptor);
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        try {
            if (!AopUtils.isAopProxy(bean)) {
                super.setProxyTargetClass(true);
                bean = super.wrapIfNecessary(bean, beanName, cacheKey);
            } else {
                AdvisedSupport advised = this.getAdvisedSupport(bean);
                Advisor[] advisor = buildAdvisors(beanName, getAdvicesAndAdvisorsForBean(null, null, null));
                int i = 0;
                for (Advisor avr : advisor) {
                    advised.addAdvisor(i++, avr);
                }
            }
        } catch (Exception e) {
            log.error("wrapIfNecessary error", e);
        }
        return bean;
    }
}
