package com.jmframework.boot.javaplayground.convertnumber;

import com.jmframework.boot.javaplayground.convertnumber.enumeration.ChineseNumber;
import com.jmframework.boot.javaplayground.convertnumber.enumeration.NumberUnit;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * <h1>NumberConverter</h1>
 * <p>
 * Convert number to words. Supported language: Simplified Chinese (zh-CN)
 * <p>
 * TODO: support English (en-US) later
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-27 09:33
 **/
@Slf4j
@SuppressWarnings({"unused", "WeakerAccess", "SameParameterValue"})
public class NumberConverter {
    /**
     * Convert big integer value to words (Chinese version)
     * <p>
     * Example 1: 10,0010,0010,0010,0011 -> 十京十兆十亿十万〇一十一
     * <p>
     * Example 2: 10,1010,0010,0010,0011 -> 十京一千〇一十兆十亿十万〇一十一
     * <p>
     * Unit test: com.jmframework.boot.javaplayground.convertnumber.ConvertNumberTest#convertNumberTest()
     *
     * @param value    big integer value
     * @param caseFlag false - lowercase Chinese; true - uppercase Chinese
     * @return number in words
     */
    public static String convertToChineseWords(BigInteger value, boolean caseFlag) {
        StringBuilder result = new StringBuilder();
        BigInteger tenQuadrillionSegment = value.divide(NumberUnit.TEN_QUADRILLION.getValue());
        if (!BigInteger.ZERO.equals(tenQuadrillionSegment)) {
            if (tenQuadrillionSegment.bitCount() >= 3) {
                throw new IllegalArgumentException("Number overflows: " + value.toString());
            }
            result.append(readNumberWithinTenThousand(tenQuadrillionSegment.toString(), caseFlag))
                  .append(NumberUnit.TEN_QUADRILLION.getChineseWord());
        }

        BigInteger trillionSegment = value.divide(NumberUnit.TRILLION.getValue())
                                          .mod(NumberUnit.TEN_THOUSAND.getValue());
        if (!BigInteger.ZERO.equals(trillionSegment)) {
            result.append(readNumberWithinTenThousand(trillionSegment.toString(), caseFlag))
                  .append(NumberUnit.TRILLION.getChineseWord());
        }

        BigInteger hundredMillionSegment = value.divide(NumberUnit.HUNDRED_MILLION.getValue())
                                                .mod(NumberUnit.TEN_THOUSAND.getValue());
        if (!BigInteger.ZERO.equals(hundredMillionSegment)) {
            result.append(readNumberWithinTenThousand(hundredMillionSegment.toString(), caseFlag))
                  .append(NumberUnit.HUNDRED_MILLION.getChineseWord());
        }

        BigInteger tenThousandSegment = value.divide(NumberUnit.TEN_THOUSAND.getValue())
                                             .mod(NumberUnit.TEN_THOUSAND.getValue());
        if (!BigInteger.ZERO.equals(tenThousandSegment)) {
            result.append(readNumberWithinTenThousand(tenThousandSegment.toString(), caseFlag))
                  .append(NumberUnit.TEN_THOUSAND.getChineseWord());
        }

        BigInteger thousandSegment = value.mod(NumberUnit.TEN_THOUSAND.getValue());
        if (!BigInteger.ZERO.equals(thousandSegment)) {
            result.append(readNumberWithinTenThousand(value.toString().substring(value.toString().length() - 4),
                                                      caseFlag));
        }
        return result.toString().replaceAll("一十", "十");
    }

    /**
     * Read number ranged from 0 (or 0000) to 9999
     *
     * @param value    4-bit number
     * @param caseFlag false - lowercase Chinese; true - uppercase Chinese
     * @return number in words (Chinese version)
     */
    @SuppressWarnings("Duplicates")
    private static StringBuilder readNumberWithinTenThousand(String value, boolean caseFlag) {
        BigInteger bigIntegerValue = new BigInteger(value);
        StringBuilder numberInWords = new StringBuilder();
        // Case 0: all numbers are zero
        if (bigIntegerValue.equals(BigInteger.ZERO)) {
            return numberInWords;
        }

        // Case 1: read number like 0010 segment of the last part of the whole number, and need to add "〇 (or 零)"
        if (1 <= bigIntegerValue.toString().length() && bigIntegerValue.toString().length() <= 2
                && value.length() > 2) {
            numberInWords.append(ChineseNumber.ZERO.getChineseNumber(caseFlag));
            BigInteger tenValue = bigIntegerValue.divide(NumberUnit.TEN.getValue()).mod(NumberUnit.TEN.getValue());
            if (bigIntegerValue.toString().length() == 2) {
                numberInWords.append(ChineseNumber.getChineseNumberByValue(tenValue.intValue(), caseFlag));
            }
            if (!BigInteger.ZERO.equals(tenValue)) {
                numberInWords.append(NumberUnit.TEN.getChineseWord());
            }

            BigInteger oneValue = bigIntegerValue.mod(NumberUnit.TEN.getValue());
            if (!BigInteger.ZERO.equals(oneValue)) {
                numberInWords.append(ChineseNumber.getChineseNumberByValue(oneValue.intValue(), caseFlag));
            }
            return numberInWords;
        }

        // Case 2: Thousand or hundred digit is not zero (don't need to add "〇 (or 零)")
        BigInteger thousandValue = bigIntegerValue.divide(NumberUnit.THOUSAND.getValue());
        BigInteger hundredValue = bigIntegerValue.divide(NumberUnit.HUNDRED.getValue()).mod(NumberUnit.TEN.getValue());
        BigInteger tenValue = bigIntegerValue.divide(NumberUnit.TEN.getValue()).mod(NumberUnit.TEN.getValue());
        BigInteger oneValue = bigIntegerValue.mod(NumberUnit.TEN.getValue());

        if (bigIntegerValue.toString().length() == 4) {
            numberInWords.append(ChineseNumber.getChineseNumberByValue(thousandValue.intValue(), caseFlag));
        }
        if (!BigInteger.ZERO.equals(thousandValue)) {
            numberInWords.append(NumberUnit.THOUSAND.getChineseWord());
        }

        if (bigIntegerValue.toString().length() == 3 ||
                bigIntegerValue.toString().length() == 4 &&
                        (!BigInteger.ZERO.equals(tenValue) || !BigInteger.ZERO.equals(oneValue))) {
            numberInWords.append(ChineseNumber.getChineseNumberByValue(hundredValue.intValue(), caseFlag));
        }
        if (!BigInteger.ZERO.equals(hundredValue)) {
            numberInWords.append(NumberUnit.HUNDRED.getChineseWord());
        }

        if (bigIntegerValue.toString().length() == 2 ||
                (BigInteger.ZERO.equals(hundredValue)) && !BigInteger.ZERO.equals(tenValue)) {
            numberInWords.append(ChineseNumber.getChineseNumberByValue(tenValue.intValue(), caseFlag));
        }
        if (!BigInteger.ZERO.equals(tenValue)) {
            numberInWords.append(NumberUnit.TEN.getChineseWord());
        }

        if (!BigInteger.ZERO.equals(oneValue)) {
            numberInWords.append(ChineseNumber.getChineseNumberByValue(oneValue.intValue(), caseFlag));
        }
        return numberInWords;
    }
}
