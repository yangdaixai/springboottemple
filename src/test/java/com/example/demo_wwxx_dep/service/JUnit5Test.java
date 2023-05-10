package com.example.demo_wwxx_dep.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 杨圣伟
 * @description
 * @date 2023-05-09-16:47
 */

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JUnit5Test {

    private final ThreadPoolConfigTest threadPoolConfigTest;
    @Test
    public void test() {
        System.out.println("=====");
        threadPoolConfigTest.threadPoolConfigTest();
    }

}
