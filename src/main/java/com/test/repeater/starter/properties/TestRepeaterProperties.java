package com.test.repeater.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dingshangping
 * @date 10/1/21 6:34 下午
 */
@Component
@ConfigurationProperties(prefix = "repeater.active")
@Data
public class TestRepeaterProperties {
    private String flagDesc = "active";
    private String collect = "true";
    private String repeater = "true";
}
