/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.tourdulich.bll.IDoanBLL;
import com.tourdulich.bll.IDsKhachDoanBLL;
import com.tourdulich.bll.IDsNhanVienDoanBLL;
import com.tourdulich.bll.IKhachHangBLL;
import com.tourdulich.bll.INhanVienBLL;
import com.tourdulich.bll.ITourBLL;
import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.bll.impl.DoanBLL;
import com.tourdulich.bll.impl.DsKhachDoanBLL;
import com.tourdulich.bll.impl.DsNhanVienDoanBLL;
import com.tourdulich.bll.impl.KhachHangBLL;
import com.tourdulich.bll.impl.TourBLL;
import com.tourdulich.bll.impl.VaiTroBLL;
import com.tourdulich.dto.DoanDTO;
import com.tourdulich.dto.DsKhachDoanDTO;
import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import com.tourdulich.dto.TourDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import com.tourdulich.gui.menu.MyComboBoxEditor;
import com.tourdulich.gui.menu.MyComboBoxRenderer;
import com.tourdulich.util.NhanVienTableLoaderUtil;
import com.tourdulich.util.TableSetupUtil;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hi
 */
public class PopUpCapVaiTroGUI extends javax.swing.JFrame {
    String[] listColumns = {
                        "Id",
                        "Họ",
                        "Tên",
                        "Giới Tính",
                        "Ngày Sinh",
                        "Địa Chỉ",
                        "SĐT"
                        
    };
    private String action;
    private DoanDTO doan = null;
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
    private IKhachHangBLL khachHangBLL;
    private INhanVienBLL nhanVienBLL;
    private IDsNhanVienDoanBLL dsNhanVienDoanBLL;
    private ArrayList<KhachHangDTO> listKhach = null;
    private ArrayList<NhanVienDTO> listNhanVien = null;
    private IVaiTroBLL vaiTroBLL;
    private PopUpTableCapVaiTroNhanVienGUI popUp;
    public PopUpCapVaiTroGUI(String action) {
        initComponents();
       
        dsNhanVienDoanBLL = new DsNhanVienDoanBLL();
        this.action = action;    
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        setComboBoxDoan();
       
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        comboBoxDoan = myComboBox(comboBoxDoan, new Color(14,142,233));
         loadTableData();
        this.setVisible(true);    
    }
    
    public PopUpCapVaiTroGUI(String action, DoanDTO doan) {
        initComponents(); 
        this.action = action;  
        this.doan = doan;
        
        dsNhanVienDoanBLL = new DsNhanVienDoanBLL();
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
         loadTableData();
        setLabelText(doan);
        this.setVisible(true);    
    }
     
    public void setLabelText(DoanDTO doan)
    {
       // comboBoxTour.setSelectedItem(getTourItemName(tourBLL.findById(doan.getIdTour())));
         
       
    }
    
    public void loadTableData() {
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        tblNhanVien.setModel(new NhanVienTableLoaderUtil().setTable(dsNhanVienDoanBLL.findByIdDoan(idDoan), this.listColumns)) ;
        //this.rowSorter = TableSetupUtil.setTableFilter(tblNhanVien, txtTimKiem);
        headerColor(14,142,233,tblNhanVien);
    }
   
