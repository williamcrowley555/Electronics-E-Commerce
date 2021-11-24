/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.ecommerceapp.bll.IRoleBLL;
import com.ecommerceapp.bll.ISupplierBLL;
import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.IUser_RoleBLL;
import com.ecommerceapp.bll.impl.RoleBLL;
import com.ecommerceapp.bll.impl.SupplierBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.bll.impl.User_RoleBLL;
import com.ecommerceapp.dto.RoleDTO;
import com.ecommerceapp.dto.SupplierDTO;
import com.ecommerceapp.dto.UserDTO;
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
import com.ecommerceapp.gui.menu.MyComboBoxEditor;
import com.ecommerceapp.gui.menu.MyComboBoxRenderer;
import com.ecommerceapp.gui.others.MD5;
import com.ecommerceapp.util.BCrypt;
import com.ecommerceapp.util.InputValidatorUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Hi
 */
public class PopUpSupplierGUI extends javax.swing.JFrame {
   
    private String action;
    
    private SupplierDTO supplier = null;
    private ISupplierBLL supplierBLL;
    public PopUpSupplierGUI(String action) {
        initComponents();
        
        this.action = action;   
        supplierBLL = new SupplierBLL();
        CustomWindow();
        myTextArea();
      
        
        this.setVisible(true);    
    }
    
    public PopUpSupplierGUI(String action, SupplierDTO supplier) {
        initComponents();
        this.action = action;  
        this.supplier = supplier;
        supplierBLL = new SupplierBLL();
        CustomWindow();
        myTextArea();
        setLabelText(supplier);
        this.setVisible(true);    
    }
    
   
    
