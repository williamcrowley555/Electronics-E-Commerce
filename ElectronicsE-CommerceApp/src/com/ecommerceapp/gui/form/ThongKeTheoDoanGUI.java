/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.form;

import com.ecommerceapp.bll.IThongKeDoanBLL;
import com.ecommerceapp.bll.ITourBLL;
import com.ecommerceapp.bll.impl.ThongKeDoanBLL;
import com.ecommerceapp.bll.impl.TourBLL;
import com.ecommerceapp.dto.ThongKeDoanDTO;
import com.ecommerceapp.dto.TourDTO;
import com.ecommerceapp.gui.menu.MyComboBoxEditor;
import com.ecommerceapp.gui.menu.MyComboBoxRenderer;
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.util.DoanTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import com.ecommerceapp.util.ThongKeDoanTableLoaderUtil;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class ThongKeTheoDoanGUI extends javax.swing.JPanel {
    DefaultTableModel model;
    private ITourBLL tourBLL;
    private IThongKeDoanBLL thongKeDoanBLL;
    TableRowSorter<TableModel> rowSorter = null;
    String[] columnNames = {
                            "Id",
                            "Tên Đoàn",
                            "Số Khách",
                            "Giá Tour",
                            "Doanh Thu",
                            "Tổng Chi Phí",
                            "Lãi"};
    /**
     * Creates new form Panel1
     */
    public ThongKeTheoDoanGUI() {
        initComponents();
        thongKeDoanBLL = new ThongKeDoanBLL();
        tourBLL = new TourBLL();
        model = new DefaultTableModel(columnNames,0);
        tblThongKeDoan.setModel(model);
        setComboBox(comboBoxTour, getTourItems());
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        headerColor(14,142,233,tblThongKeDoan);
//        String selectedTour = comboBoxTour.getSelectedItem().toString();
//        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
//        loadTableData(idTour);
        loadTableData();
        headerColor(14,142,233,tblThongKeDoan);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
    
    public void loadTableData() {
        String selectedTour = comboBoxTour.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
        List<ThongKeDoanDTO> thongKeDoanList = thongKeDoanBLL.findByIdTour(idTour);
        tblThongKeDoan.setModel(new ThongKeDoanTableLoaderUtil().setTable(thongKeDoanList, this.columnNames)) ;
        setTotal(thongKeDoanList);
        this.rowSorter = TableSetupUtil.setTableFilter(tblThongKeDoan, txtTimKiem);
        headerColor(14,142,233,tblThongKeDoan);
    }
    
    public void setTotal(List<ThongKeDoanDTO> thongKeDoanList) {
        Long tongDoanhThu = 0L;
        Long tongChiPhi = 0L;
        Long tongLai = 0L;
        for (ThongKeDoanDTO thongKeDoan : thongKeDoanList) {
            tongDoanhThu += thongKeDoan.getDoanhThu();
            tongChiPhi += thongKeDoan.getTongChiPhi();
            tongLai += thongKeDoan.getLai();
        }
        
        lblTongDoanhThu.setText(tongDoanhThu.toString());
        lblTongChiPhi.setText(tongChiPhi.toString());
        lblTongLai.setText(tongLai.toString());
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
     
     public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
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

        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblChonTour = new javax.swing.JLabel();
        comboBoxTour = new javax.swing.JComboBox<>();
        lblTimKiem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblThongKeDoan = new javax.swing.JTable();
        lblTong = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        lblTongChiPhi = new javax.swing.JLabel();
        lblTongLai = new javax.swing.JLabel();
        lblTong4 = new javax.swing.JLabel();
        lblTong5 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(808, 150));

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(14, 142, 233)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText(" Thống Kê Theo Đoàn");

        lblChonTour.setText("Tên Tour:");

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

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tourdulich/img/search_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTimKiem))
                .addGap(16, 16, 16))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblThongKeDoan.setModel(new javax.swing.table.DefaultTableModel(
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
        scroll.setViewportView(tblThongKeDoan);

        lblTong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTong.setText("Tổng Doanh Thu:");

        lblTongDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongDoanhThu.setText("300000");

        lblTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongChiPhi.setText("200000");

        lblTongLai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongLai.setText("100000");

        lblTong4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTong4.setText("Tổng Chi Phí:");

        lblTong5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTong5.setText("Tổng Lãi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTong5)
                                .addGap(119, 119, 119)
                                .addComponent(lblTongLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTong)
                                .addGap(47, 47, 47)
                                .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTong4)
                                .addGap(80, 80, 80)
                                .addComponent(lblTongChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTong)
                    .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTong4)
                    .addComponent(lblTongChiPhi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTong5)
                    .addComponent(lblTongLai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void comboBoxTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTourItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTourItemStateChanged

    private void comboBoxTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTourActionPerformed
        // TODO add your handling code here:
        loadTableData();
    }//GEN-LAST:event_comboBoxTourActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTour;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblChonTour;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel lblTong4;
    private javax.swing.JLabel lblTong5;
    private javax.swing.JLabel lblTongChiPhi;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongLai;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblThongKeDoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
