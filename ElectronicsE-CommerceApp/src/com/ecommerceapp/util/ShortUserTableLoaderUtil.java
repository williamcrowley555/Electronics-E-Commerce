/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.dto.UserDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khoa Nguyen
 */
public class ShortUserTableLoaderUtil implements ITableLoaderUtil<UserDTO> {
    private IUserBLL userBLL = new UserBLL();
    
    @Override
    public DefaultTableModel setTable(List<UserDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(UserDTO user : listItems) {
            row = new Vector();
            row.add(user.getId());
            row.add(user.getFirstName());
            row.add(user.getEmail());
            row.add(user.getPhone());
            model.addRow(row);
        }
        
        return model;
    }        
}
