/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.toedter.calendar.JTextFieldDateEditor;
import com.ecommerceapp.bll.IBrandBLL;
import com.ecommerceapp.bll.IBrand_CatalogBLL;
import com.ecommerceapp.bll.ICatalogBLL;
import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.IInvoiceDetailBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.impl.BrandBLL;
import com.ecommerceapp.bll.impl.Brand_CatalogBLL;
import com.ecommerceapp.bll.impl.CatalogBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceDetailBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.dto.BrandDTO;
import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.dto.InvoiceDetailDTO;
import com.ecommerceapp.dto.ProductDTO;
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
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.util.ImageUtil;
import com.ecommerceapp.util.InputValidatorUtil;
import com.ecommerceapp.util.InvoiceDetailTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import com.itextpdf.text.BaseColor;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;
import javax.swing.JFileChooser;
/**
 *
 * @author Hi
 */
public class popUpDInvoiceDetailGUI extends javax.swing.JFrame {
    private File selectedImg = null;
    private String action;
    private ProductDTO product = null;
    private InvoiceDTO invoice = null;
    private IBrandBLL brandBLL;
    private ICatalogBLL catalogBLL;
    private IProductBLL productBLL;
    private IBrand_CatalogBLL brand_catalogBLL;
    private IInvoiceBLL invoiceBLL;
    private IInvoiceDetailBLL invoiceDetailBLL;
    private IUserBLL userBLL;
    TableRowSorter<TableModel> rowSorter = null;
    List<ProductDTO> productList = new ArrayList<>();
    List<InvoiceDetailDTO> invoiceDetails = new ArrayList<>();
    DefaultTableModel model;
    Long total = 0L;
    Long userId = null;
    private PopUpTableChonUserGUI popUp = null;
    
    String[] columnNamesProduct = {
                            "Id",
                            "Tên sản phẩm",
                            "Số Lượng",
                            "Giá",
                            "Tổng",
                                          
    };
    
    public popUpDInvoiceDetailGUI(String action) {
        initComponents();
        
        this.action = action;    
        productBLL = new ProductBLL();
        brandBLL = new BrandBLL();
        catalogBLL = new CatalogBLL();
        brand_catalogBLL = new Brand_CatalogBLL();
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        CustomWindow();
        myTextArea();
        loadTableData();
        
        this.setVisible(true);    
    }
    
    public popUpDInvoiceDetailGUI(String action, InvoiceDTO invoice) throws UnsupportedEncodingException {
        initComponents();
        this.action = action;  
        this.invoice = invoice;
        productBLL = new ProductBLL();
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        userBLL = new UserBLL();
        CustomWindow();
        myTextArea();
        loadTableData();
        setLabelText(invoice);
       
        this.setVisible(true);    
    }
    
    public void loadTableData() {
        //tblDiaDiem.setModel(new DiaDiemTableLoaderUtil().setTable(diaDiemBLL.findAll(), this.columnNames)) ;
     
        List<ProductDTO> products = invoiceDetailBLL.findByIdInvoice(this.invoice.getId());
        for( int i = 0; i < products.size(); i++)
        {
            this.invoiceDetails.add(invoiceDetailBLL.findById(invoice.getId(), products.get(i).getId()));
        }
        tblInvoice_Product.setModel(new InvoiceDetailTableLoaderUtil().setTable(invoiceDetails, columnNamesProduct));
        headerColor(77,77,77,tblInvoice_Product);
        resizeColumnWidth(tblInvoice_Product);
    }
    
