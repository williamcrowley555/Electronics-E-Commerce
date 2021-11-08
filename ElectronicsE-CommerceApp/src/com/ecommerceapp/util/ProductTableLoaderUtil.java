/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IBrandBLL;
import com.ecommerceapp.bll.ICatalogBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.impl.BrandBLL;
import com.ecommerceapp.bll.impl.CatalogBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.dto.ProductDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class ProductTableLoaderUtil implements ITableLoaderUtil<ProductDTO>{
    private IProductBLL productBLL = new ProductBLL();
    private IBrandBLL brandBLL = new BrandBLL();
    private ICatalogBLL catalogBLL = new CatalogBLL();
    //private ITinhBLL tinhBLL = new TinhBLL();
    
    @Override
    public DefaultTableModel setTable(List<ProductDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(ProductDTO product : listItems) {
            row = new Vector();
            row.add(product.getId());
            row.add(product.getName());
            //row.add(tinhBLL.findById(diaDiem.getIdTinh()).getTenTinh());
            row.add(product.getPrice());
            row.add(brandBLL.findById(product.getBrandId()).getName());
            row.add(catalogBLL.findById(product.getCatalogId()).getName());
            row.add(product.getQuantity());
            row.add(product.getDescription());
            model.addRow(row);
        }
        
        return model;
    }    
}
