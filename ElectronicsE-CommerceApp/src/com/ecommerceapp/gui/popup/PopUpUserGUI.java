/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.ecommerceapp.bll.IRoleBLL;
import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.IUser_RoleBLL;
import com.ecommerceapp.bll.impl.RoleBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.bll.impl.User_RoleBLL;
import com.ecommerceapp.dto.RoleDTO;
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
public class PopUpUserGUI extends javax.swing.JFrame {
   
    private String action;
    private UserDTO user = null;
    private IUserBLL userBLL;
    private IRoleBLL roleBLL;
    private IUser_RoleBLL user_roleBLL;
    private RoleDTO role;
    public PopUpUserGUI(String action) {
        initComponents();
        
        this.action = action;    
        userBLL = new UserBLL();
        roleBLL = new RoleBLL();
        user_roleBLL = new User_RoleBLL();
        CustomWindow();
        myTextArea();
        setComboBox(ComboboxVaiTro, getVaiTroItems());
        disableEditingDateChooser(true);
        txtPass.setEchoChar('\u26AB');
        ComboboxVaiTro = myComboBox(ComboboxVaiTro, new Color(77,77,77));
        this.setVisible(true);    
    }
    
    public PopUpUserGUI(String action, UserDTO user) {
        initComponents();
        this.action = action;  
        this.user = user;
        userBLL = new UserBLL();
        roleBLL = new RoleBLL();
        user_roleBLL = new User_RoleBLL();
        hideEmailAndPassword();
        CustomWindow();
        myTextArea();
        setLabelText(user);
        ComboboxVaiTro = myComboBox(ComboboxVaiTro, new Color(77,77,77));
        this.setVisible(true);    
    }
    
    public void hideEmailAndPassword(){
        lblEmail.setVisible(false);
        txtEmail.setVisible(false);
        lblPass.setVisible(false);
        txtPass.setVisible(false);
        lblValidateEmail.setVisible(false);
        lblValidatePassword.setVisible(false);
    }
    
    public void disableEditingDateChooser(boolean flag)
    {
        if (flag)
        {
            JTextField editor =  DCNgaySinh.getComponentDateTextField();
            editor.setEditable(false);
        }else{
            
        }
    }
    
