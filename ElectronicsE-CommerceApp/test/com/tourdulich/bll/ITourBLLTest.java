/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.bll.impl.TourBLL;
import com.tourdulich.dto.TourDTO;
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
 * @author Hi
 */
public class ITourBLLTest {
    
    public ITourBLLTest() {
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ITourBLL instance = new TourBLL();
        List<TourDTO> result = instance.findAll();
        assertNotNull(result);
    }

   
    
    @Test
    public void testFindByIdValid() {
        System.out.println("findById");
        Long id = 1L;
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertEquals(id, result.getId());
        
    }
    
    @Test
    public void testFindByIdInputNull() {
        System.out.println("findByIdInputNull");
        Long id = null;
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdNotExist() {
        System.out.println("findByIdNotExist");
        Long id = 100L;
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdInputString() {
        System.out.println("findByIdInputString");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("abc");
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputFloat() {
        System.out.println("findByIdInputFloat");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("1.5");
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputSpecialCharacters() {
        System.out.println("findByIdInputSpecialCharacters");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("@b1");
        ITourBLL instance = new TourBLL();
        TourDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSaveValid() {
        System.out.println("saveValid");
        
        TourDTO tour = new TourDTO();
        String tenTour = "Tour Hà Nội";
        String dacDiem = "3 ngày";
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertEquals(result.getId(), saveId);
    }
    
    @Test
    public void testSaveNull_1() {
        System.out.println("saveNull_1");
        
        TourDTO tour = new TourDTO();
        String tenTour = null;
        String dacDiem = null;
        Long idLoaiDuLich = null;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNull(instance.findById(saveId));
    }
    
    @Test
    public void testSaveNull_2() {
        System.out.println("saveNull_2");
        
        TourDTO tour = new TourDTO();
        String tenTour = null;
        String dacDiem = null;
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNull(instance.findById(saveId));
    }
    
    @Test
    public void testSaveNull_3() {
        System.out.println("saveNull_3");
        
        TourDTO tour = new TourDTO();
        String tenTour = null;
        String dacDiem = "5 ngày";
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNull(instance.findById(saveId));
    }
    
    @Test
    public void testSaveNull_4() {
        System.out.println("saveNull_4");
        
        TourDTO tour = new TourDTO();
        String tenTour = "Tour Tây Nguyên";
        String dacDiem =  null;
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNotNull(instance.findById(saveId));
    }
    
    @Test
    public void testSaveInValid_1() {
        System.out.println("saveValid_1");
        
        TourDTO tour = new TourDTO();
        String tenTour = "!@#@!#!#123";
        String dacDiem = "3 ngày";
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNull(result);
    }
    
    @Test
    public void testSaveInValid_2() {
        System.out.println("saveValid_2");
        
        TourDTO tour = new TourDTO();
        String tenTour = "!@#@!#!#123";
        String dacDiem = null;
        Long idLoaiDuLich = 100L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        ITourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertNull(result);
    }
    
    
    

    @Test
    public void testUpdateValid() {
        System.out.println("updateValid");
        Long id  = 2L;
        String tenTour  = "Tour Sài Gòn";
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        tour.setTenTour(tenTour);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getTenTour(), tenTour);
    }
    
    @Test
    public void testUpdateNotExistId() {
        System.out.println("updateNotExistId");
        Long id  = 200L;
        String tenTour  = "Tour Sài Gòn";
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        assertNull(tour);
    }
    
    @Test
    public void testUpdateNull_1() {
        System.out.println("updateNull_1");
        Long id  = 2L;
        String tenTour  = null;
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        String oldTenTour = tour.getTenTour();
        tour.setTenTour(tenTour);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getTenTour(), oldTenTour);
    }
    
    @Test
    public void testUpdateNull_2() {
        System.out.println("updateNull_2");
        Long id  = 1L;
        String dacDiem  = null;
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        
        tour.setDacDiem(dacDiem);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getDacDiem(), dacDiem);
    }
    
    @Test
    public void testUpdateNull_3() {
        System.out.println("updateNull_3");
        Long id  = 1L;
        Long idLoaiDuLich = null;
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        Long oldId = tour.getIdLoaiDuLich();
        tour.setIdLoaiDuLich(idLoaiDuLich);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertNotEquals(result.getDacDiem(), oldId);
    }
    
    @Test
    public void testUpdateInvalid_1() {
        System.out.println("updateInvalid_1");
        Long id  = 9L;
        String tenTour  = "!@#@!#!#@";
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        
        String oldTenTour = tour.getTenTour();
        tour.setTenTour(tenTour);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getTenTour(), oldTenTour);
    }
    
    @Test
    public void testUpdateInvalid_2() {
        System.out.println("updateInvalid_2");
        Long id  = 9L;
        Long idLoaiDuLich  = 100L;
        
        ITourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        Long oldId = tour.getIdLoaiDuLich();
        String oldTenTour = tour.getTenTour();
        tour.setIdLoaiDuLich(idLoaiDuLich);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getIdLoaiDuLich(), oldId);
    }
    

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 2L;
        ITourBLL instance = new TourBLL();
        instance.delete(id);
        TourDTO result = instance.findById(id);
        
        assertNull(result);
    }
    
    @Test
    public void testDeleteNotExistId() {
        System.out.println("deleteNotExistId");
        Long id = 2L;
        ITourBLL instance = new TourBLL();
        instance.delete(id);
        TourDTO result = instance.findById(id);
        
        assertNull(result);
    }
}
