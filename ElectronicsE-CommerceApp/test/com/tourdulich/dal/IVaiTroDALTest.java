/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dal.impl.VaiTroDAL;
import com.tourdulich.dto.VaiTroDTO;
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
 * @author Khoa Nguyen
 */
public class IVaiTroDALTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IVaiTroDAL instance = new VaiTroDAL();
        List<VaiTroDTO> expResult = null;
        List<VaiTroDTO> result = instance.findAll();
        assertNotNull(result);
    }

     @Test
    public void testFindByIdValid() {
        System.out.println("findByIdValid");
        Long id = 5L;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
        
    }

    @Test
    public void testFindByIdInputNull() {
        System.out.println("findByIdInputNull");
        Long id = null;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdNotExist() {
        System.out.println("findByIdNotExist");
        Long id = 1009L;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdInputString() {
        System.out.println("findByIdInputString");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("abc");
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputFloat() {
        System.out.println("findByIdInputFloat");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("1.5");
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputSpecialCharacters() {
        System.out.println("findByIdInputSpecialCharacters");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("@b1");
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
      
    
    @Test
    public void testSaveValid() {
        System.out.println("saveValid");
        String tenVaiTro = "Hướng dẫn viên phụ";
        
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        
        IVaiTroDAL instance = new VaiTroDAL();
        Long savedId = instance.save(vaiTro);
        assertNotNull(instance.findById(savedId));
    }

    @Test
    public void testSaveNullData() {
        System.out.println("saveNullData");
        String tenVaiTro = null;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        Long savedId = instance.save(vaiTro);
        assertNull(instance.findById(savedId));
    }
    
    @Test
    public void testSaveInvalidTenVaiTro() {
        System.out.println("saveInvalidTenVaiTro");
        String tenVaiTro = "@!@#@#!@123";
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        Long savedId = instance.save(vaiTro);
        assertNull(instance.findById(savedId));
    }
    
    @Test
    public void testUpdateTenVaiTro() {
        System.out.println("updateTenVaiTro");
        IVaiTroDAL instance = new VaiTroDAL();
        
        Long id = 7L;
        String tenVaiTro = "Lái xe phụ";
        
        VaiTroDTO vaiTro = instance.findById(id);
        vaiTro.setTenVaiTro(tenVaiTro);
        instance.update(vaiTro);
        
        assertEquals(tenVaiTro, instance.findById(id).getTenVaiTro());
    }

   @Test
    public void testUpdateNullTenVaiTro() {
        System.out.println("updateNullTenVaiTro");
        Long vaiTroId = 10L;
        String tenVaiTro = null;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO vaiTro = instance.findById(vaiTroId);
        String oldTenVaiTro = vaiTro.getTenVaiTro();
        vaiTro.setTenVaiTro(tenVaiTro);
        instance.update(vaiTro);
        assertEquals(oldTenVaiTro, instance.findById(vaiTroId).getTenVaiTro());
    }
    
    @Test
    public void testUpdateNotExistId() {
        System.out.println("updateNotExistId");
        Long vaiTroId = 200L;
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO dichVu = instance.findById(vaiTroId);
        assertNull(dichVu);
    }
    
    @Test
    public void testUpdateInValid() {
        System.out.println("updateInvalid");
        Long vaiTroId = 10L;
        String tenVaiTro = "!@#!#!123";
        IVaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO loaiDuLich = instance.findById(vaiTroId);
        String oldTenVaiTro = loaiDuLich.getTenVaiTro();
        loaiDuLich.setTenVaiTro(tenVaiTro);
        instance.update(loaiDuLich);
        VaiTroDTO result = instance.findById(vaiTroId);
        assertEquals(oldTenVaiTro, result.getTenVaiTro());
    }
    
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 7L;
        IVaiTroDAL instance = new VaiTroDAL();
        instance.delete(id);
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }

    @Test
    public void testDeleteNotExistId() {
        System.out.println("deleteNotExistId");
        Long id = 300L;
        IVaiTroDAL instance = new VaiTroDAL();
        instance.delete(id);
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
}