     public boolean validateForm() 
    {   
        
         return true;
       
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getTourItems() {
        List<TourDTO> tourLists = tourBLL.findAll();
        String[] tourItems = new String[tourLists.size()];
        int index = 0;
        for(TourDTO vt : tourLists) {
            tourItems[index] = vt.getId() + " - " + vt.getTenTour();
            ++ index;
        }
        return tourItems;
    }
    
    public String getTourItemName(TourDTO tour) {
        return tour.getId() + " - " + tour.getTenTour();
    }
    
    public String[] getDoanItems(Long idTour) {
        List<DoanDTO> doanLists = doanBLL.findByIdTour(idTour);
        String[] doanItems = new String[doanLists.size()];
        int index = 0;
        for(DoanDTO vt : doanLists) {
            doanItems[index] = vt.getId() + " - " + vt.getTenDoan();
            ++ index;
        }
        return doanItems;
    }
    
    public String getDoanItemName(DoanDTO doan) {
        return doan.getId() + " - " + doan.getTenDoan();
    }
    
  
    
    public void setComboBoxDoan()
    {
        doanBLL = new DoanBLL();
        String selectedTour = comboBoxTour.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
        setComboBox(comboBoxDoan, getDoanItems(idTour));
    }
    
  
    
    
    
    public PopUpCapVaiTroGUI() {
        initComponents();
        CustomWindow();
       
    }
    
    public void CustomWindow()
    {   
        Color flatBlue = new Color(14,142,233);  
        //this.setSize(new Dimension(563,424));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
//        comboBoxTour = myComboBox(comboBoxTour, new Color(240,240,240));
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
    
    public void center()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGioiTinh = new javax.swing.ButtonGroup();
        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblChonTour = new javax.swing.JLabel();
        lblTenDoan = new javax.swing.JLabel();
        lblValidateNgayKetThuc = new javax.swing.JLabel();
        lblValidateNgayKhoiHanh = new javax.swing.JLabel();
        lblValidateTenDoan = new javax.swing.JLabel();
        comboBoxTour = new javax.swing.JComboBox<>();
        comboBoxDoan = new javax.swing.JComboBox<>();
        scroll = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        lblTenDoan1 = new javax.swing.JLabel();
        btnLuu1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(45, 113, 248));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 40));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });

        lblMinimize.setBackground(new java.awt.Color(255, 255, 255));
        lblMinimize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(608, Short.MAX_VALUE)
                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        lblChonTour.setText("Tên Tour");

        lblTenDoan.setText("Tên Đoàn:");

        lblValidateNgayKetThuc.setPreferredSize(new java.awt.Dimension(24, 24));

        comboBoxTour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxTour.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTourItemStateChanged(evt);
            }
        });
        comboBoxTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTourActionPerformed(evt);
            }
        });

        comboBoxDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxDoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxDoanItemStateChanged(evt);
            }
        });
        comboBoxDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDoanActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblNhanVien);

        lblTenDoan1.setText("Nhân Viên Trong Đoàn:");

        btnLuu1.setBackground(new java.awt.Color(14, 142, 233));
        btnLuu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu1.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu1.setText("Cấp Vai Trò");
        btnLuu1.setBorder(null);
        btnLuu1.setContentAreaFilled(false);
        btnLuu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu1.setOpaque(true);
        btnLuu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenDoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateTenDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBoxTour, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxDoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll)
                    .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenDoan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)
                        .addComponent(btnLuu1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(comboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenDoan)
                    .addComponent(lblValidateTenDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTenDoan1)
                            .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLuu1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
       this.dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void panelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseDragged
        setLocation (evt.getXOnScreen()-(getWidth()/2),evt.getYOnScreen()-10);
    }//GEN-LAST:event_panelHeaderMouseDragged
    
    private void comboBoxTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTourActionPerformed
        // TODO add your handling code here:
        setComboBoxDoan();
        loadTableData();
    }//GEN-LAST:event_comboBoxTourActionPerformed

    private void comboBoxTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTourItemStateChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_comboBoxTourItemStateChanged

    private void comboBoxDoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDoanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDoanItemStateChanged

    private void comboBoxDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDoanActionPerformed
        // TODO add your handling code here:
        loadTableData();
    }//GEN-LAST:event_comboBoxDoanActionPerformed

    private void tblNhanVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseReleased
  
        
    }//GEN-LAST:event_tblNhanVienMouseReleased

    private void btnLuu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblNhanVien.getSelectedRow();
        if (this.popUp == null) {
            if (selectedRow >=0)
            {
                int rowindex = tblNhanVien.getSelectedRow();
                Long idNhanVien = Long.parseLong(tblNhanVien.getValueAt(rowindex,0).toString());
                String selectedDoan = comboBoxDoan.getSelectedItem().toString();
                Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));         

                this.popUp = new PopUpTableCapVaiTroNhanVienGUI(dsNhanVienDoanBLL.findByIdNhanVienDoan(idDoan, idNhanVien).getId());
                this.popUp.center();
                popUp.setVisible(true);
                
                popUp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                   popUp = null;
                   loadTableData();
                }
                });
            } else JOptionPane.showMessageDialog(this, "Hãy Chọn 1 Nhân Viên Để Cấp Vai Trò", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
           
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        
      
       
    }//GEN-LAST:event_btnLuu1ActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tblNhanVienMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopUpCapVaiTroGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpCapVaiTroGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpCapVaiTroGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpCapVaiTroGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpCapVaiTroGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnLuu1;
    private javax.swing.JComboBox<String> comboBoxDoan;
    private javax.swing.JComboBox<String> comboBoxTour;
    private javax.swing.JLabel lblChonTour;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTenDoan;
    private javax.swing.JLabel lblTenDoan1;
    private javax.swing.JLabel lblValidateNgayKetThuc;
    private javax.swing.JLabel lblValidateNgayKhoiHanh;
    private javax.swing.JLabel lblValidateTenDoan;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblNhanVien;
    // End of variables declaration//GEN-END:variables
}
