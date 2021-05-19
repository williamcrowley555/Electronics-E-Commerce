/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.toedter.calendar.JTextFieldDateEditor;
import com.tourdulich.bll.IChiPhiDoanBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IDichVuBLL;
import com.tourdulich.bll.IDoanBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.ITourBLL;
import com.tourdulich.bll.impl.ChiPhiDoanBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.DichVuBLL;
import com.tourdulich.bll.impl.DoanBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.bll.impl.TourBLL;
import com.tourdulich.dto.ChiPhiDoanDTO;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DichVuDTO;
import com.tourdulich.dto.DoanDTO;
import com.tourdulich.dto.TinhDTO;
import com.tourdulich.dto.TinhDTO;
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
import com.tourdulich.util.ImageUtil;
import com.tourdulich.util.InputValidatorUtil;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
public class PopUpChiPhiGUI extends javax.swing.JFrame {
   
    private String action;
    private DiaDiemDTO diaDiem = null;
    private IDichVuBLL dichVuBLL;
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
    private ChiPhiDoanDTO chiPhiDoan;
    private IChiPhiDoanBLL chiPhiDoanBLL;
    
    public PopUpChiPhiGUI(String action) {
        initComponents();        
        this.action = action;   
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        dichVuBLL = new DichVuBLL();
        chiPhiDoan = new ChiPhiDoanDTO();
        chiPhiDoanBLL = new ChiPhiDoanBLL();
        setComboBox(comboBoxTour, getTourItems());
        setComboBox(comboBoxDoan, getDoanItems());
        setComboBox(comboBoxDichVu, getDichVuItems());
        CustomWindow();    
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        comboBoxDoan = myComboBox(comboBoxDoan, new Color(14,142,233));
        comboBoxDichVu = myComboBox(comboBoxDichVu, new Color(14,142,233));
        DCNgayHoaDon.getComponentDateTextField().setEditable(false);
        setComboBoxDoan();
        this.setVisible(true);   
        
    }
    
    public PopUpChiPhiGUI(String action, ChiPhiDoanDTO chiPhiDoan) {
        initComponents();
        this.action = action;  
        this.chiPhiDoan = chiPhiDoan;
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        dichVuBLL = new DichVuBLL();
        chiPhiDoanBLL = new ChiPhiDoanBLL();
        
        setComboBox(comboBoxTour, getTourItems());
        setComboBox(comboBoxDoan, getDoanItems());
        
        comboBoxTour.setEnabled(false);
        comboBoxDoan.setEnabled(false);
        comboBoxDichVu = myComboBox(comboBoxDichVu, new Color(14,142,233));
        setComboBox(comboBoxDichVu, getDichVuItems());
        CustomWindow();
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        comboBoxDoan = myComboBox(comboBoxDoan, new Color(14,142,233));
        setLabelText(chiPhiDoan);
        DCNgayHoaDon.getComponentDateTextField().setEditable(false);
        this.setVisible(true);    
    }

