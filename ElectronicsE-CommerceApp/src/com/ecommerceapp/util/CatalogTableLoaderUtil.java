/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.DichVuDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class CatalogTableLoaderUtil implements ITableLoaderUtil<CatalogDTO>{

    @Override
    public DefaultTableModel setTable(List<CatalogDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(CatalogDTO catalog : listItems) {
            row = new Vector();
            row.add(catalog.getId());
            row.add(catalog.getName());
            model.addRow(row);
        }
    
        return model;
    }
}