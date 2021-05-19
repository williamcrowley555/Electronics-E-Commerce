/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dal.impl.DiaDiemDAL;
import com.tourdulich.dto.DiaDiemDTO;
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
public class IDiaDiemDALTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testFindAll() {
            System.out.println("findAll");
            IDiaDiemDAL instance = new DiaDiemDAL();
            List<DiaDiemDTO> expResult = null;
            List<DiaDiemDTO> result = instance.findAll();
            assertNotNull(result);
        }

    @Test
    public void testFindByIdValid() {
        System.out.println("findByIdValid");
        Long id = 521L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertEquals(id, result.getId());
        
    }
    
    @Test
    public void testFindByIdInputNull() {
        System.out.println("findByIdInputNull");
        Long id = null;
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdNotExist() {
        System.out.println("findByIdNotExist");
        Long id = 999L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdInputString() {
        System.out.println("findByIdInputString");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("abc");
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputFloat() {
        System.out.println("findByIdInputFloat");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("1.5");
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputSpecialCharacters() {
        System.out.println("findByIdInputSpecialCharacters");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("@b1");
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdTinhValid() {
        System.out.println("findByIdTinh");
        Long idTinh = 52L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        List<DiaDiemDTO> result = instance.findByIdTinh(idTinh);
        
        for (DiaDiemDTO diaDiem : result) {
            assertEquals(idTinh, diaDiem.getIdTinh());
        }       
    }
 
    @Test
    public void testFindByIdTinhNotExist() {
        System.out.println("findByIdTinhNotExist");
        Long idTinh = 888L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        List<DiaDiemDTO> result = instance.findByIdTinh(idTinh);
        
        for (DiaDiemDTO diaDiem : result) {
            assertNull(diaDiem);
        }       
    }
    
    @Test
    public void testSaveValid() {
        System.out.println("saveValid");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = "Sapsa";
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNotNull(instance.findById(savedId));       
    }
    
    @Test
    public void testSaveNull() {
        System.out.println("saveNull");
        String tenDiaDiem = null;
        String diaChi = null;
        String gioiThieu = null;
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }

    @Test
    public void testSaveNullTenDiaDiem() {
        System.out.println("saveNullTenDiaDiem");
        String tenDiaDiem = null;
        String diaChi = "Sapa";
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }
    
    @Test
    public void testSaveNullDiaChi() {
        System.out.println("saveValid");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = null;
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }
    
    @Test
    public void testSaveNullGioiThieu() {
        System.out.println("saveValid");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = "Sapa";
        String gioiThieu = null;
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }    
    
    @Test
    public void testSaveInvalidTenDiaDiem() {
        System.out.println("saveValid");
        String tenDiaDiem = "@!@#@#!@123";
        String diaChi = "Sapa";
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }
    
    @Test
    public void testSaveInvalidDiaChi() {
        System.out.println("saveValid");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = "@!@#@#!@123";
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }
    
    @Test
    public void testSaveInvalidGioiThieu() {
        System.out.println("saveValid");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = "Sapa";
        String gioiThieu = "@!@#@#!@123";
        Long idTinh = 49L;
                
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
        assertNull(instance.findById(savedId));       
    }
    
    @Test
    public void testUpdate() {
        System.out.println("updateTenDiaDiem");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String tenDiaDiem = "Nhà cổ trăm cột";
        String diaChi = "Long Hựu Đông, Cần Đước, Long An";
        String gioiThieu = "Di tích lịch sử cấp quốc gia";
        Long idTinh = 41L;
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        instance.update(diaDiem);
        
        assertEquals(tenDiaDiem, instance.findById(diaDiemId).getTenDiaDiem());
    }
    
    @Test
    public void testUpdateTenDiaDiem() {
        System.out.println("updateTenDiaDiem");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String tenDiaDiem = "Nhà cổ trăm cột";
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setTenDiaDiem(tenDiaDiem);
        instance.update(diaDiem);
        
        assertEquals(tenDiaDiem, instance.findById(diaDiemId).getTenDiaDiem());
    }
    
    @Test
    public void testUpdateDiaChi() {
        System.out.println("updateDiaChi");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String diaChi = "Long Hựu Đông, Cần Đước, Long An";
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setDiaChi(diaChi);
        instance.update(diaDiem);
        
        assertEquals(diaChi, instance.findById(diaDiemId).getDiaChi());
    }
    
    @Test
    public void testUpdateGioiThieu() {
        System.out.println("updateGioiThieu");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String gioiThieu = "Di tích lịch sử cấp quốc gia";
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setGioiThieu(gioiThieu);
        instance.update(diaDiem);
        
        assertEquals(gioiThieu, instance.findById(diaDiemId).getGioiThieu());
    }

    @Test
    public void testUpdateIdTinh() {
        System.out.println("updateGioiThieu");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        Long idTinh = 41L;
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setIdTinh(idTinh);
        instance.update(diaDiem);
        
        assertEquals(idTinh, instance.findById(diaDiemId).getIdTinh());
    }
    
    @Test
    public void testUpdateNullTenDiaDiem() {
        System.out.println("updateNullTenDiaDiem");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String tenDiaDiem = null;
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldTenDiaDiem = diaDiem.getTenDiaDiem();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        instance.update(diaDiem);
        assertEquals(oldTenDiaDiem, instance.findById(diaDiemId).getTenDiaDiem());
    }
    
    @Test
    public void testUpdateNullDiaChi() {
        System.out.println("updateNullDiaChi");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String diaChi = null;
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldDiaChi = diaDiem.getDiaChi();
        diaDiem.setDiaChi(diaChi);
        instance.update(diaDiem);
        assertEquals(oldDiaChi, instance.findById(diaDiemId).getDiaChi());
    }
    
    @Test
    public void testUpdateNullGioiThieu() {
        System.out.println("updateNullGioiThieu");     
        IDiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String gioiThieu = null;
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldGioiThieu = diaDiem.getGioiThieu();
        diaDiem.setGioiThieu(gioiThieu);
        instance.update(diaDiem);
        assertEquals(oldGioiThieu, instance.findById(diaDiemId).getGioiThieu());
    }   
    
    @Test
    public void testUpdateInvalidTenDiaDiem() {
        System.out.println("updateInvalidTenDiaDiem");
        Long diaDiemId = 491L;
        String tenDiaDiem = "123@!$!@$@$";
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldTenDiaDiem = diaDiem.getTenDiaDiem();
        diaDiem.setTenDiaDiem(tenDiaDiem);
        instance.update(diaDiem);
        assertEquals(oldTenDiaDiem, instance.findById(diaDiemId).getTenDiaDiem());
    }
    
    @Test
    public void testUpdateInvalidDiaChi() {
        System.out.println("updateInvalidDiaChi");
        Long diaDiemId = 491L;
        String diaChi = "123@!$!@$@$";
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldDiaChi = diaDiem.getDiaChi();
        diaDiem.setDiaChi(diaChi);
        instance.update(diaDiem);
        assertEquals(oldDiaChi, instance.findById(diaDiemId).getDiaChi());
    }
    
    @Test
    public void testUpdateInvalidGioiThieu() {
        System.out.println("updateInvalidGioiThieu");
        Long diaDiemId = 491L;
        String gioiThieu = "123@!$!@$@$";
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        String oldGioiThieu = diaDiem.getGioiThieu();
        diaDiem.setGioiThieu(gioiThieu);
        instance.update(diaDiem);
        assertEquals(oldGioiThieu, instance.findById(diaDiemId).getGioiThieu());
    }
    
    @Test
    public void testUpdateNotExistId() {
        System.out.println("updateNotExist");
        Long diaDiemId = 200L;
        String diaChi = "Long Hựu Đông, Cần Đước, Long An";
        IDiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO dichVu = instance.findById(diaDiemId);
        assertNull(dichVu);
    }
    
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long diaDiemId = 6L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        instance.delete(diaDiemId);
        DiaDiemDTO result = instance.findById(diaDiemId);
        assertNull(result);
    }
    
    @Test
    public void testDeleteNotExistId() {
        System.out.println("deleteNotExistId");
        Long id = 300L;
        IDiaDiemDAL instance = new DiaDiemDAL();
        instance.delete(id);
        DiaDiemDTO result = instance.findById(id);
        assertNull(result);
    }
}