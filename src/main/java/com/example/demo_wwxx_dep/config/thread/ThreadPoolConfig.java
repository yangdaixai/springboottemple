package com.example.demo_wwxx_dep.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-18:31
 */
@Configuration
public class ThreadPoolConfig {
    private int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;


    @Value("${thread-pool.max_pool_size}")
    private int MAX_POOL_SIZE;

    @Bean(value = "testThreadPool")
    public ExecutorService testThreadPool() {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-thread-%d").build();

        /**
         * 1. CallerRunsPolicy ：    这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
         2. AbortPolicy ：         对拒绝任务抛弃处理，并且抛出异常。
         3. DiscardPolicy ：       对拒绝任务直接无声抛弃，没有异常信息。
         4. DiscardOldestPolicy ： 对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(200),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return threadPool;
    }

}
