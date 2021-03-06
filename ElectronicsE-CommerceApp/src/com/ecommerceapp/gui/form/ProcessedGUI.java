/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.form;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.bll.IProductBLL;
import com.ecommerceapp.bll.impl.InvoiceBLL;
import com.ecommerceapp.bll.impl.ProductBLL;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.gui.menu.MyComboBoxEditor;
import com.ecommerceapp.gui.menu.MyComboBoxRenderer;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.ecommerceapp.gui.menu.MyScrollBarUI;
import com.ecommerceapp.gui.popup.PopUpImportGUI;
import com.ecommerceapp.gui.popup.PopUpProductGUI;
import com.ecommerceapp.util.ProcessUnprocessTableLoaderUtil;
import com.ecommerceapp.util.ProductTableLoaderUtil;
import com.ecommerceapp.util.RevenueTableLoaderUtil;
import com.ecommerceapp.util.TableSetupUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class ProcessedGUI extends javax.swing.JPanel {

        String[] columnNames = {
                            "Id ????n",
                            "T??n kh??ch",
                            "S??t",
                            "Ng??y ?????t"
        };
                          
    /**
     * Creates new form Panel1
     */
    private IInvoiceBLL invoiceBLL;
    private List<InvoiceDTO> processedInvoices = new ArrayList<>();
    private List<InvoiceDTO> unprocessedInvoices = new ArrayList<>();
    TableRowSorter<TableModel> rowSorter = null;
    
    public ProcessedGUI() {
        initComponents();
        invoiceBLL = new InvoiceBLL();
        javax.swing.JComboBox boxMonth = (javax.swing.JComboBox) monthChooser.getComboBox();
        boxMonth = myComboBox(boxMonth, new Color(77,77,77));
        boxMonth.setLightWeightPopupEnabled (false);
        hideSpinnerArrow((javax.swing.JSpinner) monthChooser.getSpinner());
        hideSpinnerArrow((javax.swing.JSpinner) yearChooser.getSpinner());
        initNullTable();
        headerColor(77,77,77,tblProcessed);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
        boxMonth.setOpaque(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void loadTableData(int month, int year) {
        int noProcess = 0;
        processedInvoices = invoiceBLL.getProcessedOrder(month, year);
        if (processedInvoices == null || processedInvoices.isEmpty())
            JOptionPane.showMessageDialog(this, "Kh??ng c?? ????n ???? x??? l?? th??ng " + month + " n??m " + year, "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);    
        else {
            tblProcessed.setModel(new ProcessUnprocessTableLoaderUtil().setTable(processedInvoices, this.columnNames));
            this.rowSorter = TableSetupUtil.setTableFilter(tblProcessed, txtTimKiemDaXuli);
            headerColor(77,77,77,tblProcessed);
            resizeColumnWidth(tblProcessed);
            lblTitle.setText("Th???ng k?? x??? l?? ????n " + month + " n??m " + year);
            
            noProcess = invoiceBLL.getProcessedOrder(month, year).size();
            lblTotalProcessQuantity.setText("T???ng ????n ???? x??? l??: " + noProcess);
        }
        
        int noUnprocess = 0;
        unprocessedInvoices = invoiceBLL.getUnprocessedOrder(month, year);
        if (unprocessedInvoices == null || unprocessedInvoices.isEmpty())
            JOptionPane.showMessageDialog(this, "Kh??ng c?? ????n ch??a x??? l?? th??ng " + month + " n??m " + year, "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);    
        else {
            tblUnprocessed.setModel(new ProcessUnprocessTableLoaderUtil().setTable(unprocessedInvoices, this.columnNames));
            this.rowSorter = TableSetupUtil.setTableFilter(tblUnprocessed, txtTimKiemChuaXuLi);
            headerColor(77,77,77,tblUnprocessed);
            resizeColumnWidth(tblUnprocessed);
            lblTitle.setText("Th???ng k?? x??? l?? ????n " + month + " n??m " + year);
            
            noUnprocess = invoiceBLL.getUnprocessedOrder(month, year).size();
            lblTotalUnprocessQuantity.setText("T???ng ????n ch??a x??? l??: " + noUnprocess);
        }
        lblTotal.setText("T???ng ????n: " + (noProcess + noUnprocess));
    }
    
    public void initNullTable()
    {   
        DefaultTableModel emptyModel;
        emptyModel = new DefaultTableModel(columnNames,0);
        tblProcessed.setModel(emptyModel);
        headerColor(77,77,77,tblProcessed);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
        tblUnprocessed.setModel(emptyModel);
        headerColor(77,77,77,tblUnprocessed);
        scroll1.getVerticalScrollBar().setUI(new MyScrollBarUI());
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
    
    public Vector createHeader(Object[] columnNames){
        Vector header = new Vector();
        for(Object colName : columnNames){
            header.add(colName);
        }
        return header;
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
                Color flatGreen = new Color(26, 188, 156);
        
                BasicArrowButton custom = new BasicArrowButton(
                BasicArrowButton.SOUTH, null, null, Color.WHITE, null);
                custom.setBorder(new MatteBorder(0,0,0,0,flatGreen));
                return custom;
            }
        }); 

       return box;
    }
    
    public void hideSpinnerArrow(JSpinner spinner) {
        spinner.setUI(new BasicSpinnerUI() {
            protected Component createNextButton() {
                return null;
            }

            protected Component createPreviousButton() {
                return null;
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickMenu = new javax.swing.JPopupMenu();
        itemSua = new javax.swing.JMenuItem();
        itemNhap = new javax.swing.JMenuItem();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JButton();
        yearChooser = new com.toedter.calendar.JYearChooser();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        btnPrint = new javax.swing.JButton();
        pnlBody = new javax.swing.JPanel();
        lblTotalProcessQuantity = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        panelLeft = new java.awt.Panel();
        scroll = new javax.swing.JScrollPane();
        tblProcessed = new javax.swing.JTable();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiemDaXuli = new javax.swing.JTextField();
        panelRight = new java.awt.Panel();
        scroll1 = new javax.swing.JScrollPane();
        tblUnprocessed = new javax.swing.JTable();
        lblTimKiem1 = new javax.swing.JLabel();
        txtTimKiemChuaXuLi = new javax.swing.JTextField();
        lblTotalUnprocessQuantity = new javax.swing.JLabel();

        itemSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/edit_icon.png"))); // NOI18N
        itemSua.setText("S???a");
        itemSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuaActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemSua);

        itemNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/edit_icon.png"))); // NOI18N
        itemNhap.setText("Nh???p h??ng");
        itemNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNhapActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemNhap);

        setLayout(new java.awt.BorderLayout());

        pnlHead.setBackground(new java.awt.Color(255, 255, 255));
        pnlHead.setPreferredSize(new java.awt.Dimension(808, 150));

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Th???ng k?? doanh thu");

        btnThongKe.setBackground(new java.awt.Color(77, 77, 77));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setText("Th???ng k??");
        btnThongKe.setContentAreaFilled(false);
        btnThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThongKe.setOpaque(true);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThongKeMousePressed(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        yearChooser.setBackground(new java.awt.Color(204, 204, 204));

        btnPrint.setBackground(new java.awt.Color(77, 77, 77));
        btnPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Xu???t PDF");
        btnPrint.setContentAreaFilled(false);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setOpaque(true);
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrintMousePressed(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 550, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(yearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monthChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalProcessQuantity.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTotalProcessQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        scroll.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(77, 77, 77)));

        tblProcessed.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProcessed.setFillsViewportHeight(true);
        tblProcessed.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProcessed.setRowHeight(35);
        tblProcessed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProcessedMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblProcessed);

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/search_icon.png"))); // NOI18N

        txtTimKiemDaXuli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiemDaXuli.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(77, 77, 77)));
        txtTimKiemDaXuli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemDaXuliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemDaXuli, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiemDaXuli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(77, 77, 77)));

        tblUnprocessed.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUnprocessed.setFillsViewportHeight(true);
        tblUnprocessed.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblUnprocessed.setRowHeight(35);
        tblUnprocessed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblUnprocessedMouseReleased(evt);
            }
        });
        scroll1.setViewportView(tblUnprocessed);

        lblTimKiem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ecommerceapp/img/search_icon.png"))); // NOI18N

        txtTimKiemChuaXuLi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiemChuaXuLi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(77, 77, 77)));
        txtTimKiemChuaXuLi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemChuaXuLiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiemChuaXuLi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGroup(panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimKiem1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addGroup(panelRightLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTimKiemChuaXuLi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGap(0, 0, 0)
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblTotalUnprocessQuantity.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTotalUnprocessQuantity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalProcessQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalUnprocessQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLeft, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalProcessQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalUnprocessQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void itemSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuaActionPerformed
      
    }//GEN-LAST:event_itemSuaActionPerformed

    private void tblProcessedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProcessedMouseReleased
      
    }//GEN-LAST:event_tblProcessedMouseReleased

    private void itemNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNhapActionPerformed
      
    }//GEN-LAST:event_itemNhapActionPerformed

    private void btnThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMousePressed
       
    }//GEN-LAST:event_btnThongKeMousePressed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
    loadTableData(monthChooser.getMonth()+1, yearChooser.getYear());
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void tblUnprocessedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUnprocessedMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUnprocessedMouseReleased

    private void txtTimKiemDaXuliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemDaXuliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemDaXuliActionPerformed

    private void txtTimKiemChuaXuLiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemChuaXuLiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemChuaXuLiActionPerformed

    private void btnPrintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMousePressed
        // TODO add your handling code here:
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("c:/windows/fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger("test").log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger("test").log(Level.SEVERE, null, ex);
        }
        if ((unprocessedInvoices == null || unprocessedInvoices.isEmpty())
                &&(processedInvoices == null || processedInvoices.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? d??? li???u", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            // print pdf
            Document document = new Document();
            try {
                // kh???i t???o m???t PdfWriter truy???n v??o document v?? FileOutputStream
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                today.format(formatter);

                PdfWriter.getInstance(document, new FileOutputStream(f.getSelectedFile() + "\\XuLyDon"+ today.toString() + ".pdf"));

                // m??? file ????? th???c hi???n vi???t
                document.open();
                // th??m n???i dung s??? d???ng add function

                Paragraph brand = new Paragraph(lblTitle.getText(), new com.itextpdf.text.Font(bf, 16, Font.BOLD));

                brand.setAlignment(Element.ALIGN_CENTER);

                
                document.add(new Paragraph(" "));
                document.add(brand);
                document.add(new Paragraph(" "));
                
                document.add(new Paragraph("T???ng ????n ???? x??? l??: " + this.processedInvoices.size(), new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                document.add(new Paragraph(" "));
                //Kh???i t???o m???t table c?? 3 c???t
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setHorizontalAlignment(0);

                PdfPCell space = new PdfPCell(new Paragraph(" "));

                PdfPCell h1 = new PdfPCell(new Paragraph("M?? ????n", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                PdfPCell h2 = new PdfPCell(new Paragraph("T??n kh??ch h??ng", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                PdfPCell h3 = new PdfPCell(new Paragraph("S??? ??i???n tho???i", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                PdfPCell h4 = new PdfPCell(new Paragraph("Ng??y ?????t", new com.itextpdf.text.Font(bf, 12, Font.BOLD)));

                table.addCell(h1);
                table.addCell(h2);
                table.addCell(h3);
                table.addCell(h4);
                table.setHeaderRows(1);

                for( int i = 0; i < processedInvoices.size(); i++)
                {

                    PdfPCell data1 = new PdfPCell(new Paragraph(processedInvoices.get(i).getId() + "", new com.itextpdf.text.Font(bf, 12)));
                    PdfPCell data2 = new PdfPCell(new Paragraph(processedInvoices.get(i).getRecipientLastName() + " " + processedInvoices.get(i).getRecipientFirstName(), new com.itextpdf.text.Font(bf, 12)));
                    PdfPCell data3 = new PdfPCell(new Paragraph(processedInvoices.get(i).getPhone()));
                    PdfPCell data4 = new PdfPCell(new Paragraph(processedInvoices.get(i).getOrderDateFormat()));

                    table.addCell(data1);
                    table.addCell(data2);
                    table.addCell(data3);
                    table.addCell(data4);
                }
                Paragraph totalName = new Paragraph("T???ng c???ng: ", new com.itextpdf.text.Font(bf, 12));
                totalName.setAlignment(Element.ALIGN_RIGHT);

                document.add(table);
                
                //////////////////////
                document.add(new Paragraph(" "));
                document.add(new Paragraph("T???ng ????n ch??a x??? l??: " + this.unprocessedInvoices.size(), new com.itextpdf.text.Font(bf, 12, Font.BOLD)));
                document.add(new Paragraph(" "));
                //Kh???i t???o m???t table c?? 3 c???t
                PdfPTable table2 = new PdfPTable(4);
                table2.setWidthPercentage(100);
                table2.setHorizontalAlignment(0);

                table2.addCell(h1);
                table2.addCell(h2);
                table2.addCell(h3);
                table2.addCell(h4);
                table2.setHeaderRows(1);

                for( int i = 0; i < unprocessedInvoices.size(); i++)
                {
                    PdfPCell data1 = new PdfPCell(new Paragraph(unprocessedInvoices.get(i).getId() + "", new com.itextpdf.text.Font(bf, 12)));
                    PdfPCell data2 = new PdfPCell(new Paragraph(unprocessedInvoices.get(i).getRecipientLastName() + " " + unprocessedInvoices.get(i).getRecipientFirstName(), new com.itextpdf.text.Font(bf, 12)));
                    PdfPCell data3 = new PdfPCell(new Paragraph(unprocessedInvoices.get(i).getPhone()));
                    PdfPCell data4 = new PdfPCell(new Paragraph(unprocessedInvoices.get(i).getOrderDateFormat()));

                    table2.addCell(data1);
                    table2.addCell(data2);
                    table2.addCell(data3);
                    table2.addCell(data4);
                }

                document.add(table2);

                Paragraph dateDetail = new Paragraph("Ng??y "+today.getDayOfMonth() + " th??ng " + today.getMonthValue() +" n??m " + today.getYear() + "                 ", new com.itextpdf.text.Font(bf, 12));
                Paragraph endDetail = new Paragraph("Ng?????i th???ng k??                          ", new com.itextpdf.text.Font(bf, 12));

                dateDetail.setAlignment(Element.ALIGN_RIGHT);
                endDetail.setAlignment(Element.ALIGN_RIGHT);
                document.add(dateDetail);
                document.add(endDetail);

                // ????ng file
                document.close();
                JOptionPane.showMessageDialog(this, "L??u th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException ex) {
                Logger.getLogger("test").log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPrintMousePressed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JMenuItem itemNhap;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTimKiem1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalProcessQuantity;
    private javax.swing.JLabel lblTotalUnprocessQuantity;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private java.awt.Panel panelLeft;
    private java.awt.Panel panelRight;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JTable tblProcessed;
    private javax.swing.JTable tblUnprocessed;
    private javax.swing.JTextField txtTimKiemChuaXuLi;
    private javax.swing.JTextField txtTimKiemDaXuli;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
