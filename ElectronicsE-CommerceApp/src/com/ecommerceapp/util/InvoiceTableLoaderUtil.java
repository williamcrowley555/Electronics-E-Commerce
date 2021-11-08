/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.dto.InvoiceDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class InvoiceTableLoaderUtil implements ITableLoaderUtil<InvoiceDTO>{
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
            row.add(invoice.getRecipientLastName());
            row.add(invoice.getRecipientFirstName());
            row.add(invoice.getAddress());
            row.add(invoice.getPhone());
           
            row.add(invoice.getTotal());
            row.add(invoice.getOrderDate());
            row.add(invoice.getConfirmationDate());
            row.add(invoice.getPaymentDate());
            row.add(invoice.getShipDate());
            row.add(invoice.getCancellingDate());
            
            int state = invoice.getStatus();
            
            if (state == 0) row.add("Chưa xác nhận");
            else if (state == 1) row.add("Đã xác nhận");
            else if (state == 2) row.add("Đã giao");
            else if (state == 3) row.add("Đã thanh toán");
            else if (state == 4) row.add("Đã hủy");
            
            model.addRow(row);
        }
        
        return model;
    }    
}
