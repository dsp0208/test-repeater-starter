package com.test.repeater.starter.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author dingshangping
 * @date 10/1/21 10:25 上午
 */
public class ParserFactory {

    protected static List<ServiceParser> serviceParsers = new ArrayList<>();

    protected ParserFactory() {
        ServiceLoader<ServiceParser> serviceParserLoader = ServiceLoader.load(ServiceParser.class);
        for (ServiceParser serviceParser : serviceParserLoader) {
            serviceParsers.add(serviceParser);
        }
    }

    /**
     * 单例模式获取工厂
     */
    protected static class SingletonHolder {
        private static final ParserFactory INSTANCE = new ParserFactory();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static ParserFactory get() {
        return ParserFactory.SingletonHolder.INSTANCE;
    }

    /**
     * 获取类解析器
     *
     * @param serviceName
     * @return
     */
    public ServiceParser parser(Object service, String serviceName) {
        for (ServiceParser parser : serviceParsers) {
            if (parser.isParser(service, serviceName)) {
                return parser;
            }
        }
        return null;
    }

}