    public void headerColor(int r, int b, int g, JTable table)
    {
        Color color = new Color(r,b,g);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(color);
        headerRenderer.setForeground(color.WHITE);
        
        JTableHeader tab_header = table.getTableHeader();					//Get the table header			
			tab_header.setFont(new Font("Microsoft Yahei", Font.PLAIN, 15));	
			tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//Modify the height of the table header
			table.setFont(new Font("Microsoft Yahei", Font.PLAIN, 13));

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
        table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }       
         
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
    }
    
    public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 70; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width +1 , width);
        }
        if(width > 200)
            width=200;
        columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
     
    public void setUserInfo(Long id, String ho, String ten, String sdt,String diaChi)
    {
        this.userId = id; 
        txtLastName.setText(ho);
        txtFirstName.setText(ten);
        txtSDT.setText(sdt);
        txtAddress.setText(diaChi);
    }
    
    public void setLabelText(InvoiceDTO invoice) throws UnsupportedEncodingException
    {

        txtLastName.setText(invoice.getRecipientLastName());
        txtFirstName.setText(invoice.getRecipientFirstName());
        txtSDT.setText(invoice.getPhone());
        txtNote.setText(invoice.getNote());
        txtAddress.setText(invoice.getAddress());
        txtTong.setText(Long.toString(invoice.getTotal()));
        lblNgayTao.setText(invoice.getOrderDateFormat());
        UserDTO employee = userBLL.findById(invoice.getEmployeeId());
        if (employee == null)
            lblNhanVienTao.setText("(Khách mua online)");
        else
            lblNhanVienTao.setText(employee.getFullName());
    }
    
    private InvoiceDTO getFormInfo() throws IOException {
        InvoiceDTO invoice = new InvoiceDTO();
        if(this.invoice != null) {
            invoice.setId(this.invoice.getId());
        }
        System.out.println(LocalDate.now());
        invoice.setUserId(this.userId);
        invoice.setRecipientLastName(txtLastName.getText().trim());
        invoice.setRecipientFirstName(txtFirstName.getText().trim());
        invoice.setAddress(txtAddress.getText().trim());
        invoice.setNote(txtNote.getText());
        invoice.setStatus(null);
        invoice.setTotal(Long.parseLong(txtTong.getText()));
        invoice.setPhone(txtSDT.getText());
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        today.format(formatter);
        invoice.setOrderDate(today);
        
        return invoice;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public popUpDInvoiceDetailGUI() {
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
    }
    
    public void myTextArea()
    {
        txtAddress.setWrapStyleWord(true);
        txtAddress.setLineWrap(true);
        AreaScrollPane1.getVerticalScrollBar().setUI(new MyScrollBarUI());
        AreaScrollPane2.getVerticalScrollBar().setUI(new MyScrollBarUI());
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
        txtFirstName = new javax.swing.JTextField();
        lblTenDiaDiem = new javax.swing.JLabel();
        lblGioiThieu = new javax.swing.JLabel();
        lblValidateTen = new javax.swing.JLabel();
        lblValidateMota = new javax.swing.JLabel();
        lblValidateGia = new javax.swing.JLabel();
        AreaScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtLastName = new javax.swing.JTextField();
        lblTenDiaDiem1 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblTenDiaDiem2 = new javax.swing.JLabel();
        lblGioiThieu1 = new javax.swing.JLabel();
        AreaScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice_Product = new javax.swing.JTable();
        txtTong = new javax.swing.JTextField();
        lblTenDiaDiem3 = new javax.swing.JLabel();
        lblTenDiaDiem5 = new javax.swing.JLabel();
        lblGioiThieu2 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        lblGioiThieu3 = new javax.swing.JLabel();
        lblNhanVienTao = new javax.swing.JLabel();

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

        txtFirstName.setEditable(false);
        txtFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFirstName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        lblTenDiaDiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenDiaDiem.setText("Họ:");

        lblGioiThieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGioiThieu.setText("Địa Chỉ:");

        lblValidateTen.setPreferredSize(new java.awt.Dimension(24, 24));

        AreaScrollPane1.setToolTipText("");

        txtAddress.setEditable(false);
        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAddress.setRows(5);
        txtAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane1.setViewportView(txtAddress);

        txtLastName.setEditable(false);
        txtLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLastName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        lblTenDiaDiem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenDiaDiem1.setText("Tên:");

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        lblTenDiaDiem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenDiaDiem2.setText("Sđt");

        lblGioiThieu1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGioiThieu1.setText("Ghi Chú:");

        AreaScrollPane2.setToolTipText("");

        txtNote.setEditable(false);
        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNote.setRows(5);
        txtNote.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane2.setViewportView(txtNote);

        tblInvoice_Product.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInvoice_Product.setFillsViewportHeight(true);
        tblInvoice_Product.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblInvoice_Product.setRowHeight(35);
        jScrollPane2.setViewportView(tblInvoice_Product);

        txtTong.setEditable(false);
        txtTong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTong.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongActionPerformed(evt);
            }
        });

        lblTenDiaDiem3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenDiaDiem3.setText("Tổng tiền:");

        lblTenDiaDiem5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenDiaDiem5.setText("Sản phẩm:");

        lblGioiThieu2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGioiThieu2.setText("Ngày Tạo:");

        lblNgayTao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnThem.setBackground(new java.awt.Color(77, 77, 77));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Xuất PDF");
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(true);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        lblGioiThieu3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGioiThieu3.setText("Nhân Viên:");

        lblNhanVienTao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlBodyLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                                .addComponent(lblValidateMota, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(145, 145, 145))
                                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                                .addComponent(lblValidateGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pnlBodyLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                        .addComponent(lblTenDiaDiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(AreaScrollPane1)
                                .addGap(91, 91, 91))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGioiThieu)
                                    .addComponent(lblTenDiaDiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBodyLayout.createSequentialGroup()
                                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblTenDiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTenDiaDiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(58, 58, 58)
                                                .addComponent(lblTenDiaDiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioiThieu1)
                            .addComponent(AreaScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblGioiThieu2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblGioiThieu3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNhanVienTao, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94))))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTenDiaDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenDiaDiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGioiThieu3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNhanVienTao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenDiaDiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioiThieu2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiThieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AreaScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTenDiaDiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenDiaDiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(lblValidateGia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(lblValidateMota, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed

    }//GEN-LAST:event_btnThemMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("c:/windows/fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        if (f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            Document document = new Document();
            try {
        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
            String id = this.invoice.getId() + "";
            PdfWriter.getInstance(document, new FileOutputStream(f.getSelectedFile() + "\\HD"+ id + ".pdf"));

            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            Paragraph stars = new Paragraph("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            Paragraph brand = new Paragraph("Nexus");
            Paragraph header = new Paragraph("Sản phẩm             SL                   Đơn giá", new com.itextpdf.text.Font(bf, 12));

            brand.setIndentationLeft(110);
            document.add(stars);
            document.add(brand);
            document.add(stars);
            document.add(header);
            document.add(stars);
            
            //Khởi tạo một table có 3 cột
            PdfPTable table = new PdfPTable(3);
            table.setTotalWidth(260);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(0);

            List<ProductDTO> products = invoiceDetailBLL.findByIdInvoice(this.invoice.getId());
            PdfPCell space = new PdfPCell(new Paragraph(" "));
            space.setBorder(Rectangle.NO_BORDER);
            for( int i = 0; i < products.size(); i++)
            {
                InvoiceDetailDTO detail = invoiceDetailBLL.findById(invoice.getId(), products.get(i).getId());

                PdfPCell data1 = new PdfPCell(new Paragraph(products.get(i).getName(), new com.itextpdf.text.Font(bf, 12)));
                PdfPCell data2 = new PdfPCell(new Paragraph("" + detail.getQuantity()));
                PdfPCell data3 = new PdfPCell(new Paragraph("" + detail.getPrice()));

                data1.setBorder(Rectangle.NO_BORDER);
                data2.setBorder(Rectangle.NO_BORDER);
                data3.setBorder(Rectangle.NO_BORDER);

                table.addCell(data1);
                table.addCell(data2);
                table.addCell(data3);
                
                table.addCell(space);
                table.addCell(space);
                table.addCell(space);
            }
            
            document.add(table);
            document.add(stars);
            document.add(new Paragraph("Tổng tiền                                     " + invoice.getTotal(), new com.itextpdf.text.Font(bf, 12)));
            document.add(stars);
            document.add(new Paragraph("         Xin cảm ơn. Hẹn gặp lại Quý khách!            ", new com.itextpdf.text.Font(bf, 12)));
            document.add(stars);
            // đóng file
            document.close();
            JOptionPane.showMessageDialog(this, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
        
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
            java.util.logging.Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new popUpDInvoiceDetailGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane1;
    private javax.swing.JScrollPane AreaScrollPane2;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnThem;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblGioiThieu1;
    private javax.swing.JLabel lblGioiThieu2;
    private javax.swing.JLabel lblGioiThieu3;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanVienTao;
    private javax.swing.JLabel lblTenDiaDiem;
    private javax.swing.JLabel lblTenDiaDiem1;
    private javax.swing.JLabel lblTenDiaDiem2;
    private javax.swing.JLabel lblTenDiaDiem3;
    private javax.swing.JLabel lblTenDiaDiem5;
    private javax.swing.JLabel lblValidateGia;
    private javax.swing.JLabel lblValidateMota;
    private javax.swing.JLabel lblValidateTen;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTable tblInvoice_Product;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
