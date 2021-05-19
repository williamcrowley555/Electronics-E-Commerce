/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.bll.IChiPhiDoanBLL;
import com.tourdulich.bll.impl.ChiPhiDoanBLL;
import com.tourdulich.dto.ChiPhiDoanDTO;
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
public class ChiPhiDoanDALTest {
    public ChiPhiDoanDALTest(){}
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IChiPhiDoanBLL instance = new ChiPhiDoanBLL();
        List<ChiPhiDoanDTO> expResult = null;
        List<ChiPhiDoanDTO> result = instance.findAll();
        assertNotNull(result);
    }
    
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 1L;
        IChiPhiDoanBLL instance = new ChiPhiDoanBLL();
        ChiPhiDoanDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testSave()
    {
        System.out.println("save");
        Long idTour = 411L;
        Long idDoan = 4111L;
        Long idDichVu = 21L;
        String hoaDon = "HD004";
        LocalDate ngayHoaDon = LocalDate.of(2021, Month.JUNE, 1);
        Integer chiPhi = 2500000;
       
        ChiPhiDoanDTO chiPhiDoan = new ChiPhiDoanDTO();
        chiPhiDoan.setIdDoan(idDoan);
        chiPhiDoan.setIdDichVu(idDichVu);
        chiPhiDoan.setHoaDon(hoaDon);
        chiPhiDoan.setNgayHoaDon(ngayHoaDon);
        chiPhiDoan.setChiPhi(chiPhi);
       
        IChiPhiDoanBLL instance = new ChiPhiDoanBLL();
        Long savedId = instance.save(chiPhiDoan);
        ChiPhiDoanDTO result = instance.findById(savedId);
       
        assertEquals(idDoan, result.getIdDoan());
        assertEquals(idDichVu, result.getIdDichVu());
        assertEquals(hoaDon, result.getHoaDon());
        assertEquals(ngayHoaDon, result.getNgayHoaDon());
        assertEquals(chiPhi, result.getChiPhi());
    }
    
    @Test
    public void testUpdate() {
        System.out.println("update");
        Long id = 4L;
        Integer chiPhi = 100000;
        IChiPhiDoanBLL instance = new ChiPhiDoanBLL();
        ChiPhiDoanDTO chiPhiDoan = instance.findById(id);
        chiPhiDoan.setChiPhi(chiPhi);
        instance.update(chiPhiDoan);
        assertEquals(chiPhi, instance.findById(id).getChiPhi());
    }
}
