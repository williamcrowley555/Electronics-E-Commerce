/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.main;

import com.ecommerceapp.bll.impl.RoleBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.bll.impl.User_RoleBLL;
import com.ecommerceapp.dto.RoleDTO;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.util.BCrypt;
import com.github.javakeyring.BackendNotSupportedException;
import com.github.javakeyring.Keyring;
import com.github.javakeyring.PasswordAccessException;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Hi
 */
public class LoginGUI extends javax.swing.JFrame {

    /**
     * Creates new form LoginGUI
     */
    UserBLL userBLL;
    User_RoleBLL user_roleBLL;
    RoleBLL roleBLL;
    Color  lightGreen = new Color(41, 241, 195);  
    Color  matteGrey = new Color(223,230,233); 
    Color  white = new Color(255,255,255); 
    Color  matteBlack = new Color(77,77,77);
    
    ImageIcon iconUncheck = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/unchecked_2.png"));
    ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/checked.png"));
    String passwordWhite = "/com/ecommerceapp/img/password_rounded_white.png";
    String passwordGreen = "/com/ecommerceapp/img/password_rounded_green.png";
    String usernameWhite = "/com/ecommerceapp/img/username_white.png";
    String usernameGreen = "/com/ecommerceapp/img/username_green.png";
    
    int iconHeight = 36;
    int iconWidth = 36;
    
    public Preferences pref = Preferences.userRoot().node("Remember me");
    String serviceName = "Nexus";
    String accountName = "This pc";
    Keyring keyring = null; 
    
