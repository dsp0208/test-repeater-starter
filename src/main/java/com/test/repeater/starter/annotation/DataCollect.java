package com.test.repeater.starter.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author dingshangping
 * 2021.10.01
 */
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataCollect {

}
