/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import com.ecommerceapp.dto.VaiTroDTO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public interface ITableLoaderUtil<T> {
     DefaultTableModel setTable(List<T> listItems, String[] listColumns);
}