    public LoginGUI() throws PasswordAccessException, BackendNotSupportedException {
        initComponents();
        ImageIcon pass = new ImageIcon(getClass().getResource(passwordWhite));
        pass = scale(pass,iconHeight, iconWidth);
        customCheckbox();
        
        txtMatKhau.putClientProperty("JPasswordField.cutCopyAllowed",false);
        lblPassIcon.setIcon(pass);
      
        Boolean checked = false;
        keyring = Keyring.create();
        checked = pref.getBoolean("checked", checked);
        if (checked)
        {   
            checkboxLuuDangNhap.setSelected(checked);
            loadLoginInfo();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void customCheckbox()
    {
        checkboxLuuDangNhap.setIcon(iconUncheck);
        checkboxLuuDangNhap.setSelectedIcon(iconCheck);
        checkboxLuuDangNhap.setPressedIcon(iconCheck);
        checkboxLuuDangNhap.setDisabledIcon(iconUncheck);
        checkboxLuuDangNhap.setDisabledSelectedIcon(iconUncheck);
        checkboxLuuDangNhap.setRolloverIcon(iconUncheck);
        checkboxLuuDangNhap.setRolloverSelectedIcon(iconCheck);
    }
    
    private void loadLoginInfo() throws PasswordAccessException
    {           
        String email = null;
        email = pref.get("email", email);
        String password = null;
        password = keyring.getPassword(serviceName, accountName);

        if (email != null && password != null)
        {
            txtTenDangNhap.setText(email);
            txtMatKhau.setText(password);
            txtMatKhau.setEchoChar('\u26AB');
        }
    }
    
    private void saveLoginInfo(String email, String pass) throws PasswordAccessException
    {            
        pref.put("email", email);
        keyring.setPassword(serviceName, accountName, pass);
        pref.putBoolean("checked", true);
    }
    
    private void login(String user,char[] pass) throws Exception
    {
        user = txtTenDangNhap.getText();
        pass = txtMatKhau.getPassword();
        
        if (user.equals("Tên đăng nhập") || user.equals("") ||  pass.equals("Mật khẩu") || pass.equals(""))
        {          
            lblCanhBao.setText("Tên đăng nhập hoặc Mật khẩu còn trống");
            lblCanhBao.setBackground(new Color(231, 76, 60));
            lblCanhBao.setOpaque(true);
            //lblCanhBao.setBorder(new MatteBorder(1,1,1,1,new Color(192, 57, 43)));
           
        } 
        
        else 
        {
            lblCanhBao.setText("");
            lblCanhBao.setOpaque(false);
            String role = getRole(txtTenDangNhap.getText());
            
            if (role != null)
            {
                userBLL = new UserBLL();
                UserDTO loginUser = userBLL.findByEmail(txtTenDangNhap.getText());
                if (role.equals("ROLE_CUSTOMER")) 
                {
                    lblCanhBao.setText("Tài khoản khách không thể đăng nhập");
                    lblCanhBao.setBackground(new Color(231, 76, 60));
                    lblCanhBao.setOpaque(true);
                } 
                else 
                {   
                    if (!txtMatKhau.getText().equals("Mật khẩu"))
                    {
                        Boolean authorized = isMatch(txtTenDangNhap.getText(), txtMatKhau.getText());

                        if (authorized)   
                        {        
                            MainGUI main = new MainGUI(loginUser, role);
                            if(checkboxLuuDangNhap.isSelected())
                            {
                                saveLoginInfo(txtTenDangNhap.getText(),txtMatKhau.getText());
                            } else {
                                pref.putBoolean("checked", false);
                            }
                            main.setVisible(true);
                            this.dispose();
                        } 
                        else 
                        {   
                            lblCanhBao.setText("Mật khẩu không đúng");
                            lblCanhBao.setBackground(new Color(231, 76, 60));
                            lblCanhBao.setOpaque(true);
                        } 
                    }
                    else 
                        {
                            lblCanhBao.setText("Mật khẩu còn trống");
                            lblCanhBao.setBackground(new Color(231, 76, 60));
                            lblCanhBao.setOpaque(true);
                        } 
                } 
                
            } 
            else 
            {
                lblCanhBao.setText("Tên đăng nhập không tồn tại");
                lblCanhBao.setBackground(new Color(231, 76, 60));
                lblCanhBao.setOpaque(true);
            } 
            
            
        }
    } 
    
    public boolean isMatch(String email, String password)
    {   
        userBLL = new UserBLL();
        Boolean matched = false;
        UserDTO user = userBLL.findByEmail(email);
        if (user != null)
        matched = BCrypt.checkpw(password, user.getPassword());
        return matched; 
    }
    
    public String getRole(String email)
    {   
        userBLL = new UserBLL();
        user_roleBLL =  new User_RoleBLL();
        roleBLL = new RoleBLL();
        List<RoleDTO> role;
        UserDTO user = userBLL.findByEmail(email);
        if(user != null)
        {
            role = user_roleBLL.findByIdUser(user.getId());
            return role.get(0).getNormalizedName();
        }
        return null;
    }
    
    public ImageIcon scale(ImageIcon icon,int width, int height)
    {
        Image temp = icon.getImage();
        Image temp2 = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon();
        icon.setImage(temp2);
        return icon;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCanhBao = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        lblPassIcon = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        btnDangNhap1 = new javax.swing.JButton();
        checkboxLuuDangNhap = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(77, 77, 77));

        lblCanhBao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCanhBao.setForeground(new java.awt.Color(255, 255, 255));
        lblCanhBao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnDangNhap.setBackground(new java.awt.Color(35, 203, 167));
        btnDangNhap.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.setBorder(null);
        btnDangNhap.setContentAreaFilled(false);
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.setOpaque(true);
        btnDangNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseMoved(evt);
            }
        });
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangNhapMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseReleased(evt);
            }
        });
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/logo.png"))); // NOI18N

        lblUserIcon.setBackground(new java.awt.Color(77, 77, 77));
        lblUserIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserIcon.setText(" ");
        lblUserIcon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 0, new java.awt.Color(255, 255, 255)));
        lblUserIcon.setOpaque(true);

        txtTenDangNhap.setBackground(new java.awt.Color(77, 77, 77));
        txtTenDangNhap.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtTenDangNhap.setText("Tên đăng nhập");
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 2, new java.awt.Color(255, 255, 255)));
        txtTenDangNhap.setSelectionColor(new java.awt.Color(14, 142, 233));
        txtTenDangNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                txtTenDangNhapMouseDragged(evt);
            }
        });
        txtTenDangNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenDangNhapFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenDangNhapFocusLost(evt);
            }
        });
        txtTenDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenDangNhapMouseClicked(evt);
            }
        });
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        txtTenDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenDangNhapKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenDangNhapKeyTyped(evt);
            }
        });

        lblPassIcon.setBackground(new java.awt.Color(77, 77, 77));
        lblPassIcon.setForeground(new java.awt.Color(223, 230, 233));
        lblPassIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassIcon.setText(" ");
        lblPassIcon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 0, new java.awt.Color(255, 255, 255)));
        lblPassIcon.setOpaque(true);

        txtMatKhau.setBackground(new java.awt.Color(77, 77, 77));
        txtMatKhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setText("Mật khẩu");
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 2, new java.awt.Color(255, 255, 255)));
        txtMatKhau.setEchoChar((char)0);
        txtMatKhau.setSelectionColor(new java.awt.Color(14, 142, 233));
        txtMatKhau.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                txtMatKhauMouseDragged(evt);
            }
        });
        txtMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusLost(evt);
            }
        });
        txtMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatKhauMouseClicked(evt);
            }
        });
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyReleased(evt);
            }
        });

        btnDangNhap1.setBackground(new java.awt.Color(77, 77, 77));
        btnDangNhap1.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDangNhap1.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/logout_door_green.png"))); // NOI18N
        btnDangNhap1.setBorder(null);
        btnDangNhap1.setContentAreaFilled(false);
        btnDangNhap1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap1.setOpaque(true);
        btnDangNhap1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnDangNhap1MouseMoved(evt);
            }
        });
        btnDangNhap1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangNhap1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDangNhap1MouseReleased(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhap1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangNhap1MouseExited(evt);
            }
        });
        btnDangNhap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhap1ActionPerformed(evt);
            }
        });

        checkboxLuuDangNhap.setBackground(new java.awt.Color(77, 77, 77));
        checkboxLuuDangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkboxLuuDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        checkboxLuuDangNhap.setText(" Lưu đăng nhập");
        checkboxLuuDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(35, 203, 167)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCanhBao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDangNhap1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblUserIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(lblPassIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenDangNhap)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                            .addComponent(checkboxLuuDangNhap, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblCanhBao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUserIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhau)
                    .addComponent(lblPassIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkboxLuuDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangNhap1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(35, 203, 167));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDangNhapMouseMoved

    private void btnDangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDangNhapMouseEntered

    private void btnDangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDangNhapMouseExited

    private void btnDangNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMousePressed
   
    }//GEN-LAST:event_btnDangNhapMousePressed

    private void btnDangNhapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseReleased
 
    }//GEN-LAST:event_btnDangNhapMouseReleased

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        try {
            // TODO add your handling code here:
            login(txtTenDangNhap.getText(),txtMatKhau.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void txtTenDangNhapMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenDangNhapMouseDragged
        // TODO add your handling code here:
        lblCanhBao.setText("");
         lblCanhBao.setOpaque(false);
    }//GEN-LAST:event_txtTenDangNhapMouseDragged

    private void txtTenDangNhapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenDangNhapFocusGained
        // TODO add your handling code here:
        if (txtTenDangNhap.getText().equals("Tên đăng nhập")) {
            txtTenDangNhap.setText("");
            txtTenDangNhap.setForeground(Color.BLACK);
        }

        txtTenDangNhap.setBackground(matteBlack);
        txtTenDangNhap.setBorder( new MatteBorder(2,0,2,2,lightGreen));
        txtTenDangNhap.setForeground(lightGreen);

        ImageIcon user = new ImageIcon(getClass().getResource(usernameGreen));
        user = scale(user, iconHeight, iconWidth);
        lblUserIcon.setIcon(user);
        lblUserIcon.setBorder( new MatteBorder(2,2,2,0,lightGreen));
        lblUserIcon.setBackground(matteBlack);
    }//GEN-LAST:event_txtTenDangNhapFocusGained

    private void txtTenDangNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenDangNhapFocusLost
        // TODO add your handling code here:
        if (txtTenDangNhap.getText().isEmpty()) {
            txtTenDangNhap.setForeground(Color.GRAY);
            txtTenDangNhap.setText("Tên đăng nhập");
        }
        if (txtTenDangNhap.getText().equals("Tên đăng nhập"))
        {
            txtTenDangNhap.setBackground(matteBlack);
            txtTenDangNhap.setBorder(new MatteBorder(2,0,2,2,white));
            txtTenDangNhap.setForeground(white);

            ImageIcon user = new ImageIcon(getClass().getResource(usernameWhite));
            user = scale(user,iconHeight, iconWidth);
            lblUserIcon.setIcon(user);
            lblUserIcon.setBorder(new MatteBorder(2,2,2,0,white));
            lblUserIcon.setBackground(matteBlack);
        }
    }//GEN-LAST:event_txtTenDangNhapFocusLost

    private void txtTenDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenDangNhapMouseClicked
        // TODO add your handling code here:
        lblCanhBao.setText("");
         lblCanhBao.setOpaque(false);
    }//GEN-LAST:event_txtTenDangNhapMouseClicked

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtTenDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDangNhapKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btnDangNhap.doClick();
    }//GEN-LAST:event_txtTenDangNhapKeyPressed

    private void txtTenDangNhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDangNhapKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenDangNhapKeyTyped

    private void txtMatKhauMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatKhauMouseDragged
        // TODO add your handling code here:
        lblCanhBao.setText("");
         lblCanhBao.setOpaque(false);
    }//GEN-LAST:event_txtMatKhauMouseDragged

    private void txtMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusGained
        // TODO add your handling code here:
        if (txtMatKhau.getText().equals("Mật khẩu")) {
            txtMatKhau.setText("");
            txtMatKhau.setForeground(Color.BLACK);
            txtMatKhau.setEchoChar('\u26AB');
        }
        txtMatKhau.setBackground(matteBlack);
        txtMatKhau.setBorder( new MatteBorder(2,0,2,2,lightGreen));
        txtMatKhau.setForeground(lightGreen);

        ImageIcon pass = new ImageIcon(getClass().getResource(passwordGreen));
        pass = scale(pass,iconHeight, iconWidth);
        lblPassIcon.setIcon(pass);
        lblPassIcon.setBorder( new MatteBorder(2,2,2,0,lightGreen));
        lblPassIcon.setBackground(matteBlack);
    }//GEN-LAST:event_txtMatKhauFocusGained

    private void txtMatKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusLost
        // TODO add your hand  ling code here:
        if (txtMatKhau.getText().equals("")) {
            txtMatKhau.setForeground(Color.GRAY);
            txtMatKhau.setText("Mật khẩu");
            txtMatKhau.setEchoChar((char) 0);
        }
        if (txtMatKhau.getText().equals("Mật khẩu"))
        {
            txtMatKhau.setBackground(matteBlack);
            txtMatKhau.setBorder(new MatteBorder(2,0,2,2,white));
            txtMatKhau.setForeground(white);

            ImageIcon pass = new ImageIcon(getClass().getResource(passwordWhite));
            pass = scale(pass,iconHeight, iconWidth);
            lblPassIcon.setIcon(pass);
            lblPassIcon.setBorder(new MatteBorder(2,2,2,0,white));
            lblPassIcon.setBackground(matteBlack);
        }
    }//GEN-LAST:event_txtMatKhauFocusLost

    private void txtMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatKhauMouseClicked
        // TODO add your handling code here:
        lblCanhBao.setText("");
        lblCanhBao.setOpaque(false);
    }//GEN-LAST:event_txtMatKhauMouseClicked

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        btnDangNhap.doClick();
    }//GEN-LAST:event_txtMatKhauKeyPressed

    private void btnDangNhap1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhap1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhap1MouseMoved

    private void btnDangNhap1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhap1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhap1MouseEntered

    private void btnDangNhap1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhap1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhap1MouseExited

    private void btnDangNhap1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhap1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhap1MousePressed

    private void btnDangNhap1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhap1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhap1MouseReleased

    private void btnDangNhap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhap1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnDangNhap1ActionPerformed

    private void txtMatKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauKeyReleased

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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginGUI().setVisible(true);
                } catch (PasswordAccessException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BackendNotSupportedException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnDangNhap1;
    private javax.swing.JCheckBox checkboxLuuDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCanhBao;
    private javax.swing.JLabel lblPassIcon;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
