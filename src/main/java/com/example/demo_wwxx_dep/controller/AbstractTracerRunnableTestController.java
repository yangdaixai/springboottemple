package com.example.demo_wwxx_dep.controller;

import com.example.demo_wwxx_dep.config.thread.AbstractTracerRunnable;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-15:37
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class AbstractTracerRunnableTestController {
    @GetMapping("/runableTest")
    public String runableTest(){
        log.info("====new thread-pool-AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
        MDC.put("TRACE_ID", UUID.randomUUID().toString());
        log.info("====new AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
        new Thread(new AbstractTracerRunnable() {
            @Override
            public void doTracerRun() {
                log.info("====new AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
            }
        }).start();

        return "TRACE_ID:" + MDC.get("TRACE_ID");
    }

    @GetMapping("/threadPoolTest")
    public String threadPoolTest(){
        log.info("====new thread-pool-AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
        MDC.put("TRACE_ID", UUID.randomUUID().toString());
        log.info("====new thread-pool-AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("test-thread-pool-%d").build());

        threadPoolExecutor.execute(new AbstractTracerRunnable(){
            @Override
            public void doTracerRun() {
                log.info("====new thread-pool-AbstractTracerRunnable threadid:{}====",Thread.currentThread().getId());
            }
        });

        return "TRACE_ID:" + MDC.get("TRACE_ID");
    }

}
