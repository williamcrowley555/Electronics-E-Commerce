/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class InputValidatorUtilTest {
    
    public InputValidatorUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsValidPattern() {
        System.out.println("isValidPattern");
        String value = "Công viên";
        String regex = "[^A-Za-z0-9\\-\\s]";
        String message = "Invalid";
        String expResult = "";
        String result = InputValidatorUtil.isValidPattern(value, regex, message);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidPattern() {
        System.out.println("isValidPattern");
        String value = "@!@#@#!@123";
        String regex = "[^A-Za-z0-9\\-\\s]";
        String message = "Invalid";
        String result = InputValidatorUtil.isValidPattern(value, regex, message);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidPatternWithNull() {
        System.out.println("isValidPattern");
        String value = null;
        String regex = "[^A-Za-z0-9\\-\\s]";
        String message = "Invalid";
        String expResult = "";
        String result = InputValidatorUtil.isValidPattern(value, regex, message);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidName() {
        System.out.println("isValidName");
        String name = "Minh Khôi";
        boolean whitespace = true;
        String expResult = "";
        String result = InputValidatorUtil.isValidName(name, whitespace);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidName() {
        System.out.println("isValidName");
        String name = "Minh Khôi@#";
        boolean whitespace = true;
        String result = InputValidatorUtil.isValidName(name, whitespace);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidNameWithNull() {
        System.out.println("isValidName");
        String name = null;
        boolean whitespace = true;
        String expResult = "";
        String result = InputValidatorUtil.isValidName(name, whitespace);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidAddress() {
        System.out.println("isValidAddress");
        String name = "Bình Chánh";
        String expResult = "";
        String result = InputValidatorUtil.isValidAddress(name);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidAddress() {
        System.out.println("isValidAddress");
        String name = "Bình Chánh@#";
        String result = InputValidatorUtil.isValidAddress(name);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidAddressWithNull() {
        System.out.println("isValidAddress");
        String name = null;
        String expResult = "";
        String result = InputValidatorUtil.isValidAddress(name);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidInvoiceNumber() {
        System.out.println("isValidInvoiceNumber");
        String invoiceNumber = "HD009";
        String expResult = "";
        String result = InputValidatorUtil.isValidInvoiceNumber(invoiceNumber);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidInvoiceNumber() {
        System.out.println("isValidInvoiceNumber");
        String invoiceNumber = "HD009@#";
        String result = InputValidatorUtil.isValidInvoiceNumber(invoiceNumber);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidInvoiceNumberWithNull() {
        System.out.println("isValidInvoiceNumber");
        String invoiceNumber = null;
        String expResult = "";
        String result = InputValidatorUtil.isValidInvoiceNumber(invoiceNumber);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidBirthDate() {
        System.out.println("isValidBirthDate");
        LocalDate birthDate = LocalDate.of(2000, Month.NOVEMBER, 16);
        int smallestAge = 6;
        String expResult = "";
        String result = InputValidatorUtil.isValidBirthDate(birthDate, smallestAge);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidBirthDate() {
        System.out.println("isValidBirthDate");
        LocalDate birthDate = LocalDate.of(2021, Month.NOVEMBER, 16);
        int smallestAge = 6;
        String result = InputValidatorUtil.isValidBirthDate(birthDate, smallestAge);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidBirthDateWithNull() {
        System.out.println("isValidBirthDate");
        LocalDate birthDate = null;
        int smallestAge = 6;
        String result = InputValidatorUtil.isValidBirthDate(birthDate, smallestAge);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidStartDate() {
        System.out.println("isValidStartDate");
        LocalDate startDate = LocalDate.now();
        String expResult = "";
        String result = InputValidatorUtil.isValidStartDate(startDate);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidStartDate() {
        System.out.println("isValidStartDate");
        LocalDate startDate = LocalDate.of(2021, Month.APRIL, 22);
        String result = InputValidatorUtil.isValidStartDate(startDate);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidStartDateWithNull() {
        System.out.println("isValidStartDate");
        LocalDate startDate = null;
        String result = InputValidatorUtil.isValidStartDate(startDate);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidEndDate() {
        System.out.println("isValidEndDate");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2021, Month.MAY, 22);
        String expResult = "";
        String result = InputValidatorUtil.isValidEndDate(startDate, endDate);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidEndDate() {
        System.out.println("isValidEndDate");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minus(Period.ofDays(1));
        String result = InputValidatorUtil.isValidEndDate(startDate, endDate);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidEndDateWithNull() {
        System.out.println("isValidEndDate");
        LocalDate startDate = LocalDate.of(2021, Month.APRIL, 22);
        LocalDate endDate = null;
        String result = InputValidatorUtil.isValidEndDate(startDate, endDate);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidEmail() {
        System.out.println("isValidEmail");
        String email = "haminhkhoi69@gmail.com";
        String expResult = "";
        String result = InputValidatorUtil.isValidEmail(email);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidEmail() {
        System.out.println("isValidEmail");
        String email = "haminhkhoi69@gmail.com#%";
        String result = InputValidatorUtil.isValidEmail(email);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidEmailWithNull() {
        System.out.println("isValidEmail");
        String email = null;
        String expResult = "";
        String result = InputValidatorUtil.isValidEmail(email);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidInteger() {
        System.out.println("isInteger");
        String number = "69";
        String expResult = "";
        String result = InputValidatorUtil.isInteger(number);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidInteger() {
        System.out.println("isInteger");
        String number = "69.6";
        String result = InputValidatorUtil.isInteger(number);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidLong() {
        System.out.println("isLong");
        String number = "69";
        String expResult = "";
        String result = InputValidatorUtil.isLong(number);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidLong() {
        System.out.println("isLong");
        String number = "69.6";
        String result = InputValidatorUtil.isLong(number);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidNumber_String() {
        System.out.println("isVailidNumber");
        String number = "69";
        String expResult = "";
        String result = InputValidatorUtil.isVailidNumber(number);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvailidNumber_String() {
        System.out.println("isVailidNumber");
        String number = "abc";
        String result = InputValidatorUtil.isVailidNumber(number);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidNumber_StringWithNull() {
        System.out.println("isVailidNumber");
        String number = null;
        String result = InputValidatorUtil.isVailidNumber(number);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidNumber_3args() {
        System.out.println("isVailidNumber");
        String number = "69";
        Integer min = 1;
        Integer max = 100;
        String expResult = "";
        String result = InputValidatorUtil.isVailidNumber(number, min, max);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvailidNumber_3args() {
        System.out.println("isVailidNumber");
        String number = "abc";
        Integer min = 1;
        Integer max = 100;
        String result = InputValidatorUtil.isVailidNumber(number, min, max);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidNumber_3argsWithNull() {
        System.out.println("isVailidNumber");
        String number = null;
        Integer min = 1;
        Integer max = 100;
        String result = InputValidatorUtil.isVailidNumber(number, min, max);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidMoney() {
        System.out.println("isValidMoney");
        String money = "6000";
        String expResult = "";
        String result = InputValidatorUtil.isValidMoney(money);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvalidMoney() {
        System.out.println("isValidMoney");
        String money = "-6000";
        String result = InputValidatorUtil.isValidMoney(money);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsValidMoneyWithNull() {
        System.out.println("isValidMoney");
        String money = null;
        String result = InputValidatorUtil.isValidMoney(money);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidPhoneNumber() {
        System.out.println("isVailidPhoneNumber");
        String phoneNumber = "09332572030";
        String expResult = "";
        String result = InputValidatorUtil.isVailidPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvailidPhoneNumber() {
        System.out.println("isVailidPhoneNumber");
        String phoneNumber = "19332572030";
        String result = InputValidatorUtil.isVailidPhoneNumber(phoneNumber);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidPhoneNumberWithNull() {
        System.out.println("isVailidPhoneNumber");
        String phoneNumber = null;
        String result = InputValidatorUtil.isVailidPhoneNumber(phoneNumber);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidIdentityID() {
        System.out.println("isVailidIdentityID");
        String id = "025985777";
        String expResult = "";
        String result = InputValidatorUtil.isVailidIdentityID(id);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsInvailidIdentityID() {
        System.out.println("isVailidIdentityID");
        String id = "100";
        String result = InputValidatorUtil.isVailidIdentityID(id);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIsVailidIdentityIDWithNull() {
        System.out.println("isVailidIdentityID");
        String id = null;
        String result = InputValidatorUtil.isVailidIdentityID(id);
        assertFalse(result.isEmpty());
//        fail("The test case is a prototype.");
    }
}
