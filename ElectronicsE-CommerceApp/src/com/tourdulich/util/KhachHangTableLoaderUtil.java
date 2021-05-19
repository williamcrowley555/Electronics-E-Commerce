/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.bll.impl.VaiTroBLL;
import com.tourdulich.dto.KhachHangDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class KhachHangTableLoaderUtil implements ITableLoaderUtil<KhachHangDTO>{
    @Override
    public DefaultTableModel setTable(List<KhachHangDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(KhachHangDTO khachHang : listItems) {
            row = new Vector();
            row.add(khachHang.getId());
            row.add(khachHang.getHo());
            row.add(khachHang.getTen());
            row.add(khachHang.getGioiTinh() ? "Nam" : "Nữ");
            row.add(khachHang.getCmnd());
            row.add(khachHang.getNgaySinh());
            row.add(khachHang.getDiaChi());
            row.add(khachHang.getSdt());
            
            model.addRow(row);
        }
        
        return model;
    }
    
}
