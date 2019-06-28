package com.jmframework.boot.javaplayground.common;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * <h1>KeyInput</h1>
 * <p>
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * @date 2019-06-27 08:40
 **/
@Slf4j
@SuppressWarnings({"WeakerAccess", "WrapperTypeMayBePrimitive"})
public class KeyInput {
    private static final String TRUE = "TRUE";
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(inputStreamReader);

    /**
     * Read integer
     *
     * @return integer
     */
    public static Integer readInteger() {
        Integer value = 0;
        try {
            value = Integer.parseInt(readStringWithThrowingException());
        } catch (Exception e) {
            log.error("Exception occurred when read integer. Exception message: {}", e.getMessage(), e);
        }
        return value;
    }

    /**
     * Read big integer
     *
     * @return big integer
     */
    public static BigInteger readBigInteger() {
        BigInteger value = new BigInteger("0");
        try {
            value = new BigInteger(readStringWithThrowingException());
        } catch (Exception e) {
            log.error("Exception occurred when read bit integer. Exception message: {}", e.getMessage(), e);
        }
        return value;
    }

    /**
     * Read float
     *
     * @return float
     */
    public static Float readFloat() {
        Float value = 0.0f;
        try {
            value = Float.parseFloat(readStringWithThrowingException());
        } catch (Exception e) {
            log.error("Exception occurred when read float. Exception message: {}", e.getMessage(), e);
        }
        return value;
    }

    /**
     * Read double
     *
     * @return double value
     */
    public static Double readDouble() {
        Double value = 0.0;
        try {
            value = Double.parseDouble(readStringWithThrowingException());
        } catch (Exception e) {
            log.error("Exception occurred when read double. Exception message: {}", e.getMessage(), e);
        }
        return value;
    }

    /**
     * Read boolean
     *
     * @return boolean value
     */
    public static Boolean readBoolean() {
        try {
            return TRUE.equals(readStringWithThrowingException().toUpperCase());
        } catch (Exception e) {
            log.error("Exception occurred when read boolean. Exception message: {}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * Read character
     *
     * @return character value
     */
    public static Character readCharacter() {
        try {
            return readStringWithThrowingException().charAt(0);
        } catch (Exception e) {
            log.error("Exception occurred when read character. Exception message: {}", e.getMessage(), e);
        }
        return ' ';
    }

    /**
     * Read string for public use
     *
     * @return string value
     */
    public static String readString() {
        String value = "";
        try {
            value = readStringWithThrowingException();
        } catch (Exception e) {
            log.error("Exception occurred when read string. Exception message: {}", e.getMessage(), e);
        }
        return value;
    }

    /**
     * Read string for private use (may throwing IO exception)
     *
     * @return string value
     * @throws IOException throwing IO exception
     */
    private static String readStringWithThrowingException() throws IOException {
        String value;
        value = reader.readLine();
        if (value.length() == 0) {
            value = reader.readLine();
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.print("Please input an integer number: ");
        System.out.println("Your input is " + KeyInput.readInteger());
        System.out.print("Please input a bit integer number: ");
        System.out.println("Your input is " + KeyInput.readBigInteger());
        System.out.print("Please input a float number: ");
        System.out.println("Your input is " + KeyInput.readFloat());
        System.out.print("Please input a double number: ");
        System.out.println("Your input is " + KeyInput.readDouble());
        System.out.print("Please input a string: ");
        System.out.println("Your input is " + KeyInput.readString());
        System.out.print("Please input a boolean value: ");
        System.out.println("Your input is " + KeyInput.readBoolean());
        System.out.print("Please input a char: ");
        System.out.println("Your input is " + KeyInput.readCharacter());
    }
}
