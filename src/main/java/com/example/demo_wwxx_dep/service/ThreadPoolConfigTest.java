package com.example.demo_wwxx_dep.service;

import com.example.demo_wwxx_dep.config.thread.AbstractTracerRunnable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-18:37
 */
@Component
@Slf4j
public class ThreadPoolConfigTest {

    @Resource(name = "testThreadPool")
    private ExecutorService executorService;


    public String threadPoolConfigTest(){
        log.info("====threadPoolTest-父线程 threadid:{}====",Thread.currentThread().getId());

        executorService.execute(new AbstractTracerRunnable(){
            @Override
            public void doTracerRun() {
                log.info("====threadPoolTest-子线程 threadid:{}====",Thread.currentThread().getId());
            }
        });

        return "TRACE_ID:" + MDC.get("TRACE_ID");
    }

}
