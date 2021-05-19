/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.dto.TourDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hi
 */
public class TourBLLTest {
    
    public TourBLLTest() {
    }

   @Test
    public void testFindAll() {
        System.out.println("findAll");
        TourBLL instance = new TourBLL();
        List<TourDTO> result = instance.findAll();
        assertNotNull(result);
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 1L;
        TourBLL instance = new TourBLL();
        TourDTO expResult = null;
        TourDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave() {
        System.out.println("save");
        
        TourDTO tour = new TourDTO();
        String tenTour = "Tour Hà Nội";
        String dacDiem = "3 ngày";
        Long idLoaiDuLich = 1L;
        
        tour.setTenTour(tenTour);
        tour.setDacDiem(dacDiem);
        tour.setIdLoaiDuLich(idLoaiDuLich);
        
        TourBLL instance = new TourBLL();
        Long saveId = instance.save(tour);
        TourDTO result = instance.findById(saveId);
        
        assertEquals(result.getId(), saveId);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Long id  = 2L;
        String tenTour  = "Tour Sài Gòn";
        
        TourBLL instance = new TourBLL();
        TourDTO tour = instance.findById(id);
        tour.setTenTour(tenTour);
        instance.update(tour);
        
        TourDTO result = instance.findById(id);
        
        assertEquals(result.getTenTour(), tenTour);
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 2L;
        TourBLL instance = new TourBLL();
        instance.delete(id);
        TourDTO result = instance.findById(id);
        
        assertNull(result);
    }

    
}
