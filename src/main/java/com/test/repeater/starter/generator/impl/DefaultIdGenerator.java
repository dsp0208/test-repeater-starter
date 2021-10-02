package com.test.repeater.starter.generator.impl;

import com.test.repeater.starter.generator.IdGenerator;
import com.test.repeater.starter.util.SnowFlakeIdGenerator;

/**
 * @author dingshangping
 * 默认的id生成器优先解析入参，入参为单个String则作为key生成，否则采用雪花算法生成
 * @date 10/2/21 7:56 上午
 */
public class DefaultIdGenerator implements IdGenerator {

    @Override
    public String getId(Object[] params) {
        if (params == null || params.length != 1) {
            return SnowFlakeIdGenerator.getSnowFlakeId();
        }
        Object param = params[0];
        if (param instanceof String) {
            return (String) param;
        }
        return SnowFlakeIdGenerator.getSnowFlakeId();
    }
}
