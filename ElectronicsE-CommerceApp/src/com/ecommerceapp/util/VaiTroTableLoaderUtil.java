/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.VaiTroDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class VaiTroTableLoaderUtil implements ITableLoaderUtil<VaiTroDTO>{

    @Override
    public DefaultTableModel setTable(List<VaiTroDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(VaiTroDTO vaiTro : listItems) {
            row = new Vector();
            row.add(vaiTro.getId());
            row.add(vaiTro.getTenVaiTro());
            model.addRow(row);
        }
    
        return model;
    }
}