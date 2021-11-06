/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;


import com.ecommerceapp.bll.ILoaiDuLichBLL;
import com.ecommerceapp.bll.ITourBLL;
import com.ecommerceapp.bll.impl.LoaiDuLichBLL;
import com.ecommerceapp.bll.impl.TourBLL;
import com.ecommerceapp.dto.LoaiDuLichDTO;
import com.ecommerceapp.dto.TourDTO;
import com.ecommerceapp.gui.menu.MyComboBoxEditor;
import com.ecommerceapp.gui.menu.MyComboBoxRenderer;
import com.ecommerceapp.util.InputValidatorUtil;
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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Hi
 */
public class PopUpTourGUI extends javax.swing.JFrame {
   
    private String action;
    private TourDTO tour;
    private ITourBLL tourBLL;
    private ILoaiDuLichBLL loaiDuLichBLL;
    public PopUpTourGUI(String action) {
        initComponents();
        myTextArea();
        this.action = action;    
        tourBLL = new TourBLL();
        loaiDuLichBLL = new LoaiDuLichBLL();
        CustomWindow();
        myTextArea();
        setComboBox(comboBoxLoaiDuLich, getLoaiDuLichItems());
        comboBoxLoaiDuLich = myComboBox(comboBoxLoaiDuLich, new Color(14,142,233));
        
        this.setVisible(true);    
    }
    
