package com.test.repeater.starter.generator;

import com.test.repeater.starter.generator.impl.DefaultIdGenerator;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author dingshangping
 * @date 10/1/21 10:25 上午
 */
public class IdGeneratorFactory {

    protected static List<IdGenerator> idGenerators = new ArrayList<>();

    protected IdGeneratorFactory() {
        ServiceLoader<IdGenerator> idGeneratorServiceLoader = ServiceLoader.load(IdGenerator.class);
        for (IdGenerator idGenerator : idGeneratorServiceLoader) {
            idGenerators.add(idGenerator);
        }
    }

    /**
     * 单例模式获取工厂
     */
    protected static class SingletonHolder {
        private static final IdGeneratorFactory INSTANCE = new IdGeneratorFactory();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static IdGeneratorFactory get() {
        return IdGeneratorFactory.SingletonHolder.INSTANCE;
    }

    /**
     * 获取类解析器
     *
     * @return
     */
    public String getId(Object[] params) {
        String defaultId = null;
        for (IdGenerator idGenerator : idGenerators) {
            //优先用户自定义，无则采用默认生成器
            if (idGenerator instanceof DefaultIdGenerator) {
                defaultId = idGenerator.getId(params);
                continue;
            }
            String id = idGenerator.getId(params);
            if (!StringUtils.isEmpty(id)) {
                return id;
            }
        }
        //雪花算法兜底
        return defaultId;
    }

}
