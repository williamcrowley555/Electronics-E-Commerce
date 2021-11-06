/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.form;

import com.ecommerceapp.bll.IDiaDiemBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.ITinhBLL;
import com.ecommerceapp.bll.impl.DiaDiemBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.bll.impl.TinhBLL;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.gui.popup.PopUpImportGUI;
import com.ecommerceapp.gui.popup.PopUpProductGUI;
import com.ecommerceapp.gui.popup.PopUpKhachHangGUI;
import com.ecommerceapp.util.DiaDiemTableLoaderUtil;
import com.ecommerceapp.util.ProductTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import java.awt.Component;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class ProductGUI extends javax.swing.JPanel {

        String[] columnNames = {
                            "Id",
                            "Tên sản phẩm",
                            "Giá",
                            "Nhãn hiệu",
                            "Loại",
                            "SL",
                            "Mô tả"                 
        };
                          
    /**
     * Creates new form Panel1
     */
    private IProductBLL productBLL;
    
    private PopUpProductGUI popUp = null;
    private PopUpImportGUI popUpImport = null;
    TableRowSorter<TableModel> rowSorter = null;
    
    public ProductGUI() {
        initComponents();
        productBLL = new ProductBLL();
        
        loadTableData();
        
       /* Vector header = createHeader(columnNames);
        DefaultTableModel model = (DefaultTableModel) tblDiaDiem.getModel();
        model = new DefaultTableModel(header, 0);
       
        Vector row = new Vector();
        row.add("1");
        row.add("Vịnh Hạ Long");
        row.add("4");
        row.add("Quảng Ninh, Việt Nam");
        row.add("1 trong 7 kì quan thế giới");
        row.add("");
    
        
        model.addRow(row);
        tblDiaDiem.setModel(model);*/
        headerColor(77,77,77,tblProduct);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void loadTableData() {
        //tblDiaDiem.setModel(new DiaDiemTableLoaderUtil().setTable(diaDiemBLL.findAll(), this.columnNames)) ;
        tblProduct.setModel(new ProductTableLoaderUtil().setTable(productBLL.findAll(), this.columnNames)) ;
        this.rowSorter = TableSetupUtil.setTableFilter(tblProduct, txtTimKiem);
         headerColor(77,77,77,tblProduct);
         resizeColumnWidth(tblProduct);
    }
    
    public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 70; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width +1 , width);
        }
        if(width > 200)
            width=200;
        columnModel.getColumn(column).setPreferredWidth(width);
        }
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickMenu = new javax.swing.JPopupMenu();
        itemSua = new javax.swing.JMenuItem();
        itemNhap = new javax.swing.JMenuItem();
        pnlHead = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblTimKiem = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();

        itemSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/edit_icon.png"))); // NOI18N
        itemSua.setText("Sửa");
        itemSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuaActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemSua);

        itemNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/edit_icon.png"))); // NOI18N
        itemNhap.setText("Nhập hàng");
        itemNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNhapActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemNhap);

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
        lblTitle.setText("Quản Lý Sản Phẩm");

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/search_icon.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        scroll.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(77, 77, 77)));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduct.setFillsViewportHeight(true);
        tblProduct.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProduct.setRowHeight(35);
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblProduct);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addComponent(scroll)
                .addContainerGap())
        );

        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
          if (this.popUp == null) {
            this.popUp = new PopUpProductGUI("POST");
            
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        popUp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUp = null;
           loadTableData();
            
        }
    });
    }//GEN-LAST:event_btnThemMousePressed

    private void itemSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuaActionPerformed
        int rowindex = tblProduct.getSelectedRow();
        Long id = Long.parseLong(tblProduct.getValueAt(rowindex,0).toString());
        if (this.popUp == null) {
            try {
                popUp = new PopUpProductGUI("PUT", productBLL.findById(id));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ProductGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        popUp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUp = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_itemSuaActionPerformed

    private void tblProductMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseReleased
        // TODO add your handling code here:
        int r = tblProduct.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblProduct.getRowCount()) {
            tblProduct.setRowSelectionInterval(r, r);
        } else {
           tblProduct.clearSelection();
        }
        int rowindex = tblProduct.getSelectedRow();
        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
            
            rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblProductMouseReleased

    private void itemNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNhapActionPerformed
        // TODO add your handling code here:
        int rowindex = tblProduct.getSelectedRow();
        Long id = Long.parseLong(tblProduct.getValueAt(rowindex,0).toString());
        if (this.popUpImport == null) {
            popUpImport = new PopUpImportGUI("PUT", productBLL.findById(id));
        } else {
            this.popUpImport.toFront();
            this.popUpImport.center();
        }
        popUpImport.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUpImport = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_itemNhapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JMenuItem itemNhap;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}