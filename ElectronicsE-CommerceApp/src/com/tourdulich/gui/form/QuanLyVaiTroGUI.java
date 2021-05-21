/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.form;

import com.tourdulich.bll.IRoleBLL;
import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.bll.impl.RoleBLL;
import com.tourdulich.bll.impl.VaiTroBLL;
import com.tourdulich.dto.VaiTroDTO;
import com.tourdulich.gui.popup.PopUpVaiTroGUI;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.tourdulich.gui.menu.MyScrollBarUI;
import com.tourdulich.util.RoleTableLoaderUtil;
import com.tourdulich.util.TableSetupUtil;
import com.tourdulich.util.VaiTroTableLoaderUtil;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class QuanLyVaiTroGUI extends javax.swing.JPanel {
    String[] listColumns = {
                            "Id",
                            "Tên Vai Trò",
                            "Ma Vai Tro"};
    private IRoleBLL roleBLL;
    TableRowSorter<TableModel> rowSorter = null;
    PopUpVaiTroGUI popUp = null;
    public QuanLyVaiTroGUI() {
        initComponents();
        
        roleBLL = new RoleBLL();
        
        loadTableData();
        
        headerColor(77,77,77,tblVaiTro);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
    
    public void loadTableData() {
        tblVaiTro.setModel(new RoleTableLoaderUtil().setTable(roleBLL.findAll(), this.listColumns));
        this.rowSorter = TableSetupUtil.setTableFilter(tblVaiTro, txtTimKiem);
        headerColor(14,142,233,tblVaiTro);
    }
    
    public Vector createHeader(Object[] columnNames){
        Vector header = new Vector();
        for(Object colName : columnNames){
            header.add(colName);
        }
        return header;
    }
    
    public void headerColor(int r, int b, int g, JTable table)
    {
        Color color = new Color(r,b,g);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(color);
        headerRenderer.setForeground(color.WHITE);
        
        JTableHeader tab_header = table.getTableHeader();					//Get the table header			
			tab_header.setFont(new Font("Microsoft Yahei", Font.PLAIN, 15));	
			tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//Modify the height of the table header
			table.setFont(new Font("Microsoft Yahei", Font.PLAIN, 13));
        
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }       
         
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
    }
    
    public JButton getButtonThem() {
        return btnThem;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickMenu = new javax.swing.JPopupMenu();
        itemSua = new javax.swing.JMenuItem();
        pnlHead = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblTimKiem = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblVaiTro = new javax.swing.JTable();

        itemSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/img/edit_icon.png"))); // NOI18N
        itemSua.setText("Sửa");
        itemSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuaActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemSua);

        setLayout(new java.awt.BorderLayout());

        pnlHead.setBackground(new java.awt.Color(255, 255, 255));
        pnlHead.setPreferredSize(new java.awt.Dimension(808, 150));

        btnThem.setBackground(new java.awt.Color(77, 77, 77));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(true);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(77, 77, 77)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Quản Lý Vai Trò");

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/img/search_icon.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTimKiem))
                .addGap(32, 32, 32))
        );

        add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        tblVaiTro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVaiTro.setFillsViewportHeight(true);
        tblVaiTro.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblVaiTro.setRowHeight(35);
        tblVaiTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblVaiTroMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblVaiTro);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
        if (this.popUp == null)
        {
            this.popUp = new PopUpVaiTroGUI("POST");
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        this.popUp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUp = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_btnThemMousePressed

    private void itemSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuaActionPerformed
//        int rowindex = tblVaiTro.getSelectedRow();
//        Long id = Long.parseLong(tblVaiTro.getValueAt(rowindex,0).toString());
//        if (this.popUp == null)
//        {
//            this.popUp = new PopUpVaiTroGUI("PUT", roleBLL.findById(id));
//        } else {
//            this.popUp.toFront();
//            this.popUp.center();
//        }
//        this.popUp.addWindowListener(new java.awt.event.WindowAdapter() {
//        @Override
//        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//            popUp = null;
//            loadTableData();
//        }
//    });
    }//GEN-LAST:event_itemSuaActionPerformed

    private void tblVaiTroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVaiTroMouseReleased
        int r = tblVaiTro.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblVaiTro.getRowCount()) {
            tblVaiTro.setRowSelectionInterval(r, r);
        } else {
           tblVaiTro.clearSelection();
        }

        int rowindex = tblVaiTro.getSelectedRow();
       

        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
            
            rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblVaiTroMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblVaiTro;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
