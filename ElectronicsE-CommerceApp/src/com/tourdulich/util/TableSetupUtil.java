/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HP
 */
public class TableSetupUtil {
    public static TableRowSorter<TableModel> setTableFilter(JTable table, JTextField filterText) {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String searchText = filterText.getText();
                if(searchText.trim().length() > 0) {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                } else {
                    rowSorter.setRowFilter(null);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchText = filterText.getText();
                if(searchText.trim().length() > 0) {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                } else {
                    rowSorter.setRowFilter(null);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }
        });
        
        return rowSorter;
    }
}