    public PopUpTourGUI(String action, TourDTO tour) {
        initComponents();
        myTextArea();
        this.tour = tour;
        System.out.println(tour.getId());
        this.action = action;    
        tourBLL = new TourBLL();
        loaiDuLichBLL = new LoaiDuLichBLL();
        CustomWindow();  
        myTextArea();
        setComboBox(comboBoxLoaiDuLich, getLoaiDuLichItems());
        setLabelText(this.tour);
        comboBoxLoaiDuLich = myComboBox(comboBoxLoaiDuLich, new Color(14,142,233));
        
        this.setVisible(true);    
    }
    
   
    public PopUpTourGUI() {
        initComponents();
        myTextArea();
        CustomWindow();
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getLoaiDuLichItems() {
        List<LoaiDuLichDTO> loaiDuLichLists = loaiDuLichBLL.findAll();
        String[] loaiDuLichItems = new String[loaiDuLichLists.size()];
        int index = 0;
        for(LoaiDuLichDTO vt : loaiDuLichLists) {
            loaiDuLichItems[index] = vt.getId() + " - " + vt.getTenLoaiDuLich();
            ++ index;
        }
        return loaiDuLichItems;
    }
    
    
    public String getLoaiDuLichItemName(LoaiDuLichDTO loaiDuLich) {
        return loaiDuLich.getId() + " - " + loaiDuLich.getTenLoaiDuLich();
    }
    
    public void setLabelText(TourDTO tour)
    {
        txtTenTour.setText(tour.getTenTour());
        comboBoxLoaiDuLich.setSelectedItem(getLoaiDuLichItemName(loaiDuLichBLL.findById(tour.getIdLoaiDuLich())));
        txtDacDiem.setText(tour.getDacDiem());
    }
    
      public boolean validateForm() 
    {   
        
        boolean Ten, DacDiem; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/tourdulich/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/tourdulich/img/error.png"));
         
        
        if (InputValidatorUtil.isValidPattern(txtTenTour.getText(), "[^A-Za-z0-9\\-\\s]", "Tên tour không hợp lệ").isEmpty())  
        {
            Ten = true;
            lblValidateTenTour.setIcon(iconCheck);
            lblValidateTenTour.setToolTipText(null);
        } else {
            Ten = false;
            lblValidateTenTour.setIcon(iconError);
            lblValidateTenTour.setToolTipText(InputValidatorUtil.isValidPattern(txtTenTour.getText(), "[^A-Za-z0-9\\-\\s]", "Tên tour không hợp lệ"));
        } 
        
        if (InputValidatorUtil.isValidAddress(txtDacDiem.getText()).isEmpty())  
        {
            DacDiem = true;
            lblValidateDacDiem.setIcon(iconCheck);
            lblValidateDacDiem.setToolTipText(null);
        } else {
            DacDiem = false;
            lblValidateDacDiem.setIcon(iconError);
            lblValidateDacDiem.setToolTipText(InputValidatorUtil.isValidName(txtDacDiem.getText(), true));
        } 
        
        if (DacDiem && Ten)
        return true;
        else return false;
    }
      
    private TourDTO getFormInfo() throws IOException {
        TourDTO tour = new TourDTO();
        if(this.tour != null) {
            tour.setId(this.tour.getId());
        }
        tour.setTenTour(txtTenTour.getText().trim());
        String selectedLoaiDuLich = comboBoxLoaiDuLich.getSelectedItem().toString();
        Long idLoaiDuLich = Long.parseLong(selectedLoaiDuLich.substring(0, selectedLoaiDuLich.indexOf(" - ")));
        tour.setIdLoaiDuLich(idLoaiDuLich);
        tour.setDacDiem(txtDacDiem.getText().trim());
        return tour;
    }
      
    public void CustomWindow()
    {   
        Color flatBlue = new Color(14,142,233);  
        this.setSize(new Dimension(337,611));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
    }
    
    public void myTextArea()
    {
        txtDacDiem.setWrapStyleWord(true);
        txtDacDiem.setLineWrap(true);
        
       
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
        txtTenTour = new javax.swing.JTextField();
        lblLoaiDuLich = new javax.swing.JLabel();
        lblGioiThieu = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateTenTour = new javax.swing.JLabel();
        lblValidateDacDiem = new javax.swing.JLabel();
        AreaScrollPane1 = new javax.swing.JScrollPane();
        txtDacDiem = new javax.swing.JTextArea();
        comboBoxLoaiDuLich = new javax.swing.JComboBox<>();
        lblTenTour = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(45, 113, 248));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 40));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });

        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setBackground(new java.awt.Color(255, 255, 255));
        lblMinimize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });

        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(238, Short.MAX_VALUE)
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

        txtTenTour.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTenTour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTourActionPerformed(evt);
            }
        });

        lblLoaiDuLich.setText("Loại Du Lịch:");
        lblLoaiDuLich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblGioiThieu.setText("Đặc Điểm:");
        lblGioiThieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/save_icon.png"))); // NOI18N
        btnLuu.setText(" Lưu");
        btnLuu.setBackground(new java.awt.Color(14, 142, 233));
        btnLuu.setBorder(null);
        btnLuu.setContentAreaFilled(false);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setOpaque(true);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/cancel_icon.png"))); // NOI18N
        btnHuy.setText(" Hủy");
        btnHuy.setBackground(new java.awt.Color(14, 142, 233));
        btnHuy.setBorder(null);
        btnHuy.setContentAreaFilled(false);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setOpaque(true);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblValidateTenTour.setPreferredSize(new java.awt.Dimension(24, 24));

        AreaScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDacDiem.setColumns(20);
        txtDacDiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDacDiem.setRows(5);
        txtDacDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane1.setViewportView(txtDacDiem);

        comboBoxLoaiDuLich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxLoaiDuLich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxLoaiDuLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLoaiDuLichActionPerformed(evt);
            }
        });

        lblTenTour.setText("Tên Tour:");
        lblTenTour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblGioiThieu)
                        .addGap(18, 18, 18)
                        .addComponent(lblValidateDacDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTenTour, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboBoxLoaiDuLich, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AreaScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                    .addComponent(lblLoaiDuLich, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValidateTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(lblLoaiDuLich)
                .addGap(18, 18, 18)
                .addComponent(comboBoxLoaiDuLich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGioiThieu)
                    .addComponent(lblValidateDacDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void comboBoxLoaiDuLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLoaiDuLichActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxLoaiDuLichActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
                {
                        TourDTO newTour = null;
                        try {
                                newTour = getFormInfo();
                            } catch (IOException ex) {
                                Logger.getLogger(PopUpTourGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
            
                        if(this.action.equals("POST")) {
                                    Long newTourId = tourBLL.save(newTour);
                                    if(newTourId != null) {
                    
                                            JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                            dispose();
                    
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                        }
                            } else if(this.action.equals("PUT")) {
                                try {
                                        tourBLL.update(newTour);
                                        System.out.println(newTour);
                                        JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                    } catch(Exception e) {
                                        JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                        e.printStackTrace();
                                    }
                            }
                    }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtTenTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTourActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpTourGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpTourGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpTourGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpTourGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpTourGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane1;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxLoaiDuLich;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblLoaiDuLich;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTenTour;
    private javax.swing.JLabel lblValidateDacDiem;
    private javax.swing.JLabel lblValidateTenTour;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextArea txtDacDiem;
    private javax.swing.JTextField txtTenTour;
    // End of variables declaration//GEN-END:variables
}
