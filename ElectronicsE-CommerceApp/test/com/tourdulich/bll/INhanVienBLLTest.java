/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.bll.impl.NhanVienBLL;
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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author HP
 */
public class INhanVienBLLTest {
    
    public INhanVienBLLTest() {
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
        INhanVienBLL instance = new NhanVienBLL();
        List<NhanVienDTO> result = instance.findAll();
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByTrueStatus() {
        System.out.println("findByStatus");
        boolean status = true;
        INhanVienBLL instance = new NhanVienBLL();
        List<NhanVienDTO> result = instance.findByStatus(status);
        for (NhanVienDTO nhanVien : result) {
            assertEquals(nhanVien.getTrangThai(), status);
        }
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByFalseStatus() {
        System.out.println("findByStatus");
        boolean status = false;
        INhanVienBLL instance = new NhanVienBLL();
        List<NhanVienDTO> result = instance.findByStatus(status);
        for (NhanVienDTO nhanVien : result) {
            assertEquals(nhanVien.getTrangThai(), status);
        }
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByExistingId() {
        System.out.println("findById");
        Long id = 1L;
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findById(id);
        assertEquals(id, result.getId());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByNotExistingId() {
        System.out.println("findById");
        Long id = 100L;
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findById(id);
        assertNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByInvalidId() {
        System.out.println("findById");
        thrown.expect(NumberFormatException.class);
        String textId = "abc";
        Long id = Long.valueOf(textId);
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findById(id);
        assertNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByExistingSdt() {
        System.out.println("findBySdt");
        String sdt = "0114589521";
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findBySdt(sdt);
        assertEquals(sdt, result.getSdt());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByNotExistingSdt() {
        System.out.println("findBySdt");
        String sdt = "0114589529";
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findBySdt(sdt);
        assertNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByInvalidSdt() {
        System.out.println("findBySdt");
        String sdt = "1114589521";
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO result = instance.findBySdt(sdt);
        assertNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveValidData() throws IOException {
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
        
        INhanVienBLL instance = new NhanVienBLL();
        Long savedId = instance.save(nhanVien);
        
        assertNotNull(savedId);
        
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
    public void testSaveNullData() throws IOException {
        System.out.println("save");
        
        NhanVienDTO nhanVien = new NhanVienDTO();
        
        INhanVienBLL instance = new NhanVienBLL();
        Long savedId = instance.save(nhanVien);
        NhanVienDTO result = instance.findById(savedId);
        assertNull(result.getHo());
        assertNull(result.getTen());
        assertNull(result.getGioiTinh());
        assertNull(result.getNgaySinh());
        assertNull(result.getDiaChi());
        assertNull(result.getSdt());
        assertNull(result.getHinhAnh());
        assertNull(result.getIdVaiTro());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveWithExistingRoleId() throws IOException {
        System.out.println("save");
        
        Long idVaiTro = 2L;
        
        NhanVienDTO nhanVien = new NhanVienDTO();
        nhanVien.setIdVaiTro(idVaiTro);
        
        INhanVienBLL instance = new NhanVienBLL();
        Long savedId = instance.save(nhanVien);
        NhanVienDTO result = instance.findById(savedId);
        assertNull(result.getHo());
        assertNull(result.getTen());
        assertNull(result.getGioiTinh());
        assertNull(result.getNgaySinh());
        assertNull(result.getDiaChi());
        assertNull(result.getSdt());
        assertNull(result.getHinhAnh());
        assertEquals(idVaiTro, result.getIdVaiTro());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateValidPhoneNumber() {
        System.out.println("update");
        Long nhanVienId = 1L;
        String sdt = "0969653020";
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO nhanVien = instance.findById(nhanVienId);
        nhanVien.setSdt(sdt);
        instance.update(nhanVien);
        assertEquals(sdt, instance.findById(nhanVienId).getSdt());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateInvalidPhoneNumber() {
        System.out.println("update");
        Long nhanVienId = 1L;
        String sdt = "096965302011";
        INhanVienBLL instance = new NhanVienBLL();
        NhanVienDTO nhanVien = instance.findById(nhanVienId);
        nhanVien.setSdt(sdt);
        instance.update(nhanVien);
        assertEquals(sdt, instance.findById(nhanVienId).getSdt());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        boolean status = false;
        Long nhanVienId = 1L;
        INhanVienBLL instance = new NhanVienBLL();
        instance.updateStatus(status, nhanVienId);
        assertEquals(status, instance.findById(nhanVienId).getTrangThai());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 11L;
        INhanVienBLL instance = new NhanVienBLL();
        instance.delete(id);
        assertNull(instance.findById(id));
//        fail("The test case is a prototype.");
    }
    
}
