/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.dto.DichVuDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class DichVuTableLoaderUtil implements ITableLoaderUtil<DichVuDTO>{

    @Override
    public DefaultTableModel setTable(List<DichVuDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(DichVuDTO DichVu : listItems) {
            row = new Vector();
            row.add(DichVu.getId());
            row.add(DichVu.getTenDichVu());
            row.add(DichVu.getMoTa());
            model.addRow(row);
        }
    
        return model;
    }
}