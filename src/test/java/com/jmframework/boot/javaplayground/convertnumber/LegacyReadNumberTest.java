package com.jmframework.boot.javaplayground.convertnumber;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>LegacyReadNumberTest</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-26 16:41
 **/
@Slf4j
public class LegacyReadNumberTest {
    private static final Map<Integer, String> numberToChinese = new HashMap<>();

    static {
        numberToChinese.put(0, "零");
        numberToChinese.put(1, "一");
        numberToChinese.put(2, "二");
        numberToChinese.put(3, "三");
        numberToChinese.put(4, "四");
        numberToChinese.put(5, "五");
        numberToChinese.put(6, "六");
        numberToChinese.put(7, "七");
        numberToChinese.put(8, "八");
        numberToChinese.put(9, "九");
        numberToChinese.put(10, "十");
    }

    private static final String[] scopes = {"亿", "万", "千", "百", "十"};

    @SuppressWarnings("WrapperTypeMayBePrimitive")
    @Test
    public void main() {
        log.error("Mode: {}", 10000100 % 10000);
        String number = "1001,1010,0000";
        String[] numbers = number.split(",");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < numbers.length; i++) {
            switch (numbers[i].length()) {
                case 1:
                    result.append(readUnitsDigit(numbers[i]));
                    break;
                case 2:
                    result.append(readTensDigit(numbers[i]));
                    break;
                case 3:
                    result.append(readHundredsDigit(numbers[i]));
                    break;
                case 4:
                    result.append(readThousandsDigit(numbers[i]));
                    break;
            }
            switch (numbers.length - i) {
                case 3:
                    result.append(scopes[0]);
                    break;
                case 2:
                    result.append(scopes[1]);
                    break;
            }
        }
        System.out.println(result);
    }

    /**
     * Read units digit
     *
     * @param unitsDigit units number string
     * @return result
     */
    StringBuffer readUnitsDigit(String unitsDigit) {
        StringBuffer result = new StringBuffer();
        int numberAt0 = Integer.parseInt(unitsDigit);
        result.append(numberToChinese.get(numberAt0));
        return result;
    }

    /**
     * Read tens digit
     *
     * @param tensDigit tens digit string
     * @return result
     */
    StringBuffer readTensDigit(String tensDigit) {
        int numberAt1 = Integer.parseInt(String.valueOf(tensDigit.charAt(1)));
        int numberAt0 = Integer.parseInt(String.valueOf(tensDigit.charAt(0)));
        StringBuffer result = new StringBuffer();
        if (numberAt0 == 1) {
            result.append(numberToChinese.get(10));
        } else {
            result.append(numberToChinese.get(numberAt0)).append(scopes[4]);
        }
        if (numberAt1 != 0) {
            result.append(numberToChinese.get(numberAt1));
        }
        return result;
    }

    /**
     * Read hundreds digit
     *
     * @param hundredsDigit hundreds digit string
     * @return result
     */
    StringBuffer readHundredsDigit(String hundredsDigit) {
        int numberAt2 = Integer.parseInt(String.valueOf(hundredsDigit.charAt(2)));
        int numberAt1 = Integer.parseInt(String.valueOf(hundredsDigit.charAt(1)));
        int numberAt0 = Integer.parseInt(String.valueOf(hundredsDigit.charAt(0)));
        StringBuffer result = new StringBuffer();
        result.append(numberToChinese.get(numberAt0)).append(scopes[3]);
        if (numberAt1 == 0 && numberAt2 == 0) {
            return result;
        }
        if (numberAt1 == 0 && numberAt2 != 0) {
            return result.append(numberToChinese.get(0)).append(numberToChinese.get(numberAt2));
        }
        return result.append(readTensDigit(hundredsDigit.substring(1)));
    }

    /**
     * Read thousands digit
     *
     * @param thousandsDigit thousands digit string
     * @return result
     */
    StringBuffer readThousandsDigit(String thousandsDigit) {
        int numberAt3 = Integer.parseInt(String.valueOf(thousandsDigit.charAt(3)));
        int numberAt2 = Integer.parseInt(String.valueOf(thousandsDigit.charAt(2)));
        int numberAt1 = Integer.parseInt(String.valueOf(thousandsDigit.charAt(1)));
        int numberAt0 = Integer.parseInt(String.valueOf(thousandsDigit.charAt(0)));
        StringBuffer result = new StringBuffer();
        if (numberAt0 == 0 && numberAt1 == 0 && numberAt2 == 0 && numberAt3 == 0) {
            return result;
        }
        if (numberAt0 != 0) {
            result.append(numberToChinese.get(numberAt0)).append(scopes[2]);
        } else {
            result.append(numberToChinese.get(numberAt0));
        }
        if (numberAt1 == 0 && numberAt2 == 0) {
            return result.append(numberToChinese.get(0)).append(numberToChinese.get(numberAt3));
        }
        if (numberAt1 == 0 && numberAt2 != 0) {
            return result.append(numberToChinese.get(0))
                         .append(readTensDigit(thousandsDigit.substring(2)));
        }
        if (numberAt1 != 0 && numberAt2 == 0) {
            return result.append(readHundredsDigit(thousandsDigit.substring(1)));
        }
        return result.append(readHundredsDigit(thousandsDigit.substring(1)));
    }
}
