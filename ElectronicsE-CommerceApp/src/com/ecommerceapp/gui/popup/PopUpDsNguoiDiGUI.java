/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.ecommerceapp.bll.IDoanBLL;
import com.ecommerceapp.bll.IDsKhachDoanBLL;
import com.ecommerceapp.bll.IDsNhanVienDoanBLL;
import com.ecommerceapp.bll.IKhachHangBLL;
import com.ecommerceapp.bll.ITourBLL;
import com.ecommerceapp.bll.IVaiTroBLL;
import com.ecommerceapp.bll.IVaiTroNhanVienDoanBLL;
import com.ecommerceapp.bll.impl.DoanBLL;
import com.ecommerceapp.bll.impl.DsKhachDoanBLL;
import com.ecommerceapp.bll.impl.DsNhanVienDoanBLL;
import com.ecommerceapp.bll.impl.KhachHangBLL;
import com.ecommerceapp.bll.impl.TourBLL;
import com.ecommerceapp.bll.impl.VaiTroBLL;
import com.ecommerceapp.bll.impl.VaiTroNhanVienDoanBLL;
import com.ecommerceapp.dto.DoanDTO;
import com.ecommerceapp.dto.DsKhachDoanDTO;
import com.ecommerceapp.dto.DsNhanVienDoanDTO;
import com.ecommerceapp.dto.KhachHangDTO;
import com.ecommerceapp.dto.NhanVienDTO;
import com.ecommerceapp.dto.TourDTO;
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
import java.awt.Font;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Hi
 */
public class PopUpDsNguoiDiGUI extends javax.swing.JFrame {
    private String action;
    private DoanDTO doan = null;
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
    private IKhachHangBLL khachHangBLL;
    private IDsKhachDoanBLL dsKhachDoanBLL;
    private IDsNhanVienDoanBLL dsNhanVienDoanBLL;
    private IVaiTroNhanVienDoanBLL vaiTroNhanVienDoanBLL;
    private ArrayList<KhachHangDTO> listKhach = null;
    private ArrayList<KhachHangDTO> listDeletedKhach = null;
    private ArrayList<NhanVienDTO> listNhanVien = null;
    private ArrayList<NhanVienDTO> listDeletedNhanVien = null;
    private IVaiTroBLL vaiTroBLL;
    private PopUpTableKhachGUI popUpKhach;
    private PopUpTableNhanVienGUI popUpNhanVien;
    
    public PopUpDsNguoiDiGUI(String action) {
        initComponents();
        this.action = action;
        dsKhachDoanBLL = new DsKhachDoanBLL();
        dsNhanVienDoanBLL = new DsNhanVienDoanBLL();
        vaiTroNhanVienDoanBLL = new VaiTroNhanVienDoanBLL();
        listDeletedKhach = new ArrayList<>();
        listDeletedNhanVien = new ArrayList<>();
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        setComboBoxDoan();
        setListKhachDoan();
        setListNhanVienDoan();
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        comboBoxDoan = myComboBox(comboBoxDoan, new Color(14,142,233));
        this.setVisible(true);    
    }
    
    public PopUpDsNguoiDiGUI(String action, DoanDTO doan) {
        initComponents(); 
        this.action = action;  
        this.doan = doan;
        dsKhachDoanBLL = new DsKhachDoanBLL();
        dsNhanVienDoanBLL = new DsNhanVienDoanBLL();
        vaiTroNhanVienDoanBLL = new VaiTroNhanVienDoanBLL();
        listDeletedKhach = new ArrayList<>();
        listDeletedNhanVien = new ArrayList<>();
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
      
        this.setVisible(true);    
    }
    
    public void addListKhachHang(ArrayList list)
    {   
        String rs = "" ;
        listKhach = list;
        for (KhachHangDTO khach : listKhach)
        {
           rs += khach.getId()+" - "+khach.getHo() +" "+ khach.getTen();
           rs += "\n";
        }
        txtKhachHangList.setText(rs);
    }
    
    public void addDeletedKhach(KhachHangDTO deleted)
    {
       boolean duplicate = false; 
       for (int i = 0; i < listDeletedKhach.size(); i++)
           if (listDeletedKhach.get(i).getId().equals(deleted.getId()))
           duplicate = true;
       if (!duplicate)
      listDeletedKhach.add(deleted);         
    }
    
