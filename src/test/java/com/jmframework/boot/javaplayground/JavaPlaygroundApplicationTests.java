package com.jmframework.boot.javaplayground;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaPlaygroundApplicationTests {

    @Test
    public void contextLoads() {
        log.error("Test message: {}", "Hello, world!");
    }

}
