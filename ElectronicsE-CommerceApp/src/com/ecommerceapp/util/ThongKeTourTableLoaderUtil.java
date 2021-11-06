/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.ThongKeTourDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class ThongKeTourTableLoaderUtil implements ITableLoaderUtil<ThongKeTourDTO>{
@Override
    public DefaultTableModel setTable(List<ThongKeTourDTO> listItems, String[] listColumns) {
        
        Vector header = new Vector();
        for(Object colName : listColumns)
            header.add(colName);
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
       
        Vector row = null;
        for(ThongKeTourDTO ThongKeTour : listItems) {
            row = new Vector();
            row.add(ThongKeTour.getTourId());
            row.add(ThongKeTour.getTenTour());
            row.add(ThongKeTour.getTongSoDoan());
            row.add(ThongKeTour.getTongDoanhThu());
            row.add(ThongKeTour.getTongChiPhi());
            row.add(ThongKeTour.getLai());
            model.addRow(row);
        }
    
        return model;
    }    
}
