package com.test.repeater.starter.filter;

import com.test.repeater.starter.properties.TestRepeaterProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author dingshangping
 * @date 10/1/21 7:18 下午
 */
public class FilterFactory {

    protected static List<ActiveFilter> activeFilterList = new ArrayList<>();
    protected static List<ResultFilter> resultFilterList = new ArrayList<>();

    protected FilterFactory() {
        ServiceLoader<ActiveFilter> activeFilterServiceLoader = ServiceLoader.load(ActiveFilter.class);
        ServiceLoader<ResultFilter> resultFilterServiceLoader = ServiceLoader.load(ResultFilter.class);
        for (ActiveFilter filter : activeFilterServiceLoader) {
            activeFilterList.add(filter);
        }
        for (ResultFilter resultFilter : resultFilterServiceLoader) {
            resultFilterList.add(resultFilter);
        }
    }

    /**
     * 单例模式获取工厂
     */
    protected static class SingletonHolder {
        private static final FilterFactory INSTANCE = new FilterFactory();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static FilterFactory get() {
        return FilterFactory.SingletonHolder.INSTANCE;
    }

    public boolean active(TestRepeaterProperties properties) {
        for (ActiveFilter activeFilter : activeFilterList) {
            if (!activeFilter.isActive(properties.getFlagDesc())) {
                return false;
            }
        }
        return true;
    }

    public boolean resultCheck(Object result) {
        for (ResultFilter resultFilter : resultFilterList) {
            if (resultFilter.isPass(result)) {
                return true;
            }
        }
        return false;
    }


}
