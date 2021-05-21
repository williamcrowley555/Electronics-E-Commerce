/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.popup;

import com.toedter.calendar.JTextFieldDateEditor;
import com.tourdulich.bll.IBrandBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IDiaDiemBLL;
import com.tourdulich.bll.IProductBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.ITinhBLL;
import com.tourdulich.bll.impl.BrandBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.DiaDiemBLL;
import com.tourdulich.bll.impl.ProductBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.bll.impl.TinhBLL;
import com.tourdulich.dto.BrandDTO;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.ProductDTO;
import com.tourdulich.dto.TinhDTO;
import com.tourdulich.dto.TinhDTO;
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
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
public class PopUpProductGUI extends javax.swing.JFrame {
    private File selectedImg = null;
    private String action;
    private DiaDiemDTO diaDiem = null;
    private ProductDTO product = null;
    private IDiaDiemBLL diaDiemBLL;
    private ITinhBLL tinhBLL;
    private IBrandBLL brandBLL;
    private IProductBLL productBLL;
    public PopUpProductGUI(String action) {
        initComponents();
        
        this.action = action;    
        diaDiemBLL = new DiaDiemBLL();
        productBLL = new ProductBLL();
        tinhBLL = new TinhBLL();
        brandBLL = new BrandBLL();
        
        CustomWindow();
        myTextArea();
        setComboBox(comboBoxBrand, getBrandItems());
        comboBoxBrand = myComboBox(comboBoxBrand, new Color(77,77,77));
        
        
        this.setVisible(true);    
    }
    
    public PopUpProductGUI(String action, ProductDTO product) throws UnsupportedEncodingException {
        initComponents();
        this.action = action;  
        this.diaDiem = diaDiem;
        this.product = product;
        diaDiemBLL = new DiaDiemBLL();
        productBLL = new ProductBLL();
        brandBLL = new BrandBLL();
      
        CustomWindow();
        myTextArea();
        setComboBox(comboBoxBrand, getBrandItems());
        comboBoxBrand = myComboBox(comboBoxBrand, new Color(77,77,77));
        setLabelText(product);
        
       
        this.setVisible(true);    
    }
     
    public void setLabelText(ProductDTO product) throws UnsupportedEncodingException
    {
        txtName.setText(product.getName());
        txtPrice.setText(product.getPrice().toString());
        txtDescription.setText(product.getDescription());
        
       // txtDiaChi.setText(diaDiem.getDiaChi());
        
        if(product.getBase64Image()!= null) {
          // lblAnh.setIcon(ImageUtil.resizeImg(product.getImage(), lblAnh));
            
            byte[] decodedString = Base64.getDecoder().decode(new String(product.getBase64Image()).getBytes("UTF-8"));
            System.out.println(decodedString);
           // System.out.println(decodedString);
            lblAnh.setIcon(ImageUtil.resizeImg(decodedString, lblAnh));
            
           // lblAnh.setIcon(ImageUtil.resizeImg(product.getImage(), lblAnh));
           
           //System.out.println(product.getBase64Image());
           //lblAnh.setText(product.getBase64Image());
        }
        comboBoxBrand.setSelectedItem(getBrandItemName(brandBLL.findById(product.getBrandId())));
    }
    public boolean validateForm() 
    {   
        
        boolean Ten, DiaChi, GioiThieu; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/tourdulich/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/tourdulich/img/error.png"));
         
        
        if (InputValidatorUtil.isValidName(txtName.getText(), true).isEmpty())  
        {
            Ten = true;
            lblValidateTen.setIcon(iconCheck);
            lblValidateTen.setToolTipText(null);
        } else {
            Ten = false;
            lblValidateTen.setIcon(iconError);
            lblValidateTen.setToolTipText(InputValidatorUtil.isValidName(txtName.getText(), true));
        } 
        
        if (InputValidatorUtil.isValidAddress(txtDescription.getText()).isEmpty())  
        {
            GioiThieu = true;
            lblValidateGioiThieu.setIcon(iconCheck);
            lblValidateGioiThieu.setToolTipText(null);
        } else {
            GioiThieu = false;
            lblValidateGioiThieu.setIcon(iconError);
            lblValidateGioiThieu.setToolTipText(InputValidatorUtil.isValidName(txtDescription.getText(), true));
        } 
        
//        if (InputValidatorUtil.isValidAddress(txtDiaChi.getText()).isEmpty())  
//        {
//           DiaChi = true;
//           lblValidateDiaChi.setIcon(iconCheck);
//           lblValidateDiaChi.setToolTipText(null);
//        } else {
//           DiaChi = false;
//           lblValidateDiaChi.setIcon(iconError);
//        //   lblValidateDiaChi.setToolTipText(InputValidatorUtil.isValidAddress(txtDiaChi.getText()));
//        }
        
//        if (Ten && GioiThieu)
//        return true;
//        else return false;
        return true;
       
    }
    
    
    