    public void addDeletedNhanVien(NhanVienDTO deleted)
    {
       boolean duplicate = false; 
       for (int i = 0; i < listDeletedNhanVien.size(); i++)
           if (listDeletedNhanVien.get(i).getId().equals(deleted.getId()))
           duplicate = true;
       if (!duplicate)
      listDeletedNhanVien.add(deleted);         
    }
    
    public void addListNhanVien(ArrayList list)
    {   
        vaiTroBLL = new VaiTroBLL();
        String rs = "" ;
        listNhanVien = list;
        for (NhanVienDTO nhanVien : listNhanVien)
        {
           rs += nhanVien.getHo() +" "+ nhanVien.getTen() + " - "  + vaiTroBLL.findById(nhanVien.getIdVaiTro()).getTenVaiTro();
           rs += "\n";
        }
        txtNhanVienList.setText(rs);
    }
     public boolean validateForm() 
    {   
         return true;
    }
    
    private boolean save() {
        boolean result;
        DsKhachDoanDTO khachDoan;
        DsNhanVienDoanDTO nhanVienDoan;
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        Integer amountOfLists = listNhanVien.size() + listKhach.size();
        
        
        result = modifyNhanVienList(dsNhanVienDoanBLL.findByIdDoan(idDoan), listNhanVien, idDoan);
        if (result == false)
            return false;
        
        result = modifyKhachHangList(dsKhachDoanBLL.findByIdDoan(idDoan), listKhach, idDoan);
        if (result == false)
            return false;
        
        doanBLL.updateAmount(idDoan, amountOfLists);
        
        return result;
    }
    
    public boolean modifyNhanVienList(List<NhanVienDTO> originalList, List<NhanVienDTO> modifiedList, Long idDoan) {
        List<NhanVienDTO> newNhanVienList = new ArrayList<>();
        List<NhanVienDTO> deletedNhanVienList = originalList;
        
//        Find new nhan vien and deleted nhan vien to list
        for(NhanVienDTO nhanVien : modifiedList) {
            if (originalList.contains(nhanVien)) {
                deletedNhanVienList.remove(nhanVien);
            } else {
                newNhanVienList.add(nhanVien);
            }
        }
        
//        Save and delete nhan vien into dsNhanVienDoan
        if (!newNhanVienList.isEmpty()) {
            for (NhanVienDTO nhanVien : newNhanVienList) {
                Long savedId = dsNhanVienDoanBLL.save(new DsNhanVienDoanDTO(idDoan, nhanVien.getId()));
                if (savedId == null) 
                    return false;
            }
        }
        
        if (!deletedNhanVienList.isEmpty()) {
            for (NhanVienDTO nhanVien : deletedNhanVienList) {
                try {
                    dsNhanVienDoanBLL.deleteByIdDoanAndIdNhanVien(idDoan, nhanVien.getId());
                } catch(Exception e) {
                    e.printStackTrace();
                    return false;
                }  
            }
        }
        
        return true;
    }
    
