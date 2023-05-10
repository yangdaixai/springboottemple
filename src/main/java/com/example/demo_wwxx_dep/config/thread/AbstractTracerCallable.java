package com.example.demo_wwxx_dep.config.thread;
import org.slf4j.MDC;
import java.util.Map;
import java.util.concurrent.Callable;
/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-15:30
 */


/**
 * @author yangshengwei_zidie
 * @description
 * @date 2022-06-24-4:51 PM
 */
public abstract class AbstractTracerCallable<V> implements Callable<V> {

    private Map<String, String> context = MDC.getCopyOfContextMap();

    public abstract V doTracer();

    @Override
    public V call() throws Exception {
        if (this.context != null) {
            MDC.setContextMap(this.context);
        }
        try{
            return doTracer();
        } finally {
            MDC.clear();
        }
    }
}

