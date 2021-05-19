/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dal.impl.DoanDAL;
import com.tourdulich.dto.DoanDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author HP
 */
public class IDoanDALTest {
    
    public IDoanDALTest() {
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IDoanDAL instance = new DoanDAL();
        List<DoanDTO> result = instance.findAll();
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByExistingId() {
        System.out.println("findById");
        Long id = 2911L;
        IDoanDAL instance = new DoanDAL();
        DoanDTO result = instance.findById(id);
        assertEquals(id, result.getId());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByNotExistingId() {
        System.out.println("findById");
        Long id = 100L;
        IDoanDAL instance = new DoanDAL();
        DoanDTO result = instance.findById(id);
        assertNull(result);
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFindByInvalidId() {
        System.out.println("findById");
        thrown.expect(NumberFormatException.class);
        String textId = "abc";
        Long id = Long.valueOf(textId);
        IDoanDAL instance = new DoanDAL();
        DoanDTO result = instance.findById(id);
        assertNull(result);
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFindByExistingIdTour() {
        System.out.println("findByIdTour");
        Long idTour = 291L;
        IDoanDAL instance = new DoanDAL();
        List<DoanDTO> result = instance.findByIdTour(idTour);
        for (DoanDTO doan : result) {
            assertEquals(idTour, doan.getIdTour());
        }
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFindByNotExistingIdTour() {
        System.out.println("findByIdTour");
        Long idTour = 100L;
        IDoanDAL instance = new DoanDAL();
        List<DoanDTO> result = instance.findByIdTour(idTour);
        assertTrue(result.isEmpty());
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFindByInvalidIdTour() {
        System.out.println("findByIdTour");
        thrown.expect(NumberFormatException.class);
        String textIdTour = "abc";
        Long idTour = Long.valueOf(textIdTour);
        IDoanDAL instance = new DoanDAL();
        List<DoanDTO> result = instance.findByIdTour(idTour);
        assertTrue(result.isEmpty());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveValidData() {
        System.out.println("save");
        String tenDoan = "Thảo Cầm Viên Sài Gòn";
        LocalDate ngayKhoiHanh = LocalDate.of(2021, Month.APRIL, 22);
        LocalDate ngayKetThuc = LocalDate.of(2021, Month.APRIL, 23);
        Long idTour = 291L;
        
        DoanDTO doan = new DoanDTO();
        doan.setTenDoan(tenDoan);
        doan.setNgayKhoiHanh(ngayKhoiHanh);
        doan.setNgayKetThuc(ngayKetThuc);
        doan.setIdTour(idTour);
        
        IDoanDAL instance = new DoanDAL();
        Long savedId = instance.save(doan);
        
        assertNotNull(savedId);
        
        DoanDTO result = instance.findById(savedId);
        
        assertEquals(tenDoan, result.getTenDoan());
        assertEquals(ngayKhoiHanh, result.getNgayKhoiHanh());
        assertEquals(ngayKetThuc, result.getNgayKetThuc());
        assertEquals(idTour, result.getIdTour());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveNullData() {
        System.out.println("save");
        
        DoanDTO doan = new DoanDTO();
        
        IDoanDAL instance = new DoanDAL();
        Long savedId = instance.save(doan);
        DoanDTO result = instance.findById(savedId);
        
        assertNull(result.getTenDoan());
        assertNull(result.getNgayKhoiHanh());
        assertNull(result.getNgayKetThuc());
        assertNull(result.getIdTour());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateValidGroupName() {
        System.out.println("update");
        Long doanId = 5L;
        String tenDoan = "Du lịch Đà Lạt";
        IDoanDAL instance = new DoanDAL();
        DoanDTO doan = instance.findById(doanId);
        doan.setTenDoan(tenDoan);
        instance.update(doan);
        assertEquals(tenDoan, instance.findById(doanId).getTenDoan());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateInValidGroupName() {
        System.out.println("update");
        Long doanId = 5L;
        String tenDoan = "@!@#@#!@123";
        IDoanDAL instance = new DoanDAL();
        DoanDTO doan = instance.findById(doanId);
        doan.setTenDoan(tenDoan);
        instance.update(doan);
        assertEquals(tenDoan, instance.findById(doanId).getTenDoan());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateValidAmount() {
        System.out.println("updateAmount");
        Long doanId = 5L;
        int amount = 6;
        IDoanDAL instance = new DoanDAL();
        instance.updateAmount(doanId, amount);
        assertEquals(amount, instance.findById(doanId).getSoLuong());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateInvalidAmount() {
        System.out.println("updateAmount");
        Long doanId = 5L;
        int amount = -6;
        IDoanDAL instance = new DoanDAL();
        instance.updateAmount(doanId, amount);
        assertEquals(amount, instance.findById(doanId).getSoLuong());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 5L;
        IDoanDAL instance = new DoanDAL();
        instance.delete(id);
        assertNull(instance.findById(id));
//        fail("The test case is a prototype.");
    }
}
