/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.form;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.IInvoiceDetailBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceDetailBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.dto.ProductDTO;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.gui.menu.MyComboBoxEditor;
import com.ecommerceapp.gui.menu.MyComboBoxRenderer;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.gui.popup.popUpDInvoiceDetailGUI;
import com.ecommerceapp.gui.popup.popUpInvoiceGUI;
import com.ecommerceapp.util.InvoiceTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class BillGUI extends javax.swing.JPanel {

    /**
     * Creates new form Panel1
     */
    
         String[] columnNames = {
                            "Id",
                            "Họ",
                            "Tên",
                            "Địa Chỉ",
                            "SĐT",
                            "Tổng tiền",
                            "Ngày đặt",
                            "Ngày xác nhận",
                            "Ngày Th.Toán",
                            "Ngày Giao hàng",
                            "Ngày Hủy đơn",
                            "Trạng thái"
                            };
         
    private IInvoiceBLL invoiceBLL;
    private popUpInvoiceGUI popUp = null;
    private popUpDInvoiceDetailGUI popUpDetail = null;
    private IInvoiceDetailBLL invoiceDetailBLL;
    private IProductBLL productBLL;
    public UserDTO user;
    TableRowSorter<TableModel> rowSorter = null;
    
    public BillGUI() {
        initComponents();
       
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        productBLL = new ProductBLL();
        loadTableData();
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
       
    }
    
    public BillGUI(UserDTO user) {
        initComponents();
        this.user = user;
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        productBLL = new ProductBLL();
        loadTableData();
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
       
    }
    
    public void loadTableData() {
      
        tblInvoice.setModel(new InvoiceTableLoaderUtil().setTable(invoiceBLL.findAll(), this.columnNames)) ; 
     
        this.rowSorter = TableSetupUtil.setTableFilter(tblInvoice, txtTimKiem);
        //resizeColumnWidth(tblInvoice);
        headerColor(77,77,77,tblInvoice);
    }
    
     public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public Vector createHeader(Object[] columnNames){
        Vector header = new Vector();
        for(Object colName : columnNames){
            header.add(colName);
        }
        return header;
    }
    
    public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 40; // Min width
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
    
    public void headerColor(int r, int b, int g, JTable table)
    {
        Color color = new Color(r,b,g);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(color);
        headerRenderer.setForeground(color.WHITE);
        

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
        table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }       
         
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
    }
    
    public JComboBox myComboBox(JComboBox box, Color color)
    {   
        box.setRenderer(new MyComboBoxRenderer());
        box.setEditor(new MyComboBoxEditor());
        
        box.setUI(new BasicComboBoxUI() 
        {
            @Override
            protected ComboPopup createPopup() 
            {
                BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
                basicComboPopup.setBorder(new MatteBorder(2,2,2,2,color));
                return basicComboPopup;
            }
            
            @Override 
            protected JButton createArrowButton() 
            {
                Color matteGrey = new Color(223,230,233);
                Color flatBlue = new Color(14,142,233);
        
                BasicArrowButton custom = new BasicArrowButton(
                BasicArrowButton.SOUTH, null, null, Color.WHITE, null);
                custom.setBorder(new MatteBorder(0,0,0,0,flatBlue));
                return custom;
            }
        }); 

       return box;
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
        itemConfirm = new javax.swing.JMenuItem();
        itemConfirmShip = new javax.swing.JMenuItem();
        itemConfirmPayment = new javax.swing.JMenuItem();
        itemConfirmCancel = new javax.swing.JMenuItem();
        itemXemChiTietDon = new javax.swing.JMenuItem();
        pnlHead = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblTimKiem = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();

        itemConfirm.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/check.png"))); // NOI18N
        itemConfirm.setText("Xác nhận đơn");
        itemConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfirmActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemConfirm);

        itemConfirmShip.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemConfirmShip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/confirm_ship_icon.png"))); // NOI18N
        itemConfirmShip.setText("Xác nhận đã ship");
        itemConfirmShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfirmShipActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemConfirmShip);

        itemConfirmPayment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemConfirmPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/paid_icon.png"))); // NOI18N
        itemConfirmPayment.setText("Xác nhận đã thanh toán");
        itemConfirmPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfirmPaymentActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemConfirmPayment);

        itemConfirmCancel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemConfirmCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/delete_icon.png"))); // NOI18N
        itemConfirmCancel.setText("Hủy đơn");
        itemConfirmCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfirmCancelActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemConfirmCancel);

        itemXemChiTietDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemXemChiTietDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/view_detail_icon.png"))); // NOI18N
        itemXemChiTietDon.setText("Xem chi tiết đơn");
        itemXemChiTietDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemXemChiTietDonActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemXemChiTietDon);

        setLayout(new java.awt.BorderLayout());

        pnlHead.setBackground(new java.awt.Color(255, 255, 255));
        pnlHead.setPreferredSize(new java.awt.Dimension(808, 200));

        btnThem.setBackground(new java.awt.Color(77, 77, 77));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setContentAreaFilled(false);
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
        lblTitle.setText("Quản Lý Đơn Hàng");

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/search_icon.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTimKiem)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInvoice.setFillsViewportHeight(true);
        tblInvoice.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblInvoice.setRowHeight(35);
        tblInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblInvoiceMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblInvoice);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
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
        
          if (this.popUp == null) {
            this.popUp = new popUpInvoiceGUI("POST", user);
            
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

    private void tblInvoiceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInvoiceMouseReleased
        int r = tblInvoice.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblInvoice.getRowCount()) {
            tblInvoice.setRowSelectionInterval(r, r);
        } else {
           tblInvoice.clearSelection();
        }

        int rowindex = tblInvoice.getSelectedRow();
        
       
        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
            
            rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblInvoiceMouseReleased

    private void itemConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfirmActionPerformed
        int rowindex = tblInvoice.getSelectedRow();
        Long id = Long.parseLong(tblInvoice.getValueAt(rowindex,0).toString());
        InvoiceDTO invoice = invoiceBLL.findById(id);
        if (invoice.getStatus()==0)
        {
            invoice.setStatus(1);
            invoice.setConfirmationDate(LocalDate.now());
            invoiceBLL.update(invoice);
            System.out.println(invoice);
            JOptionPane.showMessageDialog(this, "Xác nhận thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
        } else if (invoice.getStatus()==1 ) JOptionPane.showMessageDialog(this, "Đơn hàng này đã được xác nhận", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(this, "Không thể xác nhận đơn này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       
//        if (this.popUp == null) {
//            //popUp = new PopUpGiaTourGUI("PUT", giaTourBLL.findById(id));
//        } else {
//            this.popUp.toFront();
//            this.popUp.center();
//        }
//        popUp.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                popUp = null;
//                loadTableData();
//            }
//        });
    }//GEN-LAST:event_itemConfirmActionPerformed

    private void itemConfirmPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfirmPaymentActionPerformed
        int rowindex = tblInvoice.getSelectedRow();
        Long id = Long.parseLong(tblInvoice.getValueAt(rowindex,0).toString());
        
        InvoiceDTO invoice = invoiceBLL.findById(id);
        if (invoice.getStatus()==2)
        {
            invoice.setStatus(3);
            invoice.setPaymentDate(LocalDate.now());
            invoiceBLL.update(invoice);
            JOptionPane.showMessageDialog(this, "Xác nhận đã thanh toán thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
        } else if (invoice.getStatus()==3) JOptionPane.showMessageDialog(this, "Đơn hàng này đã được được thanh toán rồi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(this, "Không thể xác nhận thanh toán đơn hàng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemConfirmPaymentActionPerformed

    private void itemConfirmShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfirmShipActionPerformed
        // TODO add your handling code here:
        int rowindex = tblInvoice.getSelectedRow();
        Long id = Long.parseLong(tblInvoice.getValueAt(rowindex,0).toString());
        
        InvoiceDTO invoice = invoiceBLL.findById(id);
        if (invoice.getStatus()==1)
        {
            invoice.setStatus(2);
            invoice.setShipDate(LocalDate.now());
            invoiceBLL.update(invoice);
            JOptionPane.showMessageDialog(this, "Xác nhận đã giao hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
        } else if (invoice.getStatus()==2) JOptionPane.showMessageDialog(this, "Đơn hàng này đã được được giao rồi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(this, "Không thể xác nhận giao đơn hàng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_itemConfirmShipActionPerformed

    private void itemConfirmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfirmCancelActionPerformed
        // TODO add your handling code here:
        int rowindex = tblInvoice.getSelectedRow();
        Long id = Long.parseLong(tblInvoice.getValueAt(rowindex,0).toString());
        
        InvoiceDTO invoice = invoiceBLL.findById(id);
        if (invoice.getStatus()==0 || invoice.getStatus()==1)
        {
            invoice.setStatus(4);
            invoice.setCancellingDate(LocalDate.now());
            invoiceBLL.update(invoice);
            
            List<ProductDTO> products = invoiceDetailBLL.findByIdInvoice(invoice.getId());
            
             for (int i = 0; i < products.size(); ++i) {
                 ProductDTO temp = products.get(i);
                 int quantityInvoice = invoiceDetailBLL.findById(invoice.getId(), temp.getId()).getQuantity();
                 temp.setQuantity(temp.getQuantity()+ quantityInvoice);
                 productBLL.update(temp);
            }
            
            
            
            JOptionPane.showMessageDialog(this, "Xác nhận hủy đơn hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
        } else if (invoice.getStatus()==4) JOptionPane.showMessageDialog(this, "Đơn hàng này đã được được hủy rồi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(this, "Không thể xác nhận hủy hàng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemConfirmCancelActionPerformed

    private void itemXemChiTietDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemXemChiTietDonActionPerformed
        // TODO add your handling code here:đấ
        int rowindex = tblInvoice.getSelectedRow();
        Long id = Long.parseLong(tblInvoice.getValueAt(rowindex,0).toString());
        if (this.popUpDetail == null) {
            try {
                popUpDetail = new popUpDInvoiceDetailGUI("PUT", invoiceBLL.findById(id));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(BillGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.popUpDetail.toFront();
            this.popUpDetail.center();
        }
        popUpDetail.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUpDetail = null;
            
        }
    });
    }//GEN-LAST:event_itemXemChiTietDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JMenuItem itemConfirm;
    private javax.swing.JMenuItem itemConfirmCancel;
    private javax.swing.JMenuItem itemConfirmPayment;
    private javax.swing.JMenuItem itemConfirmShip;
    private javax.swing.JMenuItem itemXemChiTietDon;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