    public void setLabelText(ChiPhiDoanDTO chiPhiDoan)
    {
        txtHoaDon.setText(chiPhiDoan.getHoaDon());
        DCNgayHoaDon.setDate(chiPhiDoan.getNgayHoaDon());
        txtSoTien.setText(chiPhiDoan.getChiPhi().toString());
        comboBoxDoan.setSelectedItem(getDoanItemName(doanBLL.findById(chiPhiDoan.getIdDoan())));
        comboBoxDichVu.setSelectedItem(getDichVuItemName(dichVuBLL.findById(chiPhiDoan.getIdDichVu())));
    }
    public boolean validateForm() 
    {   
        boolean hoaDon, chiPhi, ngayHD; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/tourdulich/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/tourdulich/img/error.png"));
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        LocalDate ngayKhoiHanh;
        LocalDate ngayKetThuc;
        if (InputValidatorUtil.isValidInvoiceNumber(txtHoaDon.getText()).isEmpty())  
        {
            hoaDon = true;
            lblValidateHoaDon.setIcon(iconCheck);
            lblValidateHoaDon.setToolTipText(null);
        } else {
            hoaDon = false;
            lblValidateHoaDon.setIcon(iconError);
            lblValidateHoaDon.setToolTipText(InputValidatorUtil.isValidInvoiceNumber(txtHoaDon.getText()));
        }   

        if (InputValidatorUtil.isVailidNumber(txtSoTien.getText(), 1000, 1000000000).isEmpty())  
        {
            chiPhi = true;
            lblValidateChiPhi.setIcon(iconCheck);
            lblValidateChiPhi.setToolTipText(null);
        } else {
            chiPhi = false;
            lblValidateChiPhi.setIcon(iconError);
            lblValidateChiPhi.setToolTipText(InputValidatorUtil.isVailidNumber(txtSoTien.getText(), 1000, 1000000000));
        }
        
        ngayKhoiHanh = doanBLL.findById(idDoan).getNgayKhoiHanh();
        ngayKetThuc = doanBLL.findById(idDoan).getNgayKetThuc();
        LocalDate ngayHoaDon = DCNgayHoaDon.getDate();
        if (ngayHoaDon != null)
        {
            if (ngayHoaDon.isAfter(ngayKhoiHanh.minusDays(1)) && ngayHoaDon.isBefore(ngayKetThuc.plusDays(1)))
            {
                ngayHD = true;
                lblValidateNgayHoaDon.setIcon(iconCheck);
                lblValidateNgayHoaDon.setToolTipText(null);
            }
            else {
                ngayHD = false;
                lblValidateNgayHoaDon.setIcon(iconError);
                lblValidateNgayHoaDon.setToolTipText("Ngày hóa đơn không hợp lệ");
            }
        } else {
                ngayHD = false;
                lblValidateNgayHoaDon.setIcon(iconError);
                lblValidateNgayHoaDon.setToolTipText("Ngày hóa đơn không được để trống");
        }

        if (hoaDon && chiPhi && ngayHD)
        return true;
        else return false;
       
    }
    private ChiPhiDoanDTO getFormInfo() throws IOException {
        ChiPhiDoanDTO chiPhiDoan = new ChiPhiDoanDTO();
        if(this.chiPhiDoan != null) {
            chiPhiDoan.setId(this.chiPhiDoan.getId());
        }
        String selectedDoan = comboBoxDoan.getSelectedItem().toString();
        Long idDoan = Long.parseLong(selectedDoan.substring(0, selectedDoan.indexOf(" - ")));
        chiPhiDoan.setIdDoan(idDoan);
        
        String selectedDichVu = comboBoxDichVu.getSelectedItem().toString();
        Long idDichVu = Long.parseLong(selectedDichVu.substring(0, selectedDichVu.indexOf(" - ")));
        chiPhiDoan.setIdDichVu(idDichVu);
        
        chiPhiDoan.setHoaDon(txtHoaDon.getText());
        chiPhiDoan.setNgayHoaDon(DCNgayHoaDon.getDate());
        chiPhiDoan.setChiPhi(Integer.valueOf(txtSoTien.getText()));
        
        return chiPhiDoan;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public PopUpChiPhiGUI() {
        initComponents();
        CustomWindow();
    }
   
    public void CustomWindow()
    {   
        Color flatBlue = new Color(14,142,233);  
       
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
        
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
    
     public void setComboBoxDoan()
    {
        doanBLL = new DoanBLL();
        String selectedTour = comboBoxTour.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
        setComboBox(comboBoxDoan, getDoanItems(idTour));
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
     
    public String[] getDoanItems() {
        List<DoanDTO> doanLists = doanBLL.findAll();
        String[] doanItems = new String[doanLists.size()];
        int index = 0;
        for(DoanDTO d : doanLists) {
            doanItems[index] = d.getId() + " - " + d.getTenDoan();
            ++ index;
        }
        return doanItems;
    }
     
    public String[] getDichVuItems() {
        List<DichVuDTO> dichVuLists = dichVuBLL.findAll();
        String[] dichVuItems = new String[dichVuLists.size()];
        int index = 0;
        for(DichVuDTO vt : dichVuLists) {
            dichVuItems[index] = vt.getId() + " - " + vt.getTenDichVu();
            ++ index;
        }
        return dichVuItems;
    }
    
    public String getDichVuItemName(DichVuDTO dichVu) {
        return dichVu.getId() + " - " + dichVu.getTenDichVu();
    }
    
    public String getDoanItemName(DoanDTO doan) {
        return doan.getId() + " - " + doan.getTenDoan();
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
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateHoaDon = new javax.swing.JLabel();
        comboBoxTour = new javax.swing.JComboBox<>();
        lblChonTour = new javax.swing.JLabel();
        comboBoxDoan = new javax.swing.JComboBox<>();
        lblTenDoan = new javax.swing.JLabel();
        lblTenDoan1 = new javax.swing.JLabel();
        comboBoxDichVu = new javax.swing.JComboBox<>();
        DCNgayHoaDon = new com.github.lgooddatepicker.components.DatePicker();
        lblTenDoan2 = new javax.swing.JLabel();
        lblTenDoan3 = new javax.swing.JLabel();
        txtHoaDon = new javax.swing.JTextField();
        lblTenDoan4 = new javax.swing.JLabel();
        txtSoTien = new javax.swing.JTextField();
        lblValidateNgayHoaDon = new javax.swing.JLabel();
        lblValidateChiPhi = new javax.swing.JLabel();

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
                .addContainerGap(432, Short.MAX_VALUE)
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

        lblChonTour.setText("Tên Tour:");

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

        lblTenDoan.setText("Tên Đoàn:");

        lblTenDoan1.setText("Loại Dịch Vụ:");

        comboBoxDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxDichVu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxDichVuItemStateChanged(evt);
            }
        });
        comboBoxDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDichVuActionPerformed(evt);
            }
        });

        lblTenDoan2.setText("Ngày hóa đơn:");

        lblTenDoan3.setText("Hóa Đơn:");

        txtHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoaDonActionPerformed(evt);
            }
        });

        lblTenDoan4.setText("Chi Phí:");

        txtSoTien.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtSoTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTienActionPerformed(evt);
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
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBodyLayout.createSequentialGroup()
                                        .addComponent(lblTenDoan4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblValidateChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboBoxDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenDoan)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addComponent(lblTenDoan2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblValidateNgayHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblTenDoan1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                                            .addComponent(lblTenDoan3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblValidateHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlBodyLayout.createSequentialGroup()
                                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBoxDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(224, 224, 224)))
                                    .addGap(2, 2, 2))
                                .addComponent(comboBoxTour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblChonTour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DCNgayHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(comboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTenDoan)
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(comboBoxDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTenDoan1)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTenDoan3))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblValidateHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lblTenDoan2))
                    .addComponent(lblValidateNgayHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DCNgayHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTenDoan4)
                            .addComponent(lblValidateChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            ChiPhiDoanDTO newChiPhi = null;
            try {
                newChiPhi = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpChiPhiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {
                Long newChiPhiId = chiPhiDoanBLL.save(newChiPhi);
                if(newChiPhiId != null) {

                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(this.action.equals("PUT")) {
                try {
                    chiPhiDoanBLL.update(newChiPhi);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void comboBoxTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTourItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTourItemStateChanged

    private void comboBoxTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTourActionPerformed
        // TODO add your handling code here:
        setComboBoxDoan();

    }//GEN-LAST:event_comboBoxTourActionPerformed

    private void comboBoxDoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDoanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDoanItemStateChanged

    private void comboBoxDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDoanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxDoanActionPerformed

    private void comboBoxDichVuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDichVuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDichVuItemStateChanged

    private void comboBoxDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDichVuActionPerformed

    private void txtHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoaDonActionPerformed

    private void txtSoTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpChiPhiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpChiPhiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpChiPhiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpChiPhiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpChiPhiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker DCNgayHoaDon;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxDichVu;
    private javax.swing.JComboBox<String> comboBoxDoan;
    private javax.swing.JComboBox<String> comboBoxTour;
    private javax.swing.JLabel lblChonTour;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTenDoan;
    private javax.swing.JLabel lblTenDoan1;
    private javax.swing.JLabel lblTenDoan2;
    private javax.swing.JLabel lblTenDoan3;
    private javax.swing.JLabel lblTenDoan4;
    private javax.swing.JLabel lblValidateChiPhi;
    private javax.swing.JLabel lblValidateHoaDon;
    private javax.swing.JLabel lblValidateNgayHoaDon;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtHoaDon;
    private javax.swing.JTextField txtSoTien;
    // End of variables declaration//GEN-END:variables
}
