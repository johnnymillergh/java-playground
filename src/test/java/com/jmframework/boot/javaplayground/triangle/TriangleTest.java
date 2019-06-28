package com.jmframework.boot.javaplayground.triangle;

import org.junit.Test;

/**
 * <h1>TriangleTest</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-26 16:16
 **/
public class TriangleTest {
    @Test
    public void main() {
        int lengthOfEdge = 17;
        for (int lineNumber = 0; lineNumber < 9; lineNumber++) {
            for (int whitespace = 0; whitespace < lineNumber; whitespace++) {
                System.out.print(" ");
            }
            for (int star = 0; star < lengthOfEdge; star++) {
                if (star + lineNumber < lengthOfEdge - lineNumber) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
