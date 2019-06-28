package com.jmframework.boot.javaplayground.convertnumber;

import com.jmframework.boot.javaplayground.common.KeyInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;

/**
 * <h1>ConvertNumberTest</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-28 14:10
 **/
@Slf4j
public class ConvertNumberTest {
    @Test
    public void convertNumberTest() {
        log.error("Input big integer value: example 101010001000100011");
        BigInteger bigInteger = KeyInput.readBigInteger();
        log.error("Convert big integer to words: {}", NumberConverter.convertToChineseWords(bigInteger, false));
    }
}
