/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.ITourBLL;
import com.tourdulich.bll.impl.TourBLL;
import com.tourdulich.dto.DoanDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class DoanTableLoaderUtil implements ITableLoaderUtil<DoanDTO> {
    private ITourBLL vaiTroBLL = new TourBLL();
    @Override
    public DefaultTableModel setTable(List<DoanDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(DoanDTO nhanVien : listItems) {
            row = new Vector();
            row.add(nhanVien.getId());
            row.add(nhanVien.getTenDoan());
            row.add(nhanVien.getNgayKhoiHanh());
            row.add(nhanVien.getNgayKetThuc());
            row.add(nhanVien.getIdTour());
            row.add(nhanVien.getSoLuong());
            row.add(nhanVien.getGiaTien());
            model.addRow(row);
        }
        
        return model;
    }    
}
