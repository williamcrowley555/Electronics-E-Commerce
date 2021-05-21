/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import com.tourdulich.bll.IRoleBLL;
import com.tourdulich.bll.impl.RoleBLL;
import com.tourdulich.dto.RoleDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khoa Nguyen
 */
public class RoleTableLoaderUtil  implements ITableLoaderUtil<RoleDTO>{
    private IRoleBLL roleBLL = new RoleBLL();
    //private ITinhBLL tinhBLL = new TinhBLL();
    
    @Override
    public DefaultTableModel setTable(List<RoleDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(RoleDTO role : listItems) {
            row = new Vector();
            row.add(role.getId());
            row.add(role.getName());
            row.add(role.getNormalizedName());
            model.addRow(row);
        }
        
            
        return model;
    }      
}
