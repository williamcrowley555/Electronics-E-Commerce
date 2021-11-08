/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.BrandDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class BrandTableLoaderUtil implements ITableLoaderUtil<BrandDTO>{

    @Override
    public DefaultTableModel setTable(List<BrandDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(BrandDTO brand : listItems) {
            row = new Vector();
            row.add(brand.getId());
            row.add(brand.getName());
            model.addRow(row);
        }
    
        return model;
    }
}