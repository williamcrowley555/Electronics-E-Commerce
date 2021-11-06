/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IBrandBLL;
import com.ecommerceapp.bll.ICatalogBLL;
import com.ecommerceapp.bll.IDiaDiemBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.ITinhBLL;
import com.ecommerceapp.bll.impl.BrandBLL;
import com.ecommerceapp.bll.impl.CatalogBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.dto.InvoiceDetailDTO;
import com.ecommerceapp.dto.ProductDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class InvoiceDetailTableLoaderUtil implements ITableLoaderUtil<InvoiceDetailDTO>{
    private IProductBLL productBLL = new ProductBLL();
  
    //private ITinhBLL tinhBLL = new TinhBLL();
    
    @Override
    public DefaultTableModel setTable(List<InvoiceDetailDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(InvoiceDetailDTO invoice : listItems) {
            row = new Vector();
            row.add(invoice.getProduct_id());
            row.add(productBLL.findById(invoice.getProduct_id()).getName());
            row.add(invoice.getQuantity());
            row.add(invoice.getSubTotal());
           
            model.addRow(row);
        }
        
        return model;
    }    
}
