package com.jmframework.boot.javaplayground.convertnumber.enumeration;

import lombok.Getter;

import java.math.BigInteger;

/**
 * <h1>NumberUnit</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-27 10:49
 **/
@Getter
public enum NumberUnit {
    /**
     * One: 个
     */
    ONE(new BigInteger("1"), "one", "个"),
    /**
     * Ten: 十
     */
    TEN(new BigInteger("10"), "ten", "十"),
    /**
     * Hundred: 百
     */
    HUNDRED(new BigInteger("100"), "hundred", "百"),
    /**
     * Thousand: 千
     */
    THOUSAND(new BigInteger("1000"), "thousand", "千"),
    /**
     * Ten thousand: 万
     */
    TEN_THOUSAND(new BigInteger("10000"), "ten thousand", "万"),
    /**
     * Hundred million: 亿
     */
    HUNDRED_MILLION(new BigInteger("100000000"), "hundred million", "亿"),
    /**
     * Trillion: 兆
     */
    TRILLION(new BigInteger("1000000000000"), "trillion", "兆"),
    /**
     * Ten quadrillion: 京
     */
    TEN_QUADRILLION(new BigInteger("10000000000000000"), "ten quadrillion", "京");

    private BigInteger value;
    private String englishWord;
    private String chineseWord;

    NumberUnit(BigInteger value, String englishWord, String chineseWord) {
        this.value = value;
        this.englishWord = englishWord;
        this.chineseWord = chineseWord;
    }
}
