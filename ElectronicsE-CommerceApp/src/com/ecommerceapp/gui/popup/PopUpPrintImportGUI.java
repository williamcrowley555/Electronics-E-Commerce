/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.popup;

import com.ecommerceapp.bll.IBrandBLL;
import com.ecommerceapp.bll.IBrand_CatalogBLL;
import com.ecommerceapp.bll.ICatalogBLL;
import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.IInvoiceDetailBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.impl.BrandBLL;
import com.ecommerceapp.bll.impl.Brand_CatalogBLL;
import com.ecommerceapp.bll.impl.CatalogBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.bll.impl.InvoiceDetailBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
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
import com.ecommerceapp.util.InputValidatorUtil;
import com.ecommerceapp.util.ProductTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.swing.JFileChooser;
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

/**
 *
 * @author Hi
 */
public class PopUpPrintImportGUI extends javax.swing.JFrame {
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
    TableRowSorter<TableModel> rowSorter = null;
    List<ProductDTO> productList = new ArrayList<>();
    DefaultTableModel model;
    Long total = 0L;
    Long userId = null;
    private PopUpTableChonUserGUI popUp = null;
    private UserDTO user;
    String[] columnNamesProduct = {
                            "Id",
                            "Tên sản phẩm",
                            "Giá",
                            "Nhãn hiệu",
                            "Loại",
                            "SL"
                                          
    };
    
    String[] columnNamesInvoice_Product = {
                            "Id Sản phẩm",
                            "Tên sản phẩm",
                            "Giá",
                            "Số Lượng",
                            "Thành tiền"
                                                                 
    };
    
    
    public PopUpPrintImportGUI(String action, UserDTO user) {
        initComponents();
        this.user = user;
        this.action = action;    
        productBLL = new ProductBLL();
        brandBLL = new BrandBLL();
        catalogBLL = new CatalogBLL();
        brand_catalogBLL = new Brand_CatalogBLL();
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        CustomWindow();
        loadTableData();
        initNullTable();
        this.setVisible(true);    
    }
    
    public PopUpPrintImportGUI(String action, ProductDTO product) throws UnsupportedEncodingException {
        initComponents();
        this.action = action;  
        this.product = product;
        productBLL = new ProductBLL();
        brandBLL = new BrandBLL();
        catalogBLL = new CatalogBLL();
        brand_catalogBLL = new Brand_CatalogBLL();
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        CustomWindow();
        loadTableData();
        initNullTable();
        this.setVisible(true);    
    }
    
    
    public void loadTableData() {
        //tblDiaDiem.setModel(new DiaDiemTableLoaderUtil().setTable(diaDiemBLL.findAll(), this.columnNames)) ;
        tblProduct.setModel(new ProductTableLoaderUtil().setTable(productBLL.findAll(), this.columnNamesProduct)) ;
        this.rowSorter = TableSetupUtil.setTableFilter(tblProduct, txtTimKiem);
        headerColor(77,77,77,tblProduct);
        resizeColumnWidth(tblProduct);
        productChooserScrollpane.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
    
    public void initNullTable()
    {   
        DefaultTableModel emptyModel;
        emptyModel = new DefaultTableModel(columnNamesInvoice_Product,0);
        tblInvoice_Product.setModel(emptyModel);
        headerColor(77,77,77,tblInvoice_Product);
        productChoseScrollpane.getVerticalScrollBar().setUI(new MyScrollBarUI());
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
    
    
    public PopUpPrintImportGUI() {
        initComponents(); 
        productBLL = new ProductBLL();
        brandBLL = new BrandBLL();
        catalogBLL = new CatalogBLL();
        brand_catalogBLL = new Brand_CatalogBLL();
        invoiceBLL = new InvoiceBLL();
        invoiceDetailBLL = new InvoiceDetailBLL();
        CustomWindow();
        loadTableData();
        initNullTable();
        this.setVisible(true); 
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
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateTen = new javax.swing.JLabel();
        lblValidateMota = new javax.swing.JLabel();
        lblValidateGia = new javax.swing.JLabel();
        lblTinh1 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        productChooserScrollpane = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        productChoseScrollpane = new javax.swing.JScrollPane();
        tblInvoice_Product = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        lblTinh2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        lblTinh3 = new javax.swing.JLabel();

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

        btnLuu.setBackground(new java.awt.Color(77, 77, 77));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/save_icon.png"))); // NOI18N
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
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/gui/popup/cancel_icon.png"))); // NOI18N
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

        lblTinh1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinh1.setText("Số lượng:");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduct.setFillsViewportHeight(true);
        tblProduct.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProduct.setRowHeight(35);
        productChooserScrollpane.setViewportView(tblProduct);

        btnXoa.setBackground(new java.awt.Color(77, 77, 77));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/minus_icon.png"))); // NOI18N
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
        productChoseScrollpane.setViewportView(tblInvoice_Product);

        btnThem.setBackground(new java.awt.Color(77, 77, 77));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/plus_icon.png"))); // NOI18N
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

        lblTinh2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinh2.setText("Tìm Kiếm:");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        lblTinh3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinh3.setText("Danh sách hàng hoá muốn nhập:");

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTinh3)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblTinh2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTinh1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblValidateMota, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblValidateGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(144, 144, 144))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                        .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(205, 205, 205)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(productChoseScrollpane)
                            .addComponent(productChooserScrollpane))
                        .addContainerGap())))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong)
                    .addComponent(lblTinh1)
                    .addComponent(lblTinh2)
                    .addComponent(txtTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productChooserScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTinh3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblValidateTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(lblValidateGia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValidateMota, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(productChoseScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
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

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        productBLL = new ProductBLL();
        int rowindex = tblProduct.getSelectedRow();

        if (rowindex >=0)
        {

            Long id = Long.parseLong(tblProduct.getValueAt(rowindex,0).toString());
            ProductDTO selected = productBLL.findById(id);
            if (InputValidatorUtil.isVailidNumber(txtSoLuong.getText(), 1, selected.getQuantity()).isEmpty())
            {
                boolean duplicate = false;
                for (ProductDTO product : productList)
                {
                    if (product.getId().equals(selected.getId()))
                    duplicate = true;
                }

                if (!duplicate)
                {

                    ProductDTO temp = selected;
                    if (temp.getQuantity()!=0)
                    {
                        temp.setQuantity(Integer.parseInt(txtSoLuong.getText()));
                        productList.add(temp);
                        model = new DefaultTableModel(columnNamesInvoice_Product,0);
                        for (int i = 0; i < productList.size(); i++) {
                            model.addRow(new Object[]   {
                                String.valueOf(productList.get(i).getId()),
                                String.valueOf(productList.get(i).getName()),
                                String.valueOf(productList.get(i).getPrice()),
                                String.valueOf(productList.get(i).getQuantity()),
                                String.valueOf(productList.get(i).getQuantity()*productList.get(i).getPrice())

                            });

                            tblInvoice_Product.setModel(model);

                        }
                    }
                } else if (duplicate)
                {
                    for (ProductDTO product : productList)
                    {
                        if (product.getId().equals(selected.getId()))
                        {
                            if (product.getQuantity()+Integer.parseInt(txtSoLuong.getText()) <= selected.getQuantity())
                            {
                                product.setQuantity(product.getQuantity()+Integer.parseInt(txtSoLuong.getText()));
                                model = new DefaultTableModel(columnNamesInvoice_Product,0);
                                for (int i = 0; i < productList.size(); i++) {
                                    model.addRow(new Object[]   {
                                        String.valueOf(productList.get(i).getId()),
                                        String.valueOf(productList.get(i).getName()),
                                        String.valueOf(productList.get(i).getPrice()),
                                        String.valueOf(productList.get(i).getQuantity()),
                                        String.valueOf(productList.get(i).getQuantity()*productList.get(i).getPrice())
                                    });

                                    tblInvoice_Product.setModel(model);

                                }
                            }
                        }
                    }
                }
                headerColor(77,77,77,tblInvoice_Product);
            } else JOptionPane.showMessageDialog(this, InputValidatorUtil.isVailidNumber(txtSoLuong.getText(), 1, selected.getQuantity()), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 sản phẩm để thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowindex = tblInvoice_Product.getSelectedRow();
        if (rowindex >=0)
        {
            Long id = Long.parseLong(tblInvoice_Product.getValueAt(rowindex,0).toString());
            for (int i = 0; i < productList.size(); i++)
            {
                if (id.equals(productList.get(i).getId()))
                productList.remove(productList.get(i));
            }
            model = new DefaultTableModel(columnNamesInvoice_Product,0);
            for (int i = 0; i < productList.size(); i++) {
                model.addRow(new Object[]   {
                    String.valueOf(productList.get(i).getId()),
                    String.valueOf(productList.get(i).getName()),
                    String.valueOf(productList.get(i).getPrice()),
                    String.valueOf(productList.get(i).getQuantity()),
                    String.valueOf(productList.get(i).getQuantity()*productList.get(i).getPrice())
                });

            }

            if (productList.size() > 0)
            tblInvoice_Product.setModel(model);
            else
            {
                model = new DefaultTableModel(columnNamesInvoice_Product,0);
                tblInvoice_Product.setModel(model);
            }
            headerColor(77,77,77,tblInvoice_Product);
        }    else JOptionPane.showMessageDialog(this, "Hãy chọn 1 sản phẩm để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (true)
        {
            if(true) {
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
                    // print pdf
                    Document document = new Document();
                    try {
                        // khởi tạo một PdfWriter truyền vào document và FileOutputStream
                        LocalDate today = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        today.format(formatter);

                        PdfWriter.getInstance(document, new FileOutputStream(f.getSelectedFile() + "\\NhapHang"+ today.toString() + ".pdf"));

                        // mở file để thực hiện viết
                        document.open();
                        // thêm nội dung sử dụng add function
                        Paragraph number = new Paragraph("Số:.................", new com.itextpdf.text.Font(bf, 12));
                        number.setIndentationLeft(30);

                        Paragraph brand = new Paragraph("GIẤY ĐỀ NGHỊ NHẬP HÀNG HOÁ VẬT TƯ", new com.itextpdf.text.Font(bf, 16, Font.BOLD));

                        Paragraph name = new Paragraph("Họ tên:........................................................................... Đơn vị:..................................................", new com.itextpdf.text.Font(bf, 12));

                        brand.setIndentationLeft(100);

                        document.add(number);
                        document.add(new Paragraph(" "));
                        document.add(brand);
                        document.add(name);
                        document.add(new Paragraph(" "));

                        //Khởi tạo một table có 3 cột
                        PdfPTable table = new PdfPTable(4);
                        table.setWidthPercentage(100);
                        table.setHorizontalAlignment(0);

                        PdfPCell space = new PdfPCell(new Paragraph(" "));

                        PdfPCell h1 = new PdfPCell(new Paragraph("Sản phẩm cần nhập", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                        PdfPCell h2 = new PdfPCell(new Paragraph("Số lượng", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                        PdfPCell h3 = new PdfPCell(new Paragraph("Đơn giá", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                        PdfPCell h4 = new PdfPCell(new Paragraph("Ghi chú", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));

                        table.addCell(h1);
                        table.addCell(h2);
                        table.addCell(h3);
                        table.addCell(h4);
                        table.setHeaderRows(1);

                        for( int i = 0; i < productList.size(); i++)
                        {

                            PdfPCell data1 = new PdfPCell(new Paragraph(productList.get(i).getName(), new com.itextpdf.text.Font(bf, 12)));
                            PdfPCell data2 = new PdfPCell(new Paragraph("" + productList.get(i).getQuantity()));
                            PdfPCell data3 = new PdfPCell(new Paragraph("" + productList.get(i).getPrice()));

                            table.addCell(data1);
                            table.addCell(data2);
                            table.addCell(data3);
                            table.addCell(space);

                        }

                        document.add(table);

                        Paragraph dateDetail = new Paragraph("Ngày "+today.getDayOfMonth() + " tháng " + today.getMonthValue() +" năm " + today.getYear() + "                 ", new com.itextpdf.text.Font(bf, 12));
                        Paragraph endDetail = new Paragraph("Người lập giấy                          ", new com.itextpdf.text.Font(bf, 12));

                        dateDetail.setAlignment(Element.ALIGN_RIGHT);
                        endDetail.setAlignment(Element.ALIGN_RIGHT);
                        document.add(dateDetail);
                        document.add(endDetail);

                        // đóng file
                        document.close();
                        JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(popUpDInvoiceDetailGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else if(this.action.equals("PUT")) {
                try {
                    //                    newProduct.setQuantity(this.product.getQuantity());
                    //                    productBLL.update(newProduct);
                    //
                    //                    System.out.println(newProduct);
                    //                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    //                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpPrintImportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpPrintImportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpPrintImportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpPrintImportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpPrintImportGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTinh1;
    private javax.swing.JLabel lblTinh2;
    private javax.swing.JLabel lblTinh3;
    private javax.swing.JLabel lblValidateGia;
    private javax.swing.JLabel lblValidateMota;
    private javax.swing.JLabel lblValidateTen;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JScrollPane productChooserScrollpane;
    private javax.swing.JScrollPane productChoseScrollpane;
    private javax.swing.JTable tblInvoice_Product;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
