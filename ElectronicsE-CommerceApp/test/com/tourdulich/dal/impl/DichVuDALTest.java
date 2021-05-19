/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DichVuDTO;
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
public class DichVuDALTest {
    
    public DichVuDALTest() {
    }
    

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DichVuDAL instance = new DichVuDAL();
        List<DichVuDTO> expResult = null;
        List<DichVuDTO> result = instance.findAll();
        assertNotNull(result);
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 21L;
        DichVuDAL instance = new DichVuDAL();
        DichVuDTO result = instance.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave() {
        System.out.println("save");
        String TenDichVu = "Hồ bơi";
        String MoTa = "Hồ bơi vô cực";
        DichVuDAL instance = new DichVuDAL();
        DichVuDTO dichVu = new DichVuDTO();
        dichVu.setMoTa(MoTa);
        dichVu.setTenDichVu(TenDichVu);
        Long savedId = instance.save(dichVu);
        assertNotNull(instance.findById(savedId));
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Long dichVuId = 2L;
        String moTa = "Hồ bơi thiên nhiên";
        DichVuDAL instance = new DichVuDAL();
        DichVuDTO dichVu = instance.findById(dichVuId);
        dichVu.setMoTa(moTa);
        instance.update(dichVu);
        assertEquals(moTa, instance.findById(dichVuId).getMoTa());
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = 3L;
        DichVuDAL instance = new DichVuDAL();
        instance.delete(id);
        DichVuDTO result = instance.findById(id);
        assertNull(result);
    }
    
}
