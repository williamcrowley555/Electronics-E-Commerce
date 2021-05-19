/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DiaDiemDTO;
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
public class DiaDiemDALTest {
    
    public DiaDiemDALTest() {
    }
    
  

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DiaDiemDAL instance = new DiaDiemDAL();
        List<DiaDiemDTO> expResult = null;
        List<DiaDiemDTO> result = instance.findAll();
        assertNotNull(result);
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 521L;
        DiaDiemDAL instance = new DiaDiemDAL();
        DiaDiemDTO result = instance.findById(id);
        
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByIdTinh() {
        System.out.println("findByIdTinh");
        Long idTinh = 52L;
        DiaDiemDAL instance = new DiaDiemDAL();
        List<DiaDiemDTO> result = instance.findByIdTinh(idTinh);
        
        for (DiaDiemDTO diaDiem : result) {
            assertEquals(idTinh, diaDiem.getIdTinh());
        }
    }

    @Test
    public void testSave() {
        System.out.println("save");
        String tenDiaDiem = "Nhà hàng Sang Mèo";
        String diaChi = "Sapa";
        String gioiThieu = "Không có mèo";
        Long idTinh = 49L;
        
        DiaDiemDTO diaDiem = new DiaDiemDTO();
        
        diaDiem.setTenDiaDiem(tenDiaDiem);
        diaDiem.setDiaChi(diaChi);
        diaDiem.setGioiThieu(gioiThieu);
        diaDiem.setIdTinh(idTinh);
        
        DiaDiemDAL instance = new DiaDiemDAL();
        Long savedId = instance.save(diaDiem);
               
        DiaDiemDTO result = instance.findById(savedId);
        assertEquals(tenDiaDiem, result.getTenDiaDiem());
        assertEquals(diaChi, result.getDiaChi());
        assertEquals(gioiThieu, result.getGioiThieu());
        assertEquals(idTinh, result.getIdTinh());
    }

    @Test
    public void testUpdate() {
        System.out.println("update");     
        DiaDiemDAL instance = new DiaDiemDAL();
        Long diaDiemId = 491L;
        String gioiThieu = "Di tích lịch sử cấp quốc gia";
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setGioiThieu(gioiThieu);
        instance.update(diaDiem);
        
        assertEquals(gioiThieu, instance.findById(diaDiemId).getGioiThieu());
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long diaDiemId = 6L;
        DiaDiemDAL instance = new DiaDiemDAL();
        instance.delete(diaDiemId);
        DiaDiemDTO result = instance.findById(diaDiemId);
        assertNull(result);
    }
}
