/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class RevenueTableLoaderUtil implements ITableLoaderUtil<RevenueDTO>{
    private IInvoiceBLL invoiceBLL = new InvoiceBLL();
    
    @Override
    public DefaultTableModel setTable(List<RevenueDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(RevenueDTO revenue : listItems) {
            row = new Vector();
            row.add(revenue.getProductId());
            row.add(revenue.getProductName());
            row.add(revenue.getProductPrice());
            row.add(revenue.getProductTotalQuantity());
            row.add(revenue.getProductSubTotal());
            model.addRow(row);
        }
        return model;
    }    
}
