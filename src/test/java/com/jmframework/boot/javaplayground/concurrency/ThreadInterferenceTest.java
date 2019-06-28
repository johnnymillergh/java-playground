package com.jmframework.boot.javaplayground.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <h1>ThreadInterferenceTest</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-26 14:17
 **/
@Slf4j
public class ThreadInterferenceTest {
    private Counter counter = new Counter();

    private class SharedDataThreadA implements Runnable {
        @Override
        public void run() {
            log.error("------------------------------------------------");
            log.error("Peak Counter's value: {}",
                      counter.getC());
            counter.increment();
            log.error("Counter's value: {}. Manipulated by {}, {}",
                      counter.getC(),
                      Thread.currentThread().getName(),
                      getClass());
            log.error("------------------------------------------------");
        }
    }

    private class SharedDataThreadB implements Runnable {
        @Override
        public void run() {
            log.error("------------------------------------------------");
            log.error("Peak Counter's value: {}",
                      counter.getC());
            counter.decrement();
            log.error("Counter's value: {}. Manipulated by {}, {}",
                      counter.getC(),
                      Thread.currentThread().getName(),
                      getClass());
            log.error("------------------------------------------------");
        }
    }

    @Test
    public void interferenceTest() {
        Thread threadA = new Thread(new SharedDataThreadA());
        Thread threadB = new Thread(new SharedDataThreadB());
        threadA.start();
        threadB.start();
        log.error("Counter's value: {}. Accessed by {}, {}",
                  counter.getC(),
                  Thread.currentThread().getName(),
                  getClass());
    }
}
