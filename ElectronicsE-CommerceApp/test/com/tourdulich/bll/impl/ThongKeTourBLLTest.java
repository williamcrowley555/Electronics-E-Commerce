/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IThongKeTourBLL;
import com.tourdulich.dto.ThongKeTourDTO;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class ThongKeTourBLLTest {
    public ThongKeTourBLLTest(){}
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IThongKeTourBLL instance = new ThongKeTourBLL();
        List<ThongKeTourDTO> result = instance.findAll();
        assertNotNull(result);
    }
}
