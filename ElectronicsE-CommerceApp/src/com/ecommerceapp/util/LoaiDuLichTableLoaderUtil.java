/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.LoaiDuLichDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class LoaiDuLichTableLoaderUtil implements ITableLoaderUtil<LoaiDuLichDTO>{
     @Override
    public DefaultTableModel setTable(List<LoaiDuLichDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(LoaiDuLichDTO LoaiDuLich : listItems) {
            row = new Vector();
            row.add(LoaiDuLich.getId());
            row.add(LoaiDuLich.getTenLoaiDuLich());
            model.addRow(row);
        }
    
        return model;
    }
}
