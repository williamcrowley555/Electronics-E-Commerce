/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.ISupplierBLL;
import com.ecommerceapp.bll.impl.SupplierBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.dto.SupplierDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khoa Nguyen
 */
public class SupplierTableLoaderUtil implements ITableLoaderUtil<SupplierDTO> {
    private ISupplierBLL supplierBLL = new SupplierBLL();
    //private ITinhBLL tinhBLL = new TinhBLL();
    
    @Override
    public DefaultTableModel setTable(List<SupplierDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(SupplierDTO supplier : listItems) {
            row = new Vector();
            row.add(supplier.getId());
            row.add(supplier.getCompanyName());
            row.add(supplier.getContactName());
            row.add(supplier.getContactJobTitle());
            row.add(supplier.getAddress());
            row.add(supplier.getEmail());
            row.add(supplier.getPhone());
            row.add(supplier.getCountry());
            model.addRow(row);
        }
        
        return model;
    }        
}
