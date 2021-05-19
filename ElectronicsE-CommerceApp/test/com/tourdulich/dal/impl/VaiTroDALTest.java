/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.VaiTroDTO;
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
public class VaiTroDALTest {
    
    public VaiTroDALTest() {
    }
    
   @Test
    public void testFindAll() {
        System.out.println("findAll");
        VaiTroDAL instance = new VaiTroDAL();
        List<VaiTroDTO> expResult = null;
        List<VaiTroDTO> result = instance.findAll();
        assertNotNull(result);
    }

    /**
     * Test of findById method, of class VaiTroDAL.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 5L;
        VaiTroDAL instance = new VaiTroDAL();
        VaiTroDTO expResult = null;
        VaiTroDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    /**
     * Test of save method, of class VaiTroDAL.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        String tenVaiTro = "Hướng dẫn viên phụ";
        
        VaiTroDTO vaiTro = new VaiTroDTO();
        vaiTro.setTenVaiTro(tenVaiTro);
        
        VaiTroDAL instance = new VaiTroDAL();
        Long saveId = instance.save(vaiTro);
        VaiTroDTO result = instance.findById(saveId);
        assertEquals(tenVaiTro, result.getTenVaiTro());
    }

    /**
     * Test of update method, of class VaiTroDAL.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        VaiTroDAL instance = new VaiTroDAL();
        
        Long id = 7L;
        String tenVaiTro = "Lái xe phụ";
        
        VaiTroDTO vaiTro = instance.findById(id);
        vaiTro.setTenVaiTro(tenVaiTro);
        instance.update(vaiTro);
        
        assertEquals(tenVaiTro, instance.findById(id).getTenVaiTro());
    }

    /**
     * Test of delete method, of class VaiTroDAL.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 7L;
        VaiTroDAL instance = new VaiTroDAL();
        instance.delete(id);
        VaiTroDTO result = instance.findById(id);
        assertNull(result);
    }
    
}
