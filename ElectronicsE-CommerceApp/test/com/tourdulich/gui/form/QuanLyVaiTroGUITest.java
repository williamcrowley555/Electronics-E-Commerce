/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.form;

import java.util.Vector;
import javax.swing.JTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Khoa Nguyen
 */
public class QuanLyVaiTroGUITest {
    
    public QuanLyVaiTroGUITest() {
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

    /**
     * Test of loadTableData method, of class QuanLyVaiTroGUI.
     */
    @Test
    public void testLoadTableData() {
        System.out.println("loadTableData");
        QuanLyVaiTroGUI instance = new QuanLyVaiTroGUI();
        instance.loadTableData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createHeader method, of class QuanLyVaiTroGUI.
     */
    @Test
    public void testCreateHeader() {
        System.out.println("createHeader");
        Object[] columnNames = null;
        QuanLyVaiTroGUI instance = new QuanLyVaiTroGUI();
        Vector expResult = null;
        Vector result = instance.createHeader(columnNames);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of headerColor method, of class QuanLyVaiTroGUI.
     */
    @Test
    public void testHeaderColor() {
        System.out.println("headerColor");
        int r = 0;
        int b = 0;
        int g = 0;
        JTable table = null;
        QuanLyVaiTroGUI instance = new QuanLyVaiTroGUI();
        instance.headerColor(r, b, g, table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
