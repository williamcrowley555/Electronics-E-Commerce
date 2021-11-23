/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class ProcessUnprocessTableLoaderUtil implements ITableLoaderUtil<InvoiceDTO>{
    private IInvoiceBLL invoiceBLL = new InvoiceBLL();
    
    @Override
    public DefaultTableModel setTable(List<InvoiceDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(InvoiceDTO invoice : listItems) {
            row = new Vector();
            row.add(invoice.getId());
            row.add(invoice.getRecipientLastName() + " " + invoice.getRecipientFirstName());
            row.add(invoice.getPhone());
            row.add(invoice.getOrderDate());
            model.addRow(row);
        }
        return model;
    }    
}
