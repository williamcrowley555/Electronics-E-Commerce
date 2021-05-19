/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.dto.KhachHangDTO;
import java.time.LocalDate;
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
public class KhachHangBLLTest {
    
    public KhachHangBLLTest() {
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        KhachHangBLL instance = new KhachHangBLL();
        List<KhachHangDTO> expResult = null;
        List<KhachHangDTO> result = instance.findAll();
        assertNotNull(result);
    }

    /**
     * Test of findById method, of class KhachHangBLL.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 2L;
        KhachHangBLL instance = new KhachHangBLL();
        KhachHangDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    /**
     * Test of findBySdt method, of class KhachHangBLL.
     */
    @Test
    public void testFindBySdt() {
        System.out.println("findBySdt");
        String sdt = "0265423595";
        KhachHangBLL instance = new KhachHangBLL();
        KhachHangDTO result = instance.findBySdt(sdt);
        assertEquals(sdt, result.getSdt());
    }

    /**
     * Test of save method, of class KhachHangBLL.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        String ho = "Nguyễn";
        String ten = "Minh Mẫn";
        Boolean gioiTinh = true;
        String cmnd = "025995456768";
        String sdt = "0268548208";
        String diaChi = "24 đường Đông Du, Phường Bến Nghé, Quận 1, TP Hồ Chí Minh";
        LocalDate ngaySinh = LocalDate.of(1995,8,19);
        
        KhachHangDTO khachHang = new KhachHangDTO();
        khachHang.setHo(ho);
        khachHang.setTen(ten);
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setCmnd(cmnd);
        khachHang.setSdt(sdt);
        khachHang.setDiaChi(diaChi);
        khachHang.setNgaySinh(ngaySinh);
        
        KhachHangBLL instance = new KhachHangBLL();
        Long saveId = instance.save(khachHang);
        KhachHangDTO result = instance.findById(saveId);
        assertEquals(ho, result.getHo());
        assertEquals(ten, result.getTen());
        assertEquals(gioiTinh, result.getGioiTinh());
        assertEquals(cmnd, result.getCmnd());
        assertEquals(sdt, result.getSdt());
        assertEquals(diaChi, result.getDiaChi());
        assertEquals(ngaySinh, result.getNgaySinh());
    }

    /**
     * Test of update method, of class KhachHangBLL.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        KhachHangBLL instance = new KhachHangBLL();
        
        Long id = 5L;       
        String ho = "Hồ";
        KhachHangDTO khachHang = instance.findById(id);
        khachHang.setHo(ho);
        instance.update(khachHang);
        
        assertEquals(ho, instance.findById(id).getHo());
    }

    /**
     * Test of delete method, of class KhachHangBLL.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 6L;
        KhachHangBLL instance = new KhachHangBLL();
        instance.delete(id);
        KhachHangDTO result = instance.findById(id);
        assertNull(result);
    }
    
}