    public void setLabelText(UserDTO user)
    {
        txtHo.setText(user.getLastName());
        txtTen.setText(user.getFirstName());
        if(user.getGender()== 1) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        DCNgaySinh.setDate(user.getDob());
        txtDiaChi.setText(user.getAddress());
        txtSDT.setText(user.getPhone());
        setComboBox(ComboboxVaiTro, getVaiTroItems());
        ComboboxVaiTro.setSelectedItem(getVaiTroItemName(user_roleBLL.findByIdUser(user.getId()).get(0)));
       
    }
    public boolean validateForm() 
    {   
        boolean Ho, Ten, Sdt = false, DiaChi, NgaySinh, Email, Password; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/error.png"));
        if (InputValidatorUtil.isValidName(txtHo.getText(), false).isEmpty())
        {
            Ho = true;
            lblValidateHo.setIcon(iconCheck);
            lblValidateHo.setToolTipText(null);
        } else {
            Ho = false;
            lblValidateHo.setIcon(iconError);
            lblValidateHo.setToolTipText(InputValidatorUtil.isValidName(txtHo.getText(), false));
        }
        
        if (InputValidatorUtil.isValidName(txtTen.getText(), true).isEmpty())  
        {
            Ten = true;
            lblValidateTen.setIcon(iconCheck);
            lblValidateTen.setToolTipText(null);
        } else {
            Ten = false;
            lblValidateTen.setIcon(iconError);
            lblValidateTen.setToolTipText(InputValidatorUtil.isValidName(txtTen.getText(), true));
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
        
        if (InputValidatorUtil.isValidBirthDate(DCNgaySinh.getDate(), 18).isEmpty())  
        {
            NgaySinh = true;
            lblValidateNgaySinh.setIcon(iconCheck);
            lblValidateNgaySinh.setToolTipText(null);
        } else {
            NgaySinh = false;
            lblValidateNgaySinh.setIcon(iconError);
            lblValidateNgaySinh.setToolTipText(InputValidatorUtil.isValidBirthDate(DCNgaySinh.getDate(),18));
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
           lblValidateEmail.setToolTipText(InputValidatorUtil.isValidAddress(txtDiaChi.getText()));
        }
        
        if (InputValidatorUtil.isValidAddress(txtPass.getText()).isEmpty())  
        {
           Password = true;
           lblValidatePassword.setIcon(iconCheck);
           lblValidatePassword.setToolTipText(null);
        } else {
           Password = false;
           lblValidatePassword.setIcon(iconError);
           lblValidatePassword.setToolTipText(InputValidatorUtil.isValidAddress(txtDiaChi.getText()));
        }
        
        if (action.equals("POST"))
        {
            if (Ho && Ten && Sdt && NgaySinh && DiaChi && Email && Password)
            return true;
            else return false;
        } else {
            if (Ho && Ten && Sdt && NgaySinh && DiaChi)
            return true;
            else return false;
        }
       
    }
    private UserDTO getFormInfo() throws IOException, NoSuchAlgorithmException {
        UserDTO user = new UserDTO();
        if(this.user != null) {
            user.setId(this.user.getId());
        }
        user.setLastName(txtHo.getText().trim());
        user.setFirstName(txtTen.getText().trim());
        user.setGender(radioNam.isSelected() ? 1 : 0);
        user.setDob(DCNgaySinh.getDate());
        user.setAddress(txtDiaChi.getText().trim());
        user.setPhone(txtSDT.getText().trim());
        
        if (action.equals("POST")){
            user.setEmail(txtEmail.getText().trim());
            String ernText = BCrypt.hashpw(txtPass.getText().trim(), BCrypt.gensalt());
            user.setPassword(ernText);
        } else {
            user.setEmail(this.user.getEmail());
            user.setPassword(this.user.getPassword());
        }
        return user;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getVaiTroItems() {
        List<RoleDTO> roleLists = roleBLL.findAll();
        String[] roleItems = new String[roleLists.size()];
        int index = 0;
        for(RoleDTO vt : roleLists) {
            roleItems[index] = vt.getId() + " - " + vt.getName();
            ++ index;
        }
        return roleItems;
    }
    
    public String getVaiTroItemName(RoleDTO role) {
        return role.getId() + " - " + role.getName();
    }
    
    public PopUpUserGUI() {
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
        lblHo = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        lblTen = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNgaySinh = new javax.swing.JLabel();
        AreaScrollPane = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        DCNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        lblValidateHo = new javax.swing.JLabel();
        lblValidateTen = new javax.swing.JLabel();
        lblValidateSDT = new javax.swing.JLabel();
        lblValidateDiaChi = new javax.swing.JLabel();
        lblValidateNgaySinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        ComboboxVaiTro = new javax.swing.JComboBox<>();
        lblValidateEmail = new javax.swing.JLabel();
        lblValidatePassword = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblHo.setText("Họ:");
        lblHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblDiaChi.setText("Địa Chỉ:");
        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        lblGioiTinh.setText("Giới Tính:");
        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnGroupGioiTinh.add(radioNam);
        radioNam.setSelected(true);
        radioNam.setText("Nam");
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });

        btnGroupGioiTinh.add(radioNu);
        radioNu.setText("Nữ");
        radioNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNuActionPerformed(evt);
            }
        });

        lblTen.setText("Tên:");
        lblTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        lblNgaySinh.setText("Ngày Sinh:");
        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        AreaScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AreaScrollPane.setViewportView(txtDiaChi);

        lblValidateHo.setPreferredSize(new java.awt.Dimension(24, 24));

        lblEmail.setText("Email:");
        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblPass.setText("Mật Khẩu:");
        lblPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblVaiTro.setText("Vai trò:");

        ComboboxVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboboxVaiTro.setPreferredSize(new java.awt.Dimension(130, 26));
        ComboboxVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboboxVaiTroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(lblTen)
                                    .addGap(12, 12, 12)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblValidateTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(lblHo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblValidateHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(DCNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblVaiTro))
                            .addComponent(lblPass)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(radioNam)
                                .addGap(18, 18, 18)
                                .addComponent(radioNu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(ComboboxVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblValidateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValidateEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblValidatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblSDT)
                        .addGap(12, 12, 12)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblValidateSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioiTinh))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNgaySinh)
                    .addComponent(lblDiaChi))
                .addContainerGap())
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblHo))
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValidateHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblValidateSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblNgaySinh)
                        .addGap(4, 4, 4))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblValidateEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPass, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValidatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioNam)
                            .addComponent(radioNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValidateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DCNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVaiTro)
                        .addComponent(ComboboxVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblDiaChi)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(31, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
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

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNamActionPerformed

    private void radioNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNuActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void ComboboxVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboboxVaiTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboboxVaiTroActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            UserDTO newUser = null;
           
            try {
                newUser = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpUserGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(PopUpUserGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {
                Long newUserId = userBLL.save(newUser);
                String selectedRole = ComboboxVaiTro.getSelectedItem().toString();
                Long idRole = Long.parseLong(selectedRole.substring(0, selectedRole.indexOf(" - ")));
                //  Long newRoleId = user_roleBLL.save(newRole);
                if(newUserId != null) {
                    user_roleBLL.save(newUserId,idRole);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(this.action.equals("PUT")) {
                try {
                    userBLL.update(newUser);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpUserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpUserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpUserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpUserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new PopUpUserGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane;
    private javax.swing.JComboBox<String> ComboboxVaiTro;
    private com.github.lgooddatepicker.components.DatePicker DCNgaySinh;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHo;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JLabel lblValidateDiaChi;
    private javax.swing.JLabel lblValidateEmail;
    private javax.swing.JLabel lblValidateHo;
    private javax.swing.JLabel lblValidateNgaySinh;
    private javax.swing.JLabel lblValidatePassword;
    private javax.swing.JLabel lblValidateSDT;
    private javax.swing.JLabel lblValidateTen;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
