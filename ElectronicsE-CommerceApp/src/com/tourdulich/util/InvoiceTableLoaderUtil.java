/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IBrandBLL;
import com.tourdulich.bll.ICatalogBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IInvoiceBLL;
import com.tourdulich.bll.IProductBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.IUserBLL;
import com.tourdulich.bll.impl.BrandBLL;
import com.tourdulich.bll.impl.CatalogBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.InvoiceBLL;
import com.tourdulich.bll.impl.ProductBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.bll.impl.UserBLL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.InvoiceDTO;
import com.tourdulich.dto.ProductDTO;
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
            row.add(invoice.getNote());
            row.add(invoice.getTotal());
            row.add(invoice.getStatus());
            
            model.addRow(row);
        }
        
        return model;
    }    
}
