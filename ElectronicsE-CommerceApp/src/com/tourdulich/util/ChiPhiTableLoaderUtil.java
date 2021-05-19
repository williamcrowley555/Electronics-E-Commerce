/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IChiPhiDoanBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IDichVuBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.impl.ChiPhiDoanBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.DichVuBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.dto.ChiPhiDoanDTO;
import com.tourdulich.dto.DiaDiemDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class ChiPhiTableLoaderUtil implements ITableLoaderUtil<ChiPhiDoanDTO>{
    private IChiPhiDoanBLL chiPhiBLL = new ChiPhiDoanBLL();
    private IDichVuBLL dichVuBLL = new DichVuBLL();
    
    @Override
    public DefaultTableModel setTable(List<ChiPhiDoanDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(ChiPhiDoanDTO chiPhi : listItems) {
            row = new Vector();
            row.add(chiPhi.getId());
            row.add(chiPhi.getIdDoan());
            row.add(dichVuBLL.findById(chiPhi.getIdDichVu()).getTenDichVu());
            row.add(chiPhi.getHoaDon());
            row.add(chiPhi.getNgayHoaDon());
            row.add(chiPhi.getChiPhi());
            model.addRow(row);
        }
        
        return model;
    }    
}
