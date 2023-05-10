package com.example.demo_wwxx_dep.aop;

import com.example.demo_wwxx_dep.controller.AbstractTracerRunnableTestController;
import com.example.demo_wwxx_dep.service.TreaceIdTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-08-15:57
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Order(-1)
public class TreaceIdAspect {

    private final HttpServletRequest request;
    private final static String TRACE_ID = "TRACE_ID";

    /**
     * 以 AbstractTracerRunnableTestController 包下定义的所有请求为切入点
     */

    @Pointcut("execution(* com.example.demo_wwxx_dep.controller..*.*(..))")
    public void TracerIdAnyMethodAop() {}

    @Around("TracerIdAnyMethodAop()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在方法执行前进行处理
        // ...
        setTraceId();

        // 执行被代理方法
        Object result = joinPoint.proceed();

        // 在方法执行后进行处理
        // ...

        return result;
    }

    public void setTraceId(){
        if (request != null && request.getHeader("Trace-Id") != null) {
            MDC.put(TRACE_ID, request.getHeader("Trace-Id"));
        }else{
            MDC.put("TRACE_ID", UUID.randomUUID().toString());
        }
    }

}
