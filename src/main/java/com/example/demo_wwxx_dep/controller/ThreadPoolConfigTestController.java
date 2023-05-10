package com.example.demo_wwxx_dep.controller;

import com.example.demo_wwxx_dep.service.ThreadPoolConfigTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-18:43
 */
@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThreadPoolConfigTestController {

    private final ThreadPoolConfigTest threadPoolConfigTest;

    @GetMapping("/threadPoolConfigTest")
    public String threadPoolTest() {

        threadPoolConfigTest.threadPoolConfigTest();

        return "TRACE_ID:" + MDC.get("TRACE_ID");
    }


}
