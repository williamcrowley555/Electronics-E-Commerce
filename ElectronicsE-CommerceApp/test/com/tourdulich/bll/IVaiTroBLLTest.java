/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.bll.impl.VaiTroBLL;
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
public class IVaiTroBLLTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IVaiTroBLL instance = new VaiTroBLL();
        List<VaiTroDTO> expResult = null;
        List<VaiTroDTO> result = instance.findAll();
        assertNotNull(result);
    }

     @Test
    public void testFindByIdValid() {
        System.out.println("findByIdValid");
        Long id = 5L;
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
        
    }

    @Test
    public void testFindByIdInputNull() {
        System.out.println("findByIdInputNull");
        Long id = null;
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdNotExist() {
        System.out.println("findByIdNotExist");
        Long id = 1009L;
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
    @Test
    public void testFindByIdInputString() {
        System.out.println("findByIdInputString");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("abc");
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputFloat() {
        System.out.println("findByIdInputFloat");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("1.5");
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByIdInputSpecialCharacters() {
        System.out.println("findByIdInputSpecialCharacters");
        thrown.expect(NumberFormatException.class);
        Long id = Long.parseLong("@b1");
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }
      
    
    @Test
    public void testSaveValid() {
        System.out.println("saveValid");
        String tenVaiTro = "Hướng dẫn viên phụ";
        
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        
        IVaiTroBLL instance = new VaiTroBLL();
        Long savedId = instance.save(vaiTro);
        assertNotNull(instance.findById(savedId));
    }

    @Test
    public void testSaveNullData() {
        System.out.println("saveNullData");
        String tenVaiTro = null;
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        Long savedId = instance.save(vaiTro);
        assertNull(instance.findById(savedId));
    }
    
    @Test
    public void testSaveInvalidTenVaiTro() {
        System.out.println("saveInvalidTenVaiTro");
        String tenVaiTro = "@!@#@#!@123";
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        Long savedId = instance.save(vaiTro);
        assertNull(instance.findById(savedId));
    }
    
    @Test
    public void testUpdate() {
        System.out.println("update");
        IVaiTroBLL instance = new VaiTroBLL();
        
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
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO vaiTro = instance.findById(vaiTroId);
        String oldTenVaiTro = vaiTro.getTenVaiTro();
        vaiTro.setTenVaiTro(tenVaiTro);
        instance.update(vaiTro);
        assertEquals(oldTenVaiTro, instance.findById(vaiTroId).getTenVaiTro());
    }
    
    @Test
    public void testUpdateInValid() {
        System.out.println("updateInvalid");
        Long vaiTroId = 10L;
        String tenVaiTro = "!@#!#!123";
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO loaiDuLich = instance.findById(vaiTroId);
        String oldTenVaiTro = loaiDuLich.getTenVaiTro();
        loaiDuLich.setTenVaiTro(tenVaiTro);
        instance.update(loaiDuLich);
        VaiTroDTO result = instance.findById(vaiTroId);
        assertEquals(oldTenVaiTro, result.getTenVaiTro());
    }
    
    @Test
    public void testUpdateNotExistId() {
        System.out.println("updateNotExistId");
        Long vaiTroId = 200L;
        String tenVaiTro = "MC";
        IVaiTroBLL instance = new VaiTroBLL();
        VaiTroDTO dichVu = instance.findById(vaiTroId);
        assertNull(dichVu);
    }
    
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 7L;
        IVaiTroBLL instance = new VaiTroBLL();
        instance.delete(id);
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }

    @Test
    public void testDeleteNotExistId() {
        System.out.println("deleteNotExistId");
        Long id = 300L;
        IVaiTroBLL instance = new VaiTroBLL();
        instance.delete(id);
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
}
