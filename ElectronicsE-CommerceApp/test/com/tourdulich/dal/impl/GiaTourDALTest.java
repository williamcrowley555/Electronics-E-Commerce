/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.bll.IGiaTourBLL;
import com.tourdulich.bll.impl.GiaTourBLL;
import com.tourdulich.dto.GiaTourDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class GiaTourDALTest {
    public GiaTourDALTest(){}
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IGiaTourBLL instance = new GiaTourBLL();
        List<GiaTourDTO> result = instance.findAll();
        assertNotNull(result);
    }
    
    @Test
    public void testSave() {
        System.out.println("save");
        Long idTour = 411L;
        Long giaTien = 1000000L;
        LocalDate ngayBatDau = LocalDate.of(2021, Month.JUNE, 1);
        LocalDate ngayKetThuc = LocalDate.of(2021, Month.JUNE, 3);
        
        GiaTourDTO giaTour = new GiaTourDTO();
        giaTour.setIdTour(idTour);
        giaTour.setGiaTien(giaTien);
        giaTour.setNgayBatDau(ngayBatDau);
        giaTour.setNgayKetThuc(ngayKetThuc);
        
        IGiaTourBLL instance = new GiaTourBLL();
        Long savedId = instance.save(giaTour);
        GiaTourDTO result = instance.findById(savedId);
        
        assertEquals(giaTien, result.getGiaTien());
        assertEquals(ngayBatDau, result.getNgayBatDau());
        assertEquals(ngayKetThuc, result.getNgayKetThuc());
    }
    
    @Test
    public void testUpdate() {
        System.out.println("update");
        Long idTour = 2L;
        Long giaTien= 5000000L;      
        IGiaTourBLL instance = new GiaTourBLL();
        GiaTourDTO giaTour = instance.findById(idTour);
        giaTour.setGiaTien(giaTien);
        instance.update(giaTour);
        assertEquals(giaTien, instance.findById(idTour).getGiaTien());
    }
}
