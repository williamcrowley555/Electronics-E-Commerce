/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

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
public class DiaDiemBLLTest {
    
    public DiaDiemBLLTest() {
    }
    

    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        
        DiaDiemBLL instance = new DiaDiemBLL();
        List<DiaDiemDTO> expResult = null;
        List<DiaDiemDTO> result = instance.findAll();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class DiaDiemBLL.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = 521L;
        DiaDiemBLL instance = new DiaDiemBLL();
        DiaDiemDTO result = instance.findById(id);
        
        assertEquals(id, result.getId());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdTinh method, of class DiaDiemBLL.
     */
    @Test
    public void testFindByIdTinh() {
        System.out.println("findByIdTinh");
        Long idTinh = 52L;
        DiaDiemBLL instance = new DiaDiemBLL();
        List<DiaDiemDTO> result = instance.findByIdTinh(idTinh);
        
        for (DiaDiemDTO diaDiem : result) {
            assertEquals(idTinh, diaDiem.getIdTinh());
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class DiaDiemBLL.
     */
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
        
        DiaDiemBLL instance = new DiaDiemBLL();
        Long savedId = instance.save(diaDiem);
        DiaDiemDTO result = instance.findById(savedId);
        
        assertEquals(tenDiaDiem, result.getTenDiaDiem());
        assertEquals(diaChi, result.getDiaChi());
        assertEquals(gioiThieu, result.getGioiThieu());
        assertEquals(idTinh, result.getIdTinh());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DiaDiemBLL.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");     
        DiaDiemBLL instance = new DiaDiemBLL();
        Long diaDiemId = 491L;
        String gioiThieu = "Di tích lịch sử cấp quốc gia";
        DiaDiemDTO diaDiem = instance.findById(diaDiemId);
        diaDiem.setGioiThieu(gioiThieu);
        instance.update(diaDiem);
        
        assertEquals(gioiThieu, instance.findById(diaDiemId).getGioiThieu());
    }

    /**
     * Test of delete method, of class DiaDiemBLL.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long diaDiemId = 6L;
        DiaDiemBLL instance = new DiaDiemBLL();
        instance.delete(diaDiemId);
        DiaDiemDTO result = instance.findById(diaDiemId);
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
