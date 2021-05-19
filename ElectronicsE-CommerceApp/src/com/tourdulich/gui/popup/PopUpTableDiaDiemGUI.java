/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IDsDiaDiemTourBLL;
import com.tourdulich.bll.IKhachHangBLL;
import com.tourdulich.bll.INhanVienBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.DsDiaDiemTourBLL;
import com.tourdulich.bll.impl.KhachHangBLL;
import com.tourdulich.bll.impl.NhanVienBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import com.tourdulich.dto.TinhDTO;
import com.tourdulich.gui.menu.MyComboBoxEditor;
import com.tourdulich.gui.menu.MyComboBoxRenderer;
import com.tourdulich.util.DiaDiemTableLoaderUtil;
import com.tourdulich.util.KhachHangTableLoaderUtil;
import com.tourdulich.util.NhanVienTableLoaderUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class PopUpTableDiaDiemGUI extends javax.swing.JFrame {

    /**
     * Creates new form popUpTableNhanVien
     */
    ITinhBLL tinhBLL;
    IDiaDiemBLL diaDiemBLL;
    IDsDiaDiemTourBLL dsDiaDiemTourBLL; 
    List<DiaDiemDTO> diaDiemList = null;
    DefaultTableModel model;
    Long idTour;
    String[] columnNames = {
                            "Id",
                            "Tên Địa Điểm"
                            };
    public PopUpTableDiaDiemGUI(String idTour) {
        initComponents();
        tinhBLL = new TinhBLL();
        diaDiemBLL = new DiaDiemBLL();
        dsDiaDiemTourBLL = new DsDiaDiemTourBLL();
        this.idTour = Long.parseLong(idTour);
        setComboBox(comboBoxTinh, getTinhItems());
        comboBoxTinh = myComboBox(comboBoxTinh, new Color(14,142,233));
        loadTableDiaDiem();
        this.diaDiemList = getDiaDiem_TourList();
        setTableDiaDiemTour(diaDiemList);
        tblDiaDiem_Tour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        tblDiaDiem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
        headerColor(14,142,233,tblDiaDiem);
        
    }

    public PopUpTableDiaDiemGUI() {
        initComponents();     
        
    }
    
    public List<DiaDiemDTO> getDiaDiem_TourList()
    {
        return dsDiaDiemTourBLL.findByIdTour(this.idTour);
    }
    
    public void setTableDiaDiemTour(List<DiaDiemDTO> list)
    {      
        model = new DefaultTableModel(columnNames,0);
        for (int i = 0; i < list.size(); i++) {
        model.addRow(new Object[]    {      
                                         String.valueOf(list.get(i).getId()),
                                         String.valueOf(list.get(i).getTenDiaDiem())
                                         
                                     });
        tblDiaDiem_Tour.setModel(model);
        }
        headerColor(14,142,233,tblDiaDiem_Tour);
    }
    
    public void loadTableDiaDiem()
    {
        String selectedTinh = comboBoxTinh.getSelectedItem().toString();
        Long idTinh = Long.parseLong(selectedTinh.substring(0, selectedTinh.indexOf(" - ")));
        tblDiaDiem.setModel(new DiaDiemTableLoaderUtil().setTable(diaDiemBLL.findByIdTinh(idTinh), columnNames));
        headerColor(14,142,233,tblDiaDiem);
    }
    public void initEmptyTableNhanVienDoan()
    {
          
          model = new DefaultTableModel(columnNames,0);
          tblDiaDiem_Tour.setModel(model);
          headerColor(14,142,233,tblDiaDiem_Tour);
    }
    
    public void setTableDiaDiemTour(ArrayList<DiaDiemDTO> list)
    {      
        model = new DefaultTableModel(columnNames,0);
        for (int i = 0; i < list.size(); i++) {
        model.addRow(new Object[]    {      
                                         String.valueOf(list.get(i).getId()),
                                         String.valueOf(list.get(i).getTenDiaDiem())
                                          
                                     });
        tblDiaDiem_Tour.setModel(model);
        }
        headerColor(14,142,233,tblDiaDiem_Tour);
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getTinhItems() {
        List<TinhDTO> tinhLists = tinhBLL.findAll();
        String[] tinhItems = new String[tinhLists.size()];
        int index = 0;
        for(TinhDTO vt : tinhLists) {
            tinhItems[index] = vt.getId() + " - " + vt.getTenTinh();
            ++ index;
        }
        return tinhItems;
    }
    
    public String getTinhItemName(TinhDTO tinh) {
        return tinh.getId() + " - " + tinh.getTenTinh();
    }
    
    private boolean save(){
        try{
            DsDiaDiemTourDTO diaDiemTour;       
            if(dsDiaDiemTourBLL.findByIdTour(idTour) != null)
                dsDiaDiemTourBLL.deleteByIdTour(idTour);
            for (int i = 0; i < diaDiemList.size(); ++i) {
                diaDiemTour = new DsDiaDiemTourDTO(this.idTour, diaDiemList.get(i).getId(), i + 1);
                dsDiaDiemTourBLL.save(diaDiemTour);
            }
        } catch(Exception e)
        {
            return false;
        }
        return true;
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
        tblDiaDiem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiaDiem_Tour = new javax.swing.JTable();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        comboBoxTinh = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblTour_DiaDiem = new javax.swing.JLabel();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblDiaDiem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblDiaDiem);

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

        tblDiaDiem_Tour.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDiaDiem_Tour);

        btnLuu.setBackground(new java.awt.Color(14, 142, 233));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
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

        btnHuy.setBackground(new java.awt.Color(14, 142, 233));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/cancel_icon.png"))); // NOI18N
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

        btnThem.setBackground(new java.awt.Color(14, 142, 233));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(true);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(14, 142, 233));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/gui/popup/save_icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setOpaque(true);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        comboBoxTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTinhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Chọn Tỉnh:");

        lblTour_DiaDiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTour_DiaDiem.setText("Các Điểm Đến Của Tour:");

        btnUp.setBackground(new java.awt.Color(14, 142, 233));
        btnUp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUp.setForeground(new java.awt.Color(255, 255, 255));
        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/img/up_arrow_icon.png"))); // NOI18N
        btnUp.setBorder(null);
        btnUp.setContentAreaFilled(false);
        btnUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUp.setOpaque(true);
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        btnDown.setBackground(new java.awt.Color(14, 142, 233));
        btnDown.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDown.setForeground(new java.awt.Color(255, 255, 255));
        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/img/down_arrow_icon.png"))); // NOI18N
        btnDown.setBorder(null);
        btnDown.setContentAreaFilled(false);
        btnDown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDown.setOpaque(true);
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(comboBoxTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTour_DiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTour_DiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
       int rowindex = tblDiaDiem_Tour.getSelectedRow();
       if (rowindex >=0)
       {
        Long id = Long.parseLong(tblDiaDiem_Tour.getValueAt(rowindex,0).toString());
        for (int i = 0; i < diaDiemList.size(); i++)
        {
            if (id.equals(diaDiemList.get(i).getId()))
            diaDiemList.remove(diaDiemList.get(i));
        }
         model = new DefaultTableModel(columnNames,0);
         for (int i = 0; i < diaDiemList.size(); i++) {
         model.addRow(new Object[]   {
                                         String.valueOf(diaDiemList.get(i).getId()),
                                         String.valueOf(diaDiemList.get(i).getTenDiaDiem())    
                                     });

         }

          if (diaDiemList.size() > 0) 
                 tblDiaDiem_Tour.setModel(model);
             else 
             {
                  model = new DefaultTableModel(columnNames,0);
                  tblDiaDiem_Tour.setModel(model);
             }
          headerColor(14,142,233,tblDiaDiem_Tour);
       }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 địa điểm để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
     
       diaDiemBLL = new DiaDiemBLL();
       int rowindex = tblDiaDiem.getSelectedRow();
       if (rowindex >=0)
       {
        Long id = Long.parseLong(tblDiaDiem.getValueAt(rowindex,0).toString());
        DiaDiemDTO selected = diaDiemBLL.findById(id);
        boolean duplicate = false;
        for (DiaDiemDTO diaDiem : diaDiemList)
        {
            if (diaDiem.getId().equals(selected.getId()))
            duplicate = true;
        }

         if (!duplicate)
         {
             diaDiemList.add(selected);
             model = new DefaultTableModel(columnNames,0);
             for (int i = 0; i < diaDiemList.size(); i++) {
             model.addRow(new Object[]   {
                                         String.valueOf(diaDiemList.get(i).getId()),
                                         String.valueOf(diaDiemList.get(i).getTenDiaDiem())                                   
                                     });
             tblDiaDiem_Tour.setModel(model);
             }  
         }
         headerColor(14,142,233,tblDiaDiem_Tour);      
       }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 địa điểm để thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThemActionPerformed

    private void comboBoxTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTinhActionPerformed
        // TODO add your handling code here:
        loadTableDiaDiem();
    }//GEN-LAST:event_comboBoxTinhActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
        
        int rowindex = tblDiaDiem_Tour.getSelectedRow();
        if (rowindex > 0)
            Collections.swap(diaDiemList, rowindex, rowindex-1 );
        model = new DefaultTableModel(columnNames,0);
             for (int i = 0; i < diaDiemList.size(); i++) {
             model.addRow(new Object[]   {
                                         String.valueOf(diaDiemList.get(i).getId()),
                                         String.valueOf(diaDiemList.get(i).getTenDiaDiem())                                   
                                     });
             tblDiaDiem_Tour.setModel(model);
             }
        headerColor(14,142,233,tblDiaDiem_Tour);
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        // TODO add your handling code here:
        int rowindex = tblDiaDiem_Tour.getSelectedRow();
        if (rowindex != diaDiemList.size()-1 && rowindex >= 0)
            Collections.swap(diaDiemList, rowindex, rowindex+1 );
        model = new DefaultTableModel(columnNames,0);
             for (int i = 0; i < diaDiemList.size(); i++) {
             model.addRow(new Object[]   {
                                         String.valueOf(diaDiemList.get(i).getId()),
                                         String.valueOf(diaDiemList.get(i).getTenDiaDiem())                                   
                                     });
             tblDiaDiem_Tour.setModel(model);
             }
        headerColor(14,142,233,tblDiaDiem_Tour);
    }//GEN-LAST:event_btnDownActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpTableDiaDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpTableDiaDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpTableDiaDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpTableDiaDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpTableDiaDiemGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUp;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> comboBoxTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTour_DiaDiem;
    private javax.swing.JTable tblDiaDiem;
    private javax.swing.JTable tblDiaDiem_Tour;
    // End of variables declaration//GEN-END:variables
}
