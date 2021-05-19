/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.NhanVienDTO;
import com.tourdulich.util.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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
public class NhanVienDALTest {
    
    public NhanVienDALTest() {
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
    public void testFindAll() {
        System.out.println("findAll");
        NhanVienDAL instance = new NhanVienDAL();
        List<NhanVienDTO> result = instance.findAll();
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        boolean status = true;
        NhanVienDAL instance = new NhanVienDAL();
        List<NhanVienDTO> result = instance.findByStatus(status);
        for (NhanVienDTO nhanVien : result) {
            assertEquals(nhanVien.getTrangThai(), status);
        }
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 1L;
        NhanVienDAL instance = new NhanVienDAL();
        NhanVienDTO result = instance.findById(id);
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindBySdt() {
        System.out.println("findBySdt");
        String sdt = "0114589521";
        NhanVienDAL instance = new NhanVienDAL();
        NhanVienDTO result = instance.findBySdt(sdt);
        assertEquals(sdt, result.getSdt());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() throws IOException {
        System.out.println("save");
        String ho = "Lê";
        String ten = "Thị Nhung";
        Boolean gioiTinh = false;
        LocalDate ngaySinh = LocalDate.of(1996, Month.MARCH, 22);
        String diaChi = "273 An Dương Vương, Q.5. TP.HCM";
        String sdt = "0969653020";
        File hinhAnh = new File("D:\\Project\\Java Project\\TourDuLich\\TourDuLich\\gallery\\justin.jpg");
        byte[] hinhAnhBytes = ImageUtil.getByteArray(hinhAnh);
        Long idVaiTro = 2L;
        
        NhanVienDTO nhanVien = new NhanVienDTO();
        nhanVien.setHo(ho);
        nhanVien.setTen(ten);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setNgaySinh(ngaySinh);
        nhanVien.setDiaChi(diaChi);
        nhanVien.setSdt(sdt);
        nhanVien.setHinhAnh(hinhAnhBytes);
        nhanVien.setIdVaiTro(idVaiTro);
        
        NhanVienDAL instance = new NhanVienDAL();
        Long savedId = instance.save(nhanVien);
        NhanVienDTO result = instance.findById(savedId);
        assertEquals(ho, result.getHo());
        assertEquals(ten, result.getTen());
        assertEquals(gioiTinh, result.getGioiTinh());
        assertEquals(ngaySinh, result.getNgaySinh());
        assertEquals(diaChi, result.getDiaChi());
        assertEquals(sdt, result.getSdt());
        assertEquals(hinhAnhBytes, result.getHinhAnh());
        assertEquals(idVaiTro, result.getIdVaiTro());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Long nhanVienId = 1L;
        String ten = "Tuấn Dũng";
        NhanVienDAL instance = new NhanVienDAL();
        NhanVienDTO nhanVien = instance.findById(nhanVienId);
        nhanVien.setTen(ten);
        instance.update(nhanVien);
        assertEquals(ten, instance.findById(nhanVienId).getTen());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        boolean status = false;
        Long nhanVienId = 1L;
        NhanVienDAL instance = new NhanVienDAL();
        instance.updateStatus(status, nhanVienId);
        assertEquals(status, instance.findById(nhanVienId).getTrangThai());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 11L;
        NhanVienDAL instance = new NhanVienDAL();
        instance.delete(id);
        assertNull(instance.findById(id));
//        fail("The test case is a prototype.");
    }
    
}
