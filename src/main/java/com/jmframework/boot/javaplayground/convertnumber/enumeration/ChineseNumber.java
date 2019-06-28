package com.jmframework.boot.javaplayground.convertnumber.enumeration;

import lombok.Getter;

/**
 * <h1>ChineseNumber</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-27 10:29
 **/
@Getter
@SuppressWarnings("unused")
public enum ChineseNumber {
    /**
     * Zero
     */
    ZERO(0, "〇", "零"),
    /**
     * ONE
     */
    ONE(1, "一", "壹"),
    /**
     * TWO
     */
    TWO(2, "二", "贰"),
    /**
     * THREE
     */
    THREE(3, "三", "叁"),
    /**
     * FOUR
     */
    FOUR(4, "四", "肆"),
    /**
     * FIVE
     */
    FIVE(5, "五", "伍"),
    /**
     * SIX
     */
    SIX(6, "六", "陆"),
    /**
     * SEVEN
     */
    SEVEN(7, "七", "柒"),
    /**
     * EIGHT
     */
    EIGHT(8, "八", "捌"),
    /**
     * NINE
     */
    NINE(9, "九", "玖"),
    /**
     * TEN
     */
    TEN(10, "十", "拾");

    private Integer value;
    private String lowercaseChinese;
    private String uppercaseChinese;

    ChineseNumber(Integer value, String lowercaseChinese, String uppercaseChinese) {
        this.value = value;
        this.lowercaseChinese = lowercaseChinese;
        this.uppercaseChinese = uppercaseChinese;
    }

    public String getChineseNumber(boolean caseFlag) {
        return caseFlag ? uppercaseChinese : lowercaseChinese;
    }

    /**
     * Get Chinese number by value
     *
     * @param value    zero to ten
     * @param caseFlag false - lowercase; true - uppercase
     * @return Chinese number
     */
    public static String getChineseNumberByValue(Integer value, boolean caseFlag) {
        ChineseNumber result = ChineseNumber.ZERO;
        ChineseNumber[] chineseNumbers = ChineseNumber.values();
        for (ChineseNumber chineseNumber : chineseNumbers) {
            if (chineseNumber.value.equals(value)) {
                result = chineseNumber;
            }
        }
        return caseFlag ? result.uppercaseChinese : result.lowercaseChinese;
    }
}
