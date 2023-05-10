package com.example.demo_wwxx_dep.controller;

import com.example.demo_wwxx_dep.service.TreaceIdTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-13:54
 */
@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerTest {


    private final TreaceIdTestService treaceIdTestService;

    @GetMapping("/gettest")
    public String getTest() {
        System.out.println("====getTest()==begin=");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println("Formatted date: " + formattedDate);
        System.out.println("====getTest()==end=");
        return "success! " +  formattedDate;
    }

    @GetMapping("/logtest")
    public String log4jtest() {

        MDC.put("TRACE_ID", UUID.randomUUID().toString());
        Map<String, String> context = MDC.getCopyOfContextMap();
        MDC.setContextMap(context);
        log.info("====new runable1 threadid:{}===",Thread.currentThread().getId());
        log.trace("trace level");
        log.debug("debug level");
        log.info("info level");
        log.warn("warn level");
        log.error("error level");
        return "success! log4jtest";
    }

    @GetMapping("/traceIdAopTest")
    public String traceIdAopTest() {
        log.info("====traceIdAopTest threadid:{}===",Thread.currentThread().getId());
        String traceId = treaceIdTestService.treaceIdTestService();
        log.info("====traceIdAopTest threadid:{}===",Thread.currentThread().getId());
       return traceId;
    }


}
