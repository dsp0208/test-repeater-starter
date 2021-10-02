package com.test.repeater.starter.autoconfiguration;

import com.test.repeater.starter.annotation.DataCollectScanner;
import com.test.repeater.starter.annotation.TestRepeaterScanner;
import com.test.repeater.starter.context.impl.ThreadLocalLoadContext;
import com.test.repeater.starter.filter.FilterFactory;
import com.test.repeater.starter.properties.TestRepeaterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

import static com.test.repeater.starter.context.impl.ThreadLocalLoadContext.COLLECT;
import static com.test.repeater.starter.context.impl.ThreadLocalLoadContext.REPEATER;

/**
 * @author dingshangping
 * @date 10/1/21 4:14 下午
 */
@Configuration
@ComponentScan(basePackages = "com.test.repeater.starter.properties")
public class TestRepeaterAutoConfiguration {

    @Bean
    public DataCollectScanner dataCollectScanner(TestRepeaterProperties properties) {
        boolean active = FilterFactory.get().active(properties);
        if (active) {
            ThreadLocalLoadContext.putSwitch(COLLECT, Boolean.valueOf(Optional.ofNullable(properties.getCollect()).orElse("false")));
            ThreadLocalLoadContext.putSwitch(REPEATER, Boolean.valueOf(Optional.ofNullable(properties.getRepeater()).orElse("false")));
        }
        return new DataCollectScanner(active);
    }

    @Bean
    public TestRepeaterScanner testRepeaterScanner(TestRepeaterProperties properties) {
        return new TestRepeaterScanner(FilterFactory.get().active(properties));
    }

}
