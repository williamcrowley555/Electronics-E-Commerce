/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.github.lgooddatepicker.components.DatePicker;
import com.toedter.calendar.JTextFieldDateEditor;
import com.ecommerceapp.bll.IDiaDiemBLL;
import com.ecommerceapp.bll.IDiaDiemBLL;
import com.ecommerceapp.bll.IDoanBLL;
import com.ecommerceapp.bll.IGiaTourBLL;
import com.ecommerceapp.bll.IKhachHangBLL;
import com.ecommerceapp.bll.ITinhBLL;
import com.ecommerceapp.bll.ITinhBLL;
import com.ecommerceapp.bll.ITourBLL;
import com.ecommerceapp.bll.IVaiTroBLL;
import com.ecommerceapp.bll.impl.DiaDiemBLL;
import com.ecommerceapp.bll.impl.DiaDiemBLL;
import com.ecommerceapp.bll.impl.DoanBLL;
import com.ecommerceapp.bll.impl.GiaTourBLL;
import com.ecommerceapp.bll.impl.KhachHangBLL;
import com.ecommerceapp.bll.impl.TinhBLL;
import com.ecommerceapp.bll.impl.TinhBLL;
import com.ecommerceapp.bll.impl.TourBLL;
import com.ecommerceapp.bll.impl.VaiTroBLL;
import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.dto.DoanDTO;
import com.ecommerceapp.dto.GiaTourDTO;
import com.ecommerceapp.dto.KhachHangDTO;
import com.ecommerceapp.dto.NhanVienDTO;
import com.ecommerceapp.dto.TinhDTO;
import com.ecommerceapp.dto.TinhDTO;
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
import com.ecommerceapp.gui.others.TableRowTransferHandler;
import com.ecommerceapp.util.ImageUtil;
import com.ecommerceapp.util.InputValidatorUtil;
import com.ecommerceapp.util.KhachHangTableLoaderUtil;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class PopUpDoanGUI extends javax.swing.JFrame {
    private File selectedImg = null;
    private String action;
    private DoanDTO doan = null;
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
    private IGiaTourBLL giaTourBLL;
    private ArrayList<KhachHangDTO> listKhach = null;
    private ArrayList<NhanVienDTO> listNhanVien = null;
    private IVaiTroBLL vaiTroBLL;
    private PopUpTableChonGiaGUI popUp;
    private GiaTourDTO giaTour;
    
    
    public PopUpDoanGUI(String action) {
        initComponents();
        
        this.action = action;    
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        giaTourBLL = new GiaTourBLL();        
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        disableEditorDateChooser();
        this.setVisible(true);    
    }
    
    public PopUpDoanGUI(String action, DoanDTO doan) {
        initComponents();
        this.action = action;  
        this.doan = doan;
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        giaTourBLL = new GiaTourBLL();
        CustomWindow();
        setComboBox(comboBoxTour, getTourItems());
        comboBoxTour = myComboBox(comboBoxTour, new Color(14,142,233));
        setLabelText(doan);
        String selectedTour = comboBoxTour.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));     
        disableEditorDateChooser();
        this.setVisible(true);    
    }
     
    public void setLabelText(DoanDTO doan)
    {
        txtDoan.setText(doan.getTenDoan());
        DCNgayKhoiHanh.setDate(doan.getNgayKhoiHanh());
        DCNgayKetThuc.setDate(doan.getNgayKetThuc());
        txtGiaTour.setText(doan.getGiaTien().toString());
    }
    
    
    
    public void disableEditorDateChooser()
    {
        DCNgayKhoiHanh.getComponentDateTextField().setEditable(false);
        DCNgayKetThuc.getComponentDateTextField().setEditable(false);
    }
    
    
     public boolean validateForm() 
    {   
        
        boolean TenDoan, StartDate, EndDate, GiaTour = false; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/tourdulich/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/tourdulich/img/error.png"));
        
        if (InputValidatorUtil.isValidPattern(txtDoan.getText(), "[^A-Za-z0-9\\-\\s]", "Tên đoàn không hợp lệ").isEmpty())  
        {
            TenDoan = true;
            lblValidateTenDoan.setIcon(iconCheck);
            lblValidateTenDoan.setToolTipText(null);
        } else {
            TenDoan = false;
            lblValidateTenDoan.setIcon(iconError);
            lblValidateTenDoan.setToolTipText(InputValidatorUtil.isValidPattern(txtDoan.getText(), "[^A-Za-z0-9\\-\\s]", "Tên đoàn không hợp lệ"));
        }
        
        if (InputValidatorUtil.isValidStartDate(DCNgayKhoiHanh.getDate()).isEmpty())  
        {
            StartDate = true;
            lblValidateNgayKhoiHanh.setIcon(iconCheck);
            lblValidateNgayKhoiHanh.setToolTipText(null);
        } else {
            StartDate = false;
            lblValidateNgayKhoiHanh.setIcon(iconError);
            lblValidateNgayKhoiHanh.setToolTipText(InputValidatorUtil.isValidStartDate(DCNgayKhoiHanh.getDate()));
        }
        
        if (InputValidatorUtil.isValidEndDate(DCNgayKhoiHanh.getDate(),DCNgayKetThuc.getDate()).isEmpty())  
        {
            EndDate = true;
            lblValidateNgayKetThuc.setIcon(iconCheck);
            lblValidateNgayKetThuc.setToolTipText(null);
        } else {
            EndDate = false;
            lblValidateNgayKetThuc.setIcon(iconError);
            lblValidateNgayKetThuc.setToolTipText(InputValidatorUtil.isValidEndDate(DCNgayKhoiHanh.getDate(),DCNgayKetThuc.getDate()));
        }
        if (txtGiaTour.getText().equals(""))
        {
            GiaTour = false;
            MatteBorder border = new MatteBorder(2, 2, 2, 2, new Color(240,52,52));
            txtGiaTour.setBorder(border);
            txtGiaTour.setToolTipText("Giá tour không được để trống !");
                       
        } else {
            GiaTour = true;
            MatteBorder border = new MatteBorder(2, 2 , 2, 2, new Color(46,204,113));
            txtGiaTour.setBorder(border); 
        }
        if (TenDoan && StartDate && EndDate && GiaTour)
        return true;
        else return false;
       
    }
    
    private DoanDTO getFormInfo() throws IOException {
        DoanDTO doan = new DoanDTO();
        if(this.doan != null) {
            doan.setId(this.doan.getId());
        }
        doan.setTenDoan(txtDoan.getText().trim());
        doan.setNgayKhoiHanh(DCNgayKhoiHanh.getDate());
        doan.setNgayKetThuc(DCNgayKetThuc.getDate());
        doan.setGiaTien(Long.parseLong(txtGiaTour.getText()));
        String selectedTour = comboBoxTour.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
        doan.setIdTour(idTour);
        return doan;
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
    
    
    
    public void  setGiaTour(GiaTourDTO giaTour)
    {
        this.giaTour = giaTour;
        txtGiaTour.setText(this.giaTour.getGiaTien().toString());
    }
    
    public void resetGiaTour()
    {
        this.giaTour = null;
        txtGiaTour.setText("");
    }
    public PopUpDoanGUI() {
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
        comboBoxTour = myComboBox(comboBoxTour, new Color(240,240,240));
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
        txtDoan = new javax.swing.JTextField();
        lblChonTour = new javax.swing.JLabel();
        lblTenDoan = new javax.swing.JLabel();
        comboBoxTour = new javax.swing.JComboBox<>();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateNgayKetThuc = new javax.swing.JLabel();
        lblValidateNgayKhoiHanh = new javax.swing.JLabel();
        lblValidateTenDoan = new javax.swing.JLabel();
        DCNgayKhoiHanh = new com.github.lgooddatepicker.components.DatePicker();
        DCNgayKetThuc = new com.github.lgooddatepicker.components.DatePicker();
        lblNgayKetThuc = new javax.swing.JLabel();
        lblNgayKhoiHanh = new javax.swing.JLabel();
        btnChonGia = new javax.swing.JButton();
        txtGiaTour = new javax.swing.JTextField();

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

        txtDoan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoanActionPerformed(evt);
            }
        });

        lblChonTour.setText("Chọn Tour:");
        lblChonTour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblTenDoan.setText("Tên Đoàn:");
        lblTenDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboBoxTour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxTour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTourActionPerformed(evt);
            }
        });

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

        DCNgayKhoiHanh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DCNgayKhoiHanhFocusLost(evt);
            }
        });
        DCNgayKhoiHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DCNgayKhoiHanhMouseExited(evt);
            }
        });
        DCNgayKhoiHanh.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                DCNgayKhoiHanhInputMethodTextChanged(evt);
            }
        });
        DCNgayKhoiHanh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DCNgayKhoiHanhPropertyChange(evt);
            }
        });

        lblNgayKetThuc.setText("Ngày Kết Thúc");
        lblNgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblNgayKhoiHanh.setText("Ngày Khởi Hành:");
        lblNgayKhoiHanh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnChonGia.setText("Chọn Giá");
        btnChonGia.setBackground(new java.awt.Color(14, 142, 233));
        btnChonGia.setBorder(null);
        btnChonGia.setContentAreaFilled(false);
        btnChonGia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChonGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonGia.setForeground(new java.awt.Color(255, 255, 255));
        btnChonGia.setOpaque(true);
        btnChonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonGiaActionPerformed(evt);
            }
        });

        txtGiaTour.setEditable(false);
        txtGiaTour.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtGiaTour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGiaTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblChonTour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblNgayKhoiHanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DCNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenDoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateTenDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                            .addComponent(lblNgayKetThuc)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaTour, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDoan, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboBoxTour, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createSequentialGroup()
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DCNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(comboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblChonTour, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenDoan)
                    .addComponent(lblValidateTenDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValidateNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(DCNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnChonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiaTour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblValidateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(DCNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoanActionPerformed
       
    }//GEN-LAST:event_txtDoanActionPerformed
    
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            DoanDTO newDoan = null;
            try {
                newDoan = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpDoanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {           
                    Long newDoanId = doanBLL.save(newDoan);
                    if(newDoanId != null) {

                        JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
            } else if(this.action.equals("PUT")) {
                try {    
                    doanBLL.update(newDoan);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void comboBoxTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTourActionPerformed
       resetGiaTour();
      DCNgayKhoiHanh.setDate(null);
      DCNgayKetThuc.setDate(null);
      
    }//GEN-LAST:event_comboBoxTourActionPerformed

    private void DCNgayKhoiHanhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DCNgayKhoiHanhPropertyChange
        
    }//GEN-LAST:event_DCNgayKhoiHanhPropertyChange

    private void DCNgayKhoiHanhInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_DCNgayKhoiHanhInputMethodTextChanged
        
    }//GEN-LAST:event_DCNgayKhoiHanhInputMethodTextChanged

    private void DCNgayKhoiHanhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DCNgayKhoiHanhFocusLost
        
    }//GEN-LAST:event_DCNgayKhoiHanhFocusLost

    private void DCNgayKhoiHanhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DCNgayKhoiHanhMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DCNgayKhoiHanhMouseExited

    private void btnChonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonGiaActionPerformed
       
        if (DCNgayKhoiHanh.getDate() == null || DCNgayKetThuc.getDate() == null)
        {   
             JOptionPane.showMessageDialog(this, "Hãy chọn ngày khởi hành và ngày kết thúc trước khi chọn giá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else 
        {      
            if (this.popUp == null) {
                String selectedTour = comboBoxTour.getSelectedItem().toString();
                Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
                this.popUp = new PopUpTableChonGiaGUI(this, idTour, DCNgayKhoiHanh.getDate(), DCNgayKetThuc.getDate());
                popUp.setVisible(true);
                popUp.center();
            } else {
                this.popUp.toFront();
                this.popUp.center();
            }
            popUp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                popUp = null;

            }
            });
        }
    }//GEN-LAST:event_btnChonGiaActionPerformed

    private void txtGiaTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTourActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpDoanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpDoanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpDoanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpDoanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpDoanGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker DCNgayKetThuc;
    private com.github.lgooddatepicker.components.DatePicker DCNgayKhoiHanh;
    private javax.swing.JButton btnChonGia;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxTour;
    private javax.swing.JLabel lblChonTour;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblNgayKhoiHanh;
    private javax.swing.JLabel lblTenDoan;
    private javax.swing.JLabel lblValidateNgayKetThuc;
    private javax.swing.JLabel lblValidateNgayKhoiHanh;
    private javax.swing.JLabel lblValidateTenDoan;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtDoan;
    private javax.swing.JTextField txtGiaTour;
    // End of variables declaration//GEN-END:variables
}