    private ProductDTO getFormInfo() throws IOException {
        ProductDTO product = new ProductDTO();
        if(this.product != null) {
            product.setId(this.product.getId());
        }
        product.setName(txtName.getText().trim());
        product.setDescription(txtDescription.getText().trim());
        product.setPrice(Long.parseLong(txtPrice.getText().trim()));
        product.setQuantity(0);
       // diaDiem.setDiaChi(txtDiaChi.getText().trim());
        if (this.selectedImg != null) {
           
            String base64Image = Base64.getEncoder().encodeToString(ImageUtil.getByteArray(this.selectedImg));
            product.setBase64Image(base64Image);
        } else {
            if (this.product != null) {
                if(this.product.getBase64Image()!= null) {
                   // product.setImage(this.product.getImage());
                   product.setBase64Image(this.product.getBase64Image());
                }
            }
        }
        String selectedBrand = comboBoxBrand.getSelectedItem().toString();
        Long idBrand = Long.parseLong(selectedBrand.substring(0, selectedBrand.indexOf(" - ")));
        product.setBrandId(idBrand);
        return product;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getBrandItems() {
        List<BrandDTO> brandLists = brandBLL.findAll();
        String[] brandItems = new String[brandLists.size()];
        int index = 0;
        for(BrandDTO vt : brandLists) {
            brandItems[index] = vt.getId() + " - " + vt.getName();
            ++ index;
        }
        return brandItems;
    }
    
    public String getBrandItemName(BrandDTO brand) {
        return brand.getId() + " - " + brand.getName();
    }
    
    public PopUpProductGUI() {
        initComponents();
        CustomWindow();
    }
   
    public void CustomWindow()
    {   
        Color flatBlue = new Color(77,77,77);  
       // this.setSize(new Dimension(659,386));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
        comboBoxBrand = myComboBox(comboBoxBrand, new Color(240,240,240));
    }
    
    public void myTextArea()
    {
//        txtDiaChi.setWrapStyleWord(true);
  //      txtDiaChi.setLineWrap(true);
        
        txtDescription.setWrapStyleWord(true);
        txtDescription.setLineWrap(true);
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
        lblAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        lblTenDiaDiem = new javax.swing.JLabel();
        lblTinh = new javax.swing.JLabel();
        comboBoxBrand = new javax.swing.JComboBox<>();
        lblGioiThieu = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateTen = new javax.swing.JLabel();
        lblValidateDiaChi = new javax.swing.JLabel();
        lblValidateGioiThieu = new javax.swing.JLabel();
        AreaScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblTinh1 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(77, 77, 77));
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

        lblAnh.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        btnChonAnh.setBackground(new java.awt.Color(204, 204, 204));
        btnChonAnh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.setContentAreaFilled(false);
        btnChonAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChonAnh.setOpaque(true);
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        lblTenDiaDiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenDiaDiem.setText("Tên sản phẩm:");

        lblTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinh.setText("Nhãn hiệu:");

        comboBoxBrand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxBrand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBrandActionPerformed(evt);
            }
        });

        lblGioiThieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGioiThieu.setText("Mô tả:");

        btnLuu.setBackground(new java.awt.Color(77, 77, 77));
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

        btnHuy.setBackground(new java.awt.Color(77, 77, 77));
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

        lblValidateTen.setPreferredSize(new java.awt.Dimension(24, 24));

        AreaScrollPane1.setToolTipText("");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescription.setRows(5);
        txtDescription.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane1.setViewportView(txtDescription);

        lblTinh1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinh1.setText("Giá:");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrice.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValidateGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTinh1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))))
                    .addComponent(lblTinh)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenDiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblGioiThieu)
                    .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValidateDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenDiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTinh)
                                    .addComponent(comboBoxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTinh1)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)))
                        .addGap(18, 18, 18)
                        .addComponent(lblGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            ProductDTO newProduct = null;
            try {
                newProduct = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpProductGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {           
                    Long newProcutId = productBLL.save(newProduct);
                    if(newProcutId != null) {

                        JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
            } else if(this.action.equals("PUT")) {
                try {    
                    productBLL.update(newProduct);
                    
                    System.out.println(newProduct);
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
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void comboBoxBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxBrandActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        selectedImg = ImageUtil.showJFileChooser(lblAnh);
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpProductGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpProductGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpProductGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpProductGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpProductGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane1;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxBrand;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTenDiaDiem;
    private javax.swing.JLabel lblTinh;
    private javax.swing.JLabel lblTinh1;
    private javax.swing.JLabel lblValidateDiaChi;
    private javax.swing.JLabel lblValidateGioiThieu;
    private javax.swing.JLabel lblValidateTen;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
