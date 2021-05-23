/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IBrandBLL;
import com.tourdulich.bll.ICatalogBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IProductBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.impl.BrandBLL;
import com.tourdulich.bll.impl.CatalogBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.ProductBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.InvoiceDetailDTO;
import com.tourdulich.dto.ProductDTO;
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
