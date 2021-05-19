/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.tourdulich.dto.DoanDTO;
import com.tourdulich.dto.TourDTO;
import java.awt.Color;
import javax.swing.JComboBox;
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
public class PopUpCapVaiTroGUITest {
    
    public PopUpCapVaiTroGUITest() {
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
     * Test of setLabelText method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testSetLabelText() {
        System.out.println("setLabelText");
        DoanDTO doan = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.setLabelText(doan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadTableData method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testLoadTableData() {
        System.out.println("loadTableData");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.loadTableData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateForm method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testValidateForm() {
        System.out.println("validateForm");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        boolean expResult = false;
        boolean result = instance.validateForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComboBox method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testSetComboBox() {
        System.out.println("setComboBox");
        JComboBox<String> comboBox = null;
        String[] listItems = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.setComboBox(comboBox, listItems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTourItems method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testGetTourItems() {
        System.out.println("getTourItems");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        String[] expResult = null;
        String[] result = instance.getTourItems();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTourItemName method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testGetTourItemName() {
        System.out.println("getTourItemName");
        TourDTO tour = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        String expResult = "";
        String result = instance.getTourItemName(tour);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoanItems method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testGetDoanItems() {
        System.out.println("getDoanItems");
        Long idTour = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        String[] expResult = null;
        String[] result = instance.getDoanItems(idTour);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoanItemName method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testGetDoanItemName() {
        System.out.println("getDoanItemName");
        DoanDTO doan = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        String expResult = "";
        String result = instance.getDoanItemName(doan);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComboBoxDoan method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testSetComboBoxDoan() {
        System.out.println("setComboBoxDoan");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.setComboBoxDoan();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CustomWindow method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testCustomWindow() {
        System.out.println("CustomWindow");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.CustomWindow();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of myComboBox method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testMyComboBox() {
        System.out.println("myComboBox");
        JComboBox box = null;
        Color color = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        JComboBox expResult = null;
        JComboBox result = instance.myComboBox(box, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of center method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testCenter() {
        System.out.println("center");
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.center();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of headerColor method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testHeaderColor() {
        System.out.println("headerColor");
        int r = 0;
        int b = 0;
        int g = 0;
        JTable table = null;
        PopUpCapVaiTroGUI instance = new PopUpCapVaiTroGUI();
        instance.headerColor(r, b, g, table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PopUpCapVaiTroGUI.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PopUpCapVaiTroGUI.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