    public boolean modifyKhachHangList(List<KhachHangDTO> originalList, List<KhachHangDTO> modifiedList, Long idDoan) {
        List<KhachHangDTO> newKhachHangList = new ArrayList<>();
        List<KhachHangDTO> deletedKhachHangList = originalList;
        
//        Find new khach hang and deleted khach hang to list
        for(KhachHangDTO khachHang : modifiedList) {
            if (originalList.contains(khachHang)) {
                deletedKhachHangList.remove(khachHang);
            } else {
                newKhachHangList.add(khachHang);
            }
        }
        
//        Save and delete khach hang into dsKhachDoan
        if (!newKhachHangList.isEmpty()) {
            for (KhachHangDTO khachHang : newKhachHangList) {
                Long savedId = dsKhachDoanBLL.save(new DsKhachDoanDTO(idDoan, khachHang.getId()));
                if (savedId == null) 
                    return false;
            }
        }
        
        if (!deletedKhachHangList.isEmpty()) {
            for (KhachHangDTO khachHang : deletedKhachHangList) {
                try {
                    dsKhachDoanBLL.deleteByIdDoanAndIdKhachHang(idDoan, khachHang.getId());
                } catch(Exception e) {
                    e.printStackTrace();
                    return false;
                }  
            }
        }
        
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
    
    public void setListKhachDoan()
    {
       
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        listKhach = dsKhachDoanBLL.findByIdDoan(idDoan);
        addListKhachHang(listKhach);
    }
    
    public void setListNhanVienDoan()
    {
        dsNhanVienDoanBLL = new DsNhanVienDoanBLL();
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        listNhanVien = dsNhanVienDoanBLL.findByIdDoan(idDoan);
        addListNhanVien(listNhanVien);
    }
    
    public PopUpDsNguoiDiGUI() {
        initComponents();
        CustomWindow();
       
    }
    
    public void cancelNhanVienPopUp()
    {   
        setListNhanVienDoan();
    }
    
    public void cancelKhachPopUp()
    {    
        setListKhachDoan();
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
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateNgayKetThuc = new javax.swing.JLabel();
        lblValidateNgayKhoiHanh = new javax.swing.JLabel();
        lblValidateTenDoan = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNhanVienList = new javax.swing.JTextArea();
        btnChonNhanVien = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtKhachHangList = new javax.swing.JTextArea();
        btnChonKhachHang = new javax.swing.JButton();
        comboBoxTour = new javax.swing.JComboBox<>();
        comboBoxDoan = new javax.swing.JComboBox<>();

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
                .addContainerGap(633, Short.MAX_VALUE)
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

        lblValidateNgayKetThuc.setPreferredSize(new java.awt.Dimension(24, 24));

        txtNhanVienList.setColumns(20);
        txtNhanVienList.setRows(5);
        jScrollPane5.setViewportView(txtNhanVienList);

        btnChonNhanVien.setText("Chọn Nhân Viên");
        btnChonNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhanVienActionPerformed(evt);
            }
        });

        txtKhachHangList.setColumns(20);
        txtKhachHangList.setRows(5);
        jScrollPane6.setViewportView(txtKhachHangList);

        btnChonKhachHang.setText("Chọn Khách Hàng");
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenDoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateTenDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlBodyLayout.createSequentialGroup()
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlBodyLayout.createSequentialGroup()
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChonNhanVien))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnChonKhachHang))
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addGap(122, 122, 122)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(comboBoxTour, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxDoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(33, 33, 33)
                .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnChonKhachHang)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnChonNhanVien)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
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
    
    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        // TODO add your handling code here:
        if (this.popUpKhach == null) {
            String selectedDoan = comboBoxDoan.getSelectedItem().toString();
            Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
            LocalDate doanStartDate = doanBLL.findById(idDoan).getNgayKhoiHanh();
            
            popUpKhach = new PopUpTableKhachGUI(this, listKhach, listDeletedKhach, doanStartDate);
            this.popUpKhach.setVisible(true);
            this.popUpKhach.center();
        } else {
            this.popUpKhach.toFront();
            this.popUpKhach.center();
        }
        this.popUpKhach.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
             popUpKhach = null;         
        }
    });
         
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void btnChonNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhanVienActionPerformed
        if (this.popUpNhanVien == null) {
            String selectedDoan = comboBoxDoan.getSelectedItem().toString();
            Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
            LocalDate doanStartDate = doanBLL.findById(idDoan).getNgayKhoiHanh();
            popUpNhanVien = new PopUpTableNhanVienGUI(this, listNhanVien, listDeletedNhanVien, doanStartDate);
            popUpNhanVien.setVisible(true);
            this.popUpNhanVien.center();
        } else {
            this.popUpNhanVien.toFront();
            this.popUpNhanVien.center();
        }
        
        this.popUpNhanVien.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
             popUpNhanVien = null;
        }
    });
    }//GEN-LAST:event_btnChonNhanVienActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            boolean result = false;
            if(this.action.equals("POST")) {
                result = save();
                if(result) {

                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void comboBoxTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTourActionPerformed
        // TODO add your handling code here:
        setComboBoxDoan();
        setListKhachDoan();
        setListNhanVienDoan();
    }//GEN-LAST:event_comboBoxTourActionPerformed

    private void comboBoxTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTourItemStateChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_comboBoxTourItemStateChanged

    private void comboBoxDoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDoanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDoanItemStateChanged

    private void comboBoxDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDoanActionPerformed
        // TODO add your handling code here:
        setListKhachDoan();
        setListNhanVienDoan();
    }//GEN-LAST:event_comboBoxDoanActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpDsNguoiDiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpDsNguoiDiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpDsNguoiDiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpDsNguoiDiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpDsNguoiDiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonNhanVien;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxDoan;
    private javax.swing.JComboBox<String> comboBoxTour;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblChonTour;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTenDoan;
    private javax.swing.JLabel lblValidateNgayKetThuc;
    private javax.swing.JLabel lblValidateNgayKhoiHanh;
    private javax.swing.JLabel lblValidateTenDoan;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextArea txtKhachHangList;
    private javax.swing.JTextArea txtNhanVienList;
    // End of variables declaration//GEN-END:variables
}
