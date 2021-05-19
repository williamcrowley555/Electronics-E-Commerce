/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IThongKeDoanBLL;
import com.tourdulich.bll.ITourBLL;
import com.tourdulich.bll.impl.TourBLL;
import com.tourdulich.dto.ThongKeDoanDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class ThongKeDoanTableLoaderUtil implements ITableLoaderUtil<ThongKeDoanDTO> {
    
    @Override
    public DefaultTableModel setTable(List<ThongKeDoanDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(ThongKeDoanDTO thongKeDoan : listItems) {
            row = new Vector();
            row.add(thongKeDoan.getId());
            row.add(thongKeDoan.getTenDoan());
            row.add(thongKeDoan.getSoKhach());
            row.add(thongKeDoan.getGiaTien());
            row.add(thongKeDoan.getDoanhThu());
            row.add(thongKeDoan.getTongChiPhi());
            row.add(thongKeDoan.getLai());

            model.addRow(row);
        }
        
        return model;
    }    
}
