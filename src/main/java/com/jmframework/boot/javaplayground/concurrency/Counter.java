package com.jmframework.boot.javaplayground.concurrency;

import lombok.Getter;

/**
 * <h1>Counter</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-26 14:09
 **/
@Getter
class Counter {
    private int c = 0;

    synchronized public void increment() {
        c++;
    }

    synchronized public void decrement() {
        c--;
    }
}
