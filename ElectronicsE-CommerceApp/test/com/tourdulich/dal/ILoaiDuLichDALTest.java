/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dal.impl.LoaiDuLichDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
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
public class ILoaiDuLichDALTest {
    
    public ILoaiDuLichDALTest() {
    }
    
   @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        List<LoaiDuLichDTO> expResult = null;
        List<LoaiDuLichDTO> result = instance.findAll();
        assertNotNull(result);
    }

    
    @Test
    public void testFindByIdValid() {
        System.out.println("findByIdValid");
        Long id = 1L;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertEquals(id, result.getId());
        
    }
    
    @Test
    public void testFindByIdInputNull() {
        System.out.println("findByIdInputNull");
        Long id = null;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdNotExist() {
        System.out.println("findByIdNotExist");
        Long id = 100L;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdInputString() {
        System.out.println("findByIdInputString");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("abc");
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputFloat() {
        System.out.println("findByIdInputFloat");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("1.5");
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputSpecialCharacters() {
        System.out.println("findByIdInputSpecialCharacters");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("@b1");
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSaveValid() {
        System.out.println("saveValid");
        String tenLoaiDuLich = "Du lịch trong nước";
        LoaiDuLichDTO loaiDuLich = new LoaiDuLichDTO();
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        Long saveId = instance.save(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(saveId);   
        assertEquals(saveId, result.getId());
    }
    
    @Test
    public void testSaveInValid() {
        System.out.println("saveInValid");
        String tenLoaiDuLich = "!@#!123";
        LoaiDuLichDTO loaiDuLich = new LoaiDuLichDTO();
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        Long saveId = instance.save(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(saveId);   
        assertNull(result);
    }
    
    @Test
    public void testSaveNull() {
        System.out.println("saveNull");
        String tenLoaiDuLich = null;
        LoaiDuLichDTO loaiDuLich = new LoaiDuLichDTO();
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        Long saveId = instance.save(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(saveId);   
        assertNull(result);
    }
    
    

    @Test
    public void testUpdateValid() {
        System.out.println("update");
        Long loaiDuLichId = 13L;
        String tenLoaiDuLich = "Du lịch ngoài nước";
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO loaiDuLich = instance.findById(loaiDuLichId);
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        instance.update(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(loaiDuLichId);
        assertEquals(tenLoaiDuLich, result.getTenLoaiDuLich());
    }
    
    @Test
    public void testUpdateInValid() {
        System.out.println("update");
        Long loaiDuLichId = 13L;
        String tenLoaiDuLich = "!@#!#!123";
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO loaiDuLich = instance.findById(loaiDuLichId);
        String oldTenLoaiDuLich = loaiDuLich.getTenLoaiDuLich();
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        instance.update(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(loaiDuLichId);
        assertEquals(oldTenLoaiDuLich, result.getTenLoaiDuLich());
    }
    
    @Test
    public void testUpdateNull() {
        System.out.println("update");
        Long loaiDuLichId = 11L;
        String tenLoaiDuLich = null;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO loaiDuLich = instance.findById(loaiDuLichId);
        String oldTenLoaiDuLich = loaiDuLich.getTenLoaiDuLich();
        loaiDuLich.setTenLoaiDuLich(tenLoaiDuLich);
        instance.update(loaiDuLich);
        LoaiDuLichDTO result = instance.findById(loaiDuLichId);
        assertEquals(oldTenLoaiDuLich, result.getTenLoaiDuLich());
    }
    
    @Test
    public void testUpdateNotExistId() {
        System.out.println("update");
        Long loaiDuLichId = 130L;
        String tenLoaiDuLich = "Du lịch ngoài nước";
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        LoaiDuLichDTO loaiDuLich = instance.findById(loaiDuLichId);
        assertNull(loaiDuLich);
    }
    
    


    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 12L;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        instance.delete(id);
        LoaiDuLichDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testDeleteNotExistId(){
        System.out.println("delete");
        Long id = 800L;
        ILoaiDuLichDAL instance = new LoaiDuLichDAL();
        instance.delete(id);
        LoaiDuLichDTO result = instance.findById(id);
        assertNull(result);
    }

    
    
}
