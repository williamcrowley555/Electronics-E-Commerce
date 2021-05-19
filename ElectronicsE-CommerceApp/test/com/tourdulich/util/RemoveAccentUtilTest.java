/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

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
public class RemoveAccentUtilTest {
    
    public RemoveAccentUtilTest() {
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
    public void testRemoveAccent() {
        System.out.println("removeAccent");
        String s = "Phạm Đức Khải";
        String expResult = "Pham Duc Khai";
        String result = RemoveAccentUtil.removeAccent(s);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
