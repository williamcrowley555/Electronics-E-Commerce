/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.ILoaiDuLichBLL;
import com.ecommerceapp.bll.ITourBLL;
import com.ecommerceapp.bll.ITinhBLL;
import com.ecommerceapp.bll.impl.LoaiDuLichBLL;
import com.ecommerceapp.bll.impl.TourBLL;
import com.ecommerceapp.bll.impl.TinhBLL;
import com.ecommerceapp.dto.TourDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class TourTableLoaderUtil implements ITableLoaderUtil<TourDTO>{
    private ILoaiDuLichBLL loaiDuLichBLL = new LoaiDuLichBLL() {};
    
    @Override
    public DefaultTableModel setTable(List<TourDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(TourDTO Tour : listItems) {
            row = new Vector();
            row.add(Tour.getId());
            row.add(Tour.getTenTour());
            row.add(loaiDuLichBLL.findById(Tour.getIdLoaiDuLich()).getTenLoaiDuLich());
            row.add(Tour.getDacDiem());
            model.addRow(row);
        }
        
        return model;
    }    
}
