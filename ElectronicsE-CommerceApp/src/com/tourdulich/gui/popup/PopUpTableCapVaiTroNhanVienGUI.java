/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.tourdulich.bll.IKhachHangBLL;
import com.tourdulich.bll.INhanVienBLL;
import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.bll.IVaiTroNhanVienDoanBLL;
import com.tourdulich.bll.impl.KhachHangBLL;
import com.tourdulich.bll.impl.NhanVienBLL;
import com.tourdulich.bll.impl.VaiTroBLL;
import com.tourdulich.bll.impl.VaiTroNhanVienDoanBLL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import com.tourdulich.dto.VaiTroDTO;
import com.tourdulich.dto.VaiTroNhanVienDoanDTO;
import com.tourdulich.util.KhachHangTableLoaderUtil;
import com.tourdulich.util.NhanVienTableLoaderUtil;
import com.tourdulich.util.TableSetupUtil;
import com.tourdulich.util.VaiTroTableLoaderUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Hi
 */
public class PopUpTableCapVaiTroNhanVienGUI extends javax.swing.JFrame {

    /**
     * Creates new form popUpTableNhanVien
     */
    INhanVienBLL nhanVienBLL;
    IVaiTroBLL vaiTroBLL;
    IVaiTroNhanVienDoanBLL vaiTroNhanVienDoanBLL;
    DefaultTableModel model;
    List<VaiTroDTO> vaiTroList = null;
    Long idVaiTroNhanVienDoan;
    
    String[] columnNames = {
                            "Id",
                            "Tên Vai Trò"
                            };
    public PopUpTableCapVaiTroNhanVienGUI(Long idVaiTroNhanVienDoan) {
        initComponents();       
        this.idVaiTroNhanVienDoan = idVaiTroNhanVienDoan;
        vaiTroBLL = new VaiTroBLL();
        vaiTroNhanVienDoanBLL = new VaiTroNhanVienDoanBLL();
        loadTableVaiTro();
        this.vaiTroList = getVaiTro_DoanList();
        setTableVaiTroDoan(this.vaiTroList);      
        tblVaiTro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        tblVaiTro_Doan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    public PopUpTableCapVaiTroNhanVienGUI() {
        initComponents();     
 
    }
    public void loadTableVaiTro()
    {
        tblVaiTro.setModel(new VaiTroTableLoaderUtil().setTable(vaiTroBLL.findAll(), columnNames));
         
        headerColor(14,142,233,tblVaiTro);
    }
    
    public List<VaiTroDTO> getVaiTro_DoanList()
    {
        return vaiTroNhanVienDoanBLL.findByIdVaiTroNhanVienDoan(this.idVaiTroNhanVienDoan);
    }
    
    
    public void setTableVaiTroDoan(List<VaiTroDTO> list)
    {      
        model = new DefaultTableModel(columnNames,0);
        for (int i = 0; i < list.size(); i++) {
        model.addRow(new Object[]    {      
                                         String.valueOf(list.get(i).getId()),
                                         String.valueOf(list.get(i).getTenVaiTro())
                                          
                                     });
        tblVaiTro_Doan.setModel(model);
        }
        headerColor(14,142,233,tblVaiTro_Doan);
    }
    
    private boolean save(){
        try{
        VaiTroNhanVienDoanDTO vaiTroNhanVienDoan;       
        if(vaiTroNhanVienDoanBLL.findByIdVaiTroNhanVienDoan(this.idVaiTroNhanVienDoan) != null)
            vaiTroNhanVienDoanBLL.deleteByIdDsNhanVienDoan(this.idVaiTroNhanVienDoan);
        for (VaiTroDTO vaiTro : vaiTroList)
        {
            vaiTroNhanVienDoan = new VaiTroNhanVienDoanDTO(idVaiTroNhanVienDoan, vaiTro.getId());
            vaiTroNhanVienDoanBLL.save(vaiTroNhanVienDoan);
        }
        } catch(Exception e)
        {
            return false;
        }
        return true;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVaiTro = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVaiTro_Doan = new javax.swing.JTable();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane2.setViewportView(tblVaiTro);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tblVaiTro_Doan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblVaiTro_Doan);

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
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

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/cancel_icon.png"))); // NOI18N
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

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBackground(new java.awt.Color(14, 142, 233));
        btnThem.setBorder(null);
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setOpaque(true);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBackground(new java.awt.Color(14, 142, 233));
        btnXoa.setBorder(null);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setOpaque(true);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
       if (save())
       {      
       JOptionPane.showMessageDialog(null, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       this.dispose();
       }
       else {
       JOptionPane.showMessageDialog(null, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);    
       } 
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
       int rowindex = tblVaiTro_Doan.getSelectedRow();
       if (rowindex >=0)
       {
        Long id = Long.parseLong(tblVaiTro_Doan.getValueAt(rowindex,0).toString());
        for (int i = 0; i < vaiTroList.size(); i++)
        {
            if (id.equals(vaiTroList.get(i).getId()))
            vaiTroList.remove(vaiTroList.get(i));
        }
         model = new DefaultTableModel(columnNames,0);
         for (int i = 0; i < vaiTroList.size(); i++) {
         model.addRow(new Object[]   {
                                         String.valueOf(vaiTroList.get(i).getId()),
                                         String.valueOf(vaiTroList.get(i).getTenVaiTro())    
                                     });

         }

          if (vaiTroList.size() > 0) 
                 tblVaiTro_Doan.setModel(model);
             else 
             {
                  model = new DefaultTableModel(columnNames,0);
                  tblVaiTro_Doan.setModel(model);
             }
          headerColor(14,142,233,tblVaiTro_Doan);
       }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 vai trò để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       vaiTroBLL = new VaiTroBLL();
       int rowindex = tblVaiTro.getSelectedRow();
       if (rowindex >=0)
       {
        Long id = Long.parseLong(tblVaiTro.getValueAt(rowindex,0).toString());
        VaiTroDTO selected = vaiTroBLL.findById(id);
        boolean duplicate = false;
        for (VaiTroDTO vaiTro : this.vaiTroList)
        {
            if (vaiTro.getId().equals(selected.getId()))
            duplicate = true;
        }

         if (!duplicate)
         {
             vaiTroList.add(selected);
             model = new DefaultTableModel(columnNames,0);
             for (int i = 0; i < vaiTroList.size(); i++) {
             model.addRow(new Object[]   {
                                         String.valueOf(vaiTroList.get(i).getId()),
                                         String.valueOf(vaiTroList.get(i).getTenVaiTro())                                   
                                     });
             tblVaiTro_Doan.setModel(model);
             }  
         }
         headerColor(14,142,233,tblVaiTro_Doan);      
       }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 vai trò để thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThemActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpTableCapVaiTroNhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpTableCapVaiTroNhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpTableCapVaiTroNhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpTableCapVaiTroNhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpTableCapVaiTroNhanVienGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVaiTro;
    private javax.swing.JTable tblVaiTro_Doan;
    // End of variables declaration//GEN-END:variables
}
