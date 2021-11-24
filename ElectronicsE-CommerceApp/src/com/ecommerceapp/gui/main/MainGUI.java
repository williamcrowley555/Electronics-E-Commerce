/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.main;


import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.enums.ERole;
import com.ecommerceapp.gui.form.BillGUI;
import com.ecommerceapp.gui.form.BrandGUI;
import com.ecommerceapp.gui.form.CatalogGUI;
import com.ecommerceapp.gui.form.ProcessedGUI;
import com.ecommerceapp.gui.others.ComponentResizer;
import com.ecommerceapp.gui.form.ProductGUI;
import com.ecommerceapp.gui.form.RevenueGUI;
import com.ecommerceapp.gui.form.UserGUI;
import com.ecommerceapp.gui.form.RoleGUI;
import com.ecommerceapp.gui.form.StaffRevenueGUI;
import com.ecommerceapp.gui.form.SupplierGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import com.ecommerceapp.gui.menu.MenuItem;
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.gui.popup.PopUpChangePasswordGUI;
import com.github.javakeyring.BackendNotSupportedException;
import com.github.javakeyring.PasswordAccessException;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RavenPC
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private boolean menuHided = false;
    private boolean maximized = false;
    ImageIcon iconShowMenu = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/show_menu_icon.png"));
    ImageIcon iconHideMenu = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/hide_menu_icon.png"));
    ImageIcon iconRestoreDown = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/restore_down.png"));
    ImageIcon iconCatalog = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/catalog_icon.png"));
    ImageIcon iconBrand = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/brand_icon.png"));
    ImageIcon iconProduct = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/product_icon.png"));
    ImageIcon iconInvoice = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/invoice_icon.png"));
    ImageIcon iconStaff = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/staff_icon.png"));
    ImageIcon iconRole = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/role_icon.png"));
    ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/com/ecommerceapp/img/thong_ke_icon.png"));
    
    
   
        
        //----Sub menu quanly nhan hieu -----
                MenuItem menuCatalog = new MenuItem(iconCatalog, "Quản Lý Loại Sản Phẩm", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        panelBody.removeAll();
                        panelBody.add(new CatalogGUI());
                        panelBody.repaint();
                        panelBody.revalidate();
                        Selected(menuProduct);
                    }
                });  
        
        //----Sub menu quanly nhan hieu -----
                MenuItem menuBrand = new MenuItem(iconBrand, "Quản Lý Nhãn Hiệu", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        panelBody.removeAll();
                        panelBody.add(new BrandGUI());
                        panelBody.repaint();
                        panelBody.revalidate();
                        Selected(menuProduct);
                    }
                });             
     
        MenuItem menuProduct = new MenuItem(iconProduct, "Quản Lý Sản Phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ProductGUI());
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuProduct);
            }
        }, menuBrand, menuCatalog);
        
        MenuItem menuInvoice = new MenuItem(iconInvoice, "Quản Lý Đơn Hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new BillGUI());
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuInvoice);
            }
        });
        
        
        //----Sub menu vai tro -----
                MenuItem menuRole = new MenuItem(iconRole, "Quản Lý Vai Trò", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        panelBody.removeAll();
                        panelBody.add(new RoleGUI());
                        panelBody.repaint();
                        panelBody.revalidate();
                        Selected(menuUser);
                    }
                });
        
        MenuItem menuUser = new MenuItem(iconStaff, "Quản Lý Người Dùng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UserGUI());
                panelBody.repaint();
                panelBody.revalidate();
                
                
                Selected(menuUser);
            }
        }, menuRole);
        
        MenuItem menuSupplier = new MenuItem(iconStaff, "Quản Lý NCC", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new SupplierGUI());
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuSupplier);
            }
        });
        
        MenuItem menuThongKe = new MenuItem(iconThongKe, "Thống kê theo tháng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new StaffRevenueGUI());
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuThongKe);
            }
        });
        
        MenuItem menuThongKeXuLiDon = new MenuItem(iconThongKe, "Thống kê xử lí đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ProcessedGUI());
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuThongKeXuLiDon);
            }
        });
    public UserDTO currentUser;
    public PopUpChangePasswordGUI popup;
    public MainGUI(UserDTO currentUser, String role) {
        initComponents();
        invisibleMenuScrollBar(8);
        this.currentUser = currentUser;
        panelBody.add(new ProductGUI(currentUser));
        panelBody.repaint();
        panelBody.revalidate();
        CustomWindow();
        
        switch(ERole.valueOf(role)){
            case ROLE_ADMIN:
            {   
                reInitMenusWithUser(currentUser);
                addMenu(menuProduct, menuSupplier, menuInvoice,menuUser, menuThongKe, menuThongKeXuLiDon);
                break;
            }
            
            case ROLE_EMPLOYEE:
            {   
                reInitMenusWithUser(currentUser);
                addMenu(menuProduct,menuInvoice);
                break;
            }
            
            case ROLE_WAREHOUSE_STAFF:
            {   
                reInitMenusWithUser(currentUser);
                addMenu(menuProduct);
                break;
            }
        }
        lblWelcome.setText("Xin chào "+ currentUser.getLastName() + " " + currentUser.getFirstName());
        Selected(menuProduct);
        
    }

    private MainGUI() {
        initComponents();
        invisibleMenuScrollBar(8);
        panelBody.add(new ProductGUI());
        panelBody.repaint();
        panelBody.revalidate();
        CustomWindow();
        addMenu(menuProduct, menuSupplier, menuInvoice,menuUser, menuThongKe, menuThongKeXuLiDon, menuSupplier);
        Selected(menuProduct);
    }
    
    public void reInitMenusWithUser(UserDTO user){
       menuInvoice = new MenuItem(iconInvoice, "Quản Lý Đơn Hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new BillGUI(user));
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuInvoice);
            }
        });
       
      menuProduct = new MenuItem(iconProduct, "Quản Lý Sản Phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ProductGUI(user));
                panelBody.repaint();
                panelBody.revalidate();
                Selected(menuProduct);
            }
        }, menuBrand, menuCatalog);
    }
    
    public void invisibleMenuScrollBar(int speed)
    {
          JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {

                    @Override
                    public boolean isVisible() {
                        return true;
                    }
                };
        menuScroll.setVerticalScrollBar(scrollBar);
        menuScroll.setVerticalScrollBarPolicy(menuScroll.VERTICAL_SCROLLBAR_NEVER);
        menuScroll.getVerticalScrollBar().setUnitIncrement(speed);
    }
    public void center()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    public void CustomWindow()
    {   
        Color flatBlack = new Color(77,77,77);//Border
        this.setMinimumSize(new Dimension(800,600));
        this.setSize(new Dimension(1280,720));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,2,2,2, flatBlack));   
        center();
        ComponentResizer cr = new ComponentResizer();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
        lblMaximize_Restore.setText("\u2750");
        cr.registerComponent(this);
    }
    
    
    
    public void Maximize_Restore(boolean state)
    {
       if (state)
       {
            lblMaximize_Restore.setText("\u2750");
            lblMaximize_Restore.setIcon(null);
            maximized = false;
            this.setPreferredSize(new Dimension(800,600));
            this.setExtendedState(JFrame.NORMAL);
       }
       else 
       {
           lblMaximize_Restore.setIcon(iconRestoreDown);
           lblMaximize_Restore.setText("");
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           maximized = true;   
       }
    }
    
    public void resetSelect()
    {
       Color flatBlack = new Color(77,77,77);
     
       menuProduct.setColor(flatBlack);
       menuInvoice.setColor(flatBlack);
       menuUser.setColor(flatBlack);
       menuThongKe.setColor(flatBlack);
       menuThongKeXuLiDon.setColor(flatBlack);
       menuSupplier.setColor(flatBlack);
    }
   
    public void Selected(MenuItem item)
    {   
        resetSelect();
        item.setColor(new Color(38, 38, 38));
    }
    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblMaximize_Restore = new javax.swing.JLabel();
        lblShow_HideMenu = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        menuScroll = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        pnlAccount = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        lblChangePass = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(38, 38, 38));
        panelHeader.setForeground(new java.awt.Color(255, 255, 255));
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

        lblMaximize_Restore.setBackground(new java.awt.Color(255, 255, 255));
        lblMaximize_Restore.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMaximize_Restore.setForeground(new java.awt.Color(255, 255, 255));
        lblMaximize_Restore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaximize_Restore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaximize_RestoreMouseClicked(evt);
            }
        });

        lblShow_HideMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShow_HideMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/show_menu_icon.png"))); // NOI18N
        lblShow_HideMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblShow_HideMenuMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addComponent(lblShow_HideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 654, Short.MAX_VALUE)
                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblMaximize_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMaximize_Restore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblShow_HideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(300, 384));

        menuScroll.setBorder(null);

        menus.setBackground(new java.awt.Color(77, 77, 77));
        menus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        menuScroll.setViewportView(menus);

        pnlAccount.setBackground(new java.awt.Color(77, 77, 77));
        pnlAccount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/logo.png"))); // NOI18N

        lblWelcome.setBackground(new java.awt.Color(41, 241, 195));
        lblWelcome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(41, 241, 195));
        lblWelcome.setText("Hello");

        lblLogout.setBackground(new java.awt.Color(41, 241, 195));
        lblLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(41, 241, 195));
        lblLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/logout_door_green.png"))); // NOI18N
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });

        lblChangePass.setBackground(new java.awt.Color(41, 241, 195));
        lblChangePass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChangePass.setForeground(new java.awt.Color(41, 241, 195));
        lblChangePass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChangePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/changePassword.png"))); // NOI18N
        lblChangePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChangePassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccount);
        pnlAccount.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblWelcome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblWelcome)
                    .addComponent(lblLogout)
                    .addComponent(lblChangePass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(pnlAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(853, 426));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblMaximize_RestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaximize_RestoreMouseClicked
        // TODO add your handling code here:
        Maximize_Restore(maximized);
    }//GEN-LAST:event_lblMaximize_RestoreMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void panelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseDragged
           setLocation (evt.getXOnScreen()-(getWidth()/2),evt.getYOnScreen()-10);
    }//GEN-LAST:event_panelHeaderMouseDragged

    private void lblShow_HideMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShow_HideMenuMousePressed
        // TODO add your handling code here:
        if (menuHided)
        {
        panelMenu.setVisible(true);
        menuHided = false;
        lblShow_HideMenu.setIcon(iconHideMenu);
        }
        else {
             panelMenu.setVisible(false);
             menuHided = true; 
             lblShow_HideMenu.setIcon(iconShowMenu);
             }
    }//GEN-LAST:event_lblShow_HideMenuMousePressed

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
        LoginGUI login;
        try {
            login = new LoginGUI();
            login.setVisible(true);
        } catch (PasswordAccessException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BackendNotSupportedException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void lblChangePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangePassMouseClicked
        // TODO add your handling code here:
        if (this.popup == null) {
            this.popup = new PopUpChangePasswordGUI(this.currentUser);
            
        } else {
            this.popup.toFront();
            this.popup.center();
        }
        this.popup.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popup = null;
        }
    });
    }//GEN-LAST:event_lblChangePassMouseClicked

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginGUI().setVisible(true);
                } catch (PasswordAccessException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BackendNotSupportedException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblChangePass;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMaximize_Restore;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblShow_HideMenu;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JScrollPane menuScroll;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel pnlAccount;
    // End of variables declaration//GEN-END:variables
}
