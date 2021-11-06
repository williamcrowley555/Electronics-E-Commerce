/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IVaiTroBLL;
import com.ecommerceapp.bll.impl.VaiTroBLL;
import com.ecommerceapp.dto.GiaTourDTO;
import com.ecommerceapp.dto.KhachHangDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class GiaTourTableLoaderUtil implements ITableLoaderUtil<GiaTourDTO>{
    @Override
    public DefaultTableModel setTable(List<GiaTourDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(GiaTourDTO giaTour : listItems) {
            row = new Vector();
            row.add(giaTour.getId());
            row.add(giaTour.getIdTour());
            row.add(giaTour.getNgayBatDau());
            row.add(giaTour.getNgayKetThuc());
            row.add(giaTour.getGiaTien());
            model.addRow(row);
        }
        
        return model;
    }
    
}