    public void setLabelText(SupplierDTO supplier)
    {
        txtTenNCC.setText(supplier.getCompanyName());
        txtTenNLL.setText(supplier.getContactName());
        txtChucVu.setText(supplier.getContactJobTitle());
        txtQuocGia.setText(supplier.getCountry());
        txtSDT.setText(supplier.getPhone());
        txtEmail.setText(supplier.getEmail());
        txtDiaChi.setText(supplier.getAddress());
    }
    public boolean validateForm() 
    {   
        boolean TenNCC, TenNLL, ChucVu, QuocGia, Sdt, DiaChi, Email; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/error.png"));
        if (InputValidatorUtil.isValidName(txtTenNCC.getText(), true).isEmpty())
        {
            TenNCC = true;
            lblValidateTenNCC.setIcon(iconCheck);
            lblValidateTenNCC.setToolTipText(null);
        } else {
            TenNCC = false;
            lblValidateTenNCC.setIcon(iconError);
            lblValidateTenNCC.setToolTipText(InputValidatorUtil.isValidName(txtTenNCC.getText(), false));
        }
        
        if (InputValidatorUtil.isValidName(txtTenNLL.getText(), true).isEmpty())  
        {
            TenNLL = true;
            lblValidateTenNLL.setIcon(iconCheck);
            lblValidateTenNLL.setToolTipText(null);
        } else {
            TenNLL = false;
            lblValidateTenNLL.setIcon(iconError);
            lblValidateTenNLL.setToolTipText(InputValidatorUtil.isValidName(txtTenNLL.getText(), true));
        }
        
        if (InputValidatorUtil.isValidName(txtChucVu.getText(), true).isEmpty())  
        {
            ChucVu = true;
            lblValidateChucVu.setIcon(iconCheck);
            lblValidateChucVu.setToolTipText(null);
        } else {
            ChucVu = false;
            lblValidateChucVu.setIcon(iconError);
            lblValidateChucVu.setToolTipText(InputValidatorUtil.isValidName(txtChucVu.getText(), true));
        }
        
        if (InputValidatorUtil.isValidName(txtQuocGia.getText(), true).isEmpty())  
        {
            QuocGia = true;
            lblValidateQuocGia.setIcon(iconCheck);
            lblValidateQuocGia.setToolTipText(null);
        } else {
            QuocGia = false;
            lblValidateQuocGia.setIcon(iconError);
            lblValidateQuocGia.setToolTipText(InputValidatorUtil.isValidName(txtQuocGia.getText(), true));
        }
     
        if (!InputValidatorUtil.isVailidPhoneNumber(txtSDT.getText()).isEmpty()) {
            Sdt = false;
            lblValidateSDT.setIcon(iconError);
            lblValidateSDT.setToolTipText(InputValidatorUtil.isVailidPhoneNumber(txtSDT.getText()));
        } else {
            Sdt = true;
            lblValidateSDT.setIcon(iconCheck);
            lblValidateSDT.setToolTipText(null);  
        } 
        
        if (InputValidatorUtil.isValidAddress(txtDiaChi.getText()).isEmpty())  
        {
           DiaChi = true;
           lblValidateDiaChi.setIcon(iconCheck);
           lblValidateDiaChi.setToolTipText(null);
        } else {
           DiaChi = false;
           lblValidateDiaChi.setIcon(iconError);
           lblValidateDiaChi.setToolTipText(InputValidatorUtil.isValidAddress(txtDiaChi.getText()));
        }
        
        if (InputValidatorUtil.isValidEmail(txtEmail.getText()).isEmpty())  
        {
           Email = true;
           lblValidateEmail.setIcon(iconCheck);
           lblValidateEmail.setToolTipText(null);
        } else {
           Email = false;
           lblValidateEmail.setIcon(iconError);
           lblValidateEmail.setToolTipText(InputValidatorUtil.isValidAddress(txtEmail.getText()));
        }
        
        if (TenNCC && TenNLL && ChucVu && QuocGia && DiaChi && Sdt && Email)
            return true;
        else 
            return false;
    }
    private SupplierDTO getFormInfo() throws IOException, NoSuchAlgorithmException {
        SupplierDTO supplier = new SupplierDTO();
        if(this.supplier != null) {
            supplier.setId(this.supplier.getId());
        }
        supplier.setCompanyName(txtTenNCC.getText().trim());
        supplier.setContactName(txtTenNLL.getText().trim());
        supplier.setContactJobTitle(txtChucVu.getText().trim());
        supplier.setCountry(txtQuocGia.getText().trim());
        supplier.setPhone(txtSDT.getText().trim());
        supplier.setEmail(txtEmail.getText().trim());
        supplier.setAddress(txtDiaChi.getText().trim());
     
        return supplier;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public PopUpSupplierGUI() {
        initComponents();
        CustomWindow();
    }
   
    public void CustomWindow()
    {   
        Color flatBlack = new Color(77,77,77);  
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlack));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
       
    }
    
    public void myTextArea()
    {
        txtDiaChi.setWrapStyleWord(true);
        txtDiaChi.setLineWrap(true);
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
        lblTenNCC = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtTenNLL = new javax.swing.JTextField();
        lblTenNLL = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        AreaScrollPane = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblValidateTenNCC = new javax.swing.JLabel();
        lblValidateTenNLL = new javax.swing.JLabel();
        lblValidateSDT = new javax.swing.JLabel();
        lblValidateDiaChi = new javax.swing.JLabel();
        lblValidateQuocGia = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblChucVu = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        lblQuocGia = new javax.swing.JLabel();
        txtQuocGia = new javax.swing.JTextField();
        lblValidateEmail = new javax.swing.JLabel();
        lblValidateChucVu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(77, 77, 77));
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
                .addContainerGap(377, Short.MAX_VALUE)
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

        lblTenNCC.setText("Tên Nhà Cung Cấp:");
        lblTenNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTenNCC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblDiaChi.setText("Địa Chỉ:");
        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTenNLL.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTenNLL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenNLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNLLActionPerformed(evt);
            }
        });

        lblTenNLL.setText("Tên Người Liên Lạc:");
        lblTenNLL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblSDT.setText("Sđt:");
        lblSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnLuu.setBackground(new java.awt.Color(77, 77, 77));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/save_icon.png"))); // NOI18N
        btnLuu.setText(" Lưu");
        btnLuu.setBorder(null);
        btnLuu.setContentAreaFilled(false);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.setOpaque(true);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(77, 77, 77));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/cancel_icon.png"))); // NOI18N
        btnHuy.setText(" Hủy");
        btnHuy.setBorder(null);
        btnHuy.setContentAreaFilled(false);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setOpaque(true);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        AreaScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AreaScrollPane.setViewportView(txtDiaChi);

        lblValidateTenNCC.setPreferredSize(new java.awt.Dimension(24, 24));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        lblChucVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChucVu.setText("Chức Vụ:");

        txtChucVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtChucVu.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChucVuActionPerformed(evt);
            }
        });

        lblQuocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQuocGia.setText("Quốc Gia:");

        txtQuocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQuocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtQuocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuocGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblQuocGia)
                        .addGap(83, 83, 83)
                        .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblValidateQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDiaChi)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenNLL)
                            .addComponent(lblTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChucVu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txtTenNLL)
                            .addComponent(txtChucVu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValidateTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValidateTenNLL, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValidateChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblSDT)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValidateSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lblEmail)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTenNCC)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblValidateTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenNLL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNLL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblValidateTenNLL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblValidateQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValidateEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblValidateSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblDiaChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            SupplierDTO newSupplier = null;

            try {
                newSupplier = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpSupplierGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(PopUpSupplierGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {
                Long newSupplierId = supplierBLL.save(newSupplier);
                if(newSupplierId != null) {
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(this.action.equals("PUT")) {
                try {
                    supplierBLL.update(newSupplier);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtTenNLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNLLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNLLActionPerformed

    private void txtChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChucVuActionPerformed

    private void txtQuocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuocGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuocGiaActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpSupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpSupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpSupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpSupplierGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpSupplierGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblQuocGia;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNCC;
    private javax.swing.JLabel lblTenNLL;
    private javax.swing.JLabel lblValidateChucVu;
    private javax.swing.JLabel lblValidateDiaChi;
    private javax.swing.JLabel lblValidateEmail;
    private javax.swing.JLabel lblValidateQuocGia;
    private javax.swing.JLabel lblValidateSDT;
    private javax.swing.JLabel lblValidateTenNCC;
    private javax.swing.JLabel lblValidateTenNLL;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTenNLL;
    // End of variables declaration//GEN-END:variables
}
