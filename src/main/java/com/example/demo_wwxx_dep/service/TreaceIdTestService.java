package com.example.demo_wwxx_dep.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-15:53
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TreaceIdTestService {

    public String treaceIdTestService() {

        log.info("====treaceIdTestService threadid:{}====",Thread.currentThread().getId());
        return "TRACE_ID:" + MDC.get("TRACE_ID");
    }

}
