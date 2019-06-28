package com.jmframework.boot.javaplayground.convertnumber;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.CurrencyAmount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * <h1>ConvertNumberInWordsUsingICU4JTest</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-27 09:09
 **/
@Slf4j
@SuppressWarnings("unused")
public class ConvertNumberInWordsUsingICU4JTest {
    /**
     * Translate
     *
     * @param countryCode      country code
     * @param language         language
     * @param val              value
     * @param fractionUnitName
     * @return
     */
    public static String translate(String countryCode, String language, String val, String fractionUnitName) {
        StringBuilder result = new StringBuilder();

        Locale locale = new Locale(language, countryCode);
        Currency currency = Currency.getInstance(locale);

        String[] inputArr = StringUtils.split(new BigDecimal(val).abs().toPlainString(), ".");
        RuleBasedNumberFormat rule = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);

        int i = 0;
        for (String input : inputArr) {
            CurrencyAmount currencyAmount = new CurrencyAmount(new BigDecimal(input), currency);
            if (i++ == 0) {
                result.append(rule.format(currencyAmount))
                      .append(" ");
            } else {
//                result.append(rule.format(currencyAmount)).append(" ").append(fractionUnitName).append(" ");
            }
        }
        return result.toString();
    }

    @Test
    public void main() {
        String countryCode = "CN";
        String language = "zh";
        String inputValue = "101090001000100011.0";
        String result = translate(countryCode, language, inputValue, "Cents");
        log.error("Input: {}, result: {}", inputValue, result);
        inputValue = "100010.0";
        result = translate(countryCode, language, inputValue, "Cents");
        log.error("Input: {}, result: {}", inputValue, result);
    }
}
