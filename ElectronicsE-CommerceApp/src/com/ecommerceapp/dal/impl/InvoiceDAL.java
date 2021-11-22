/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.ICatalogDAL;
import com.ecommerceapp.dal.IInvoiceDAL;
import java.util.List;
import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.dto.RevenueDTO;
import com.ecommerceapp.mapper.impl.CatalogMapper;
import com.ecommerceapp.mapper.impl.InvoiceMapper;
import com.ecommerceapp.mapper.impl.RevenueMapper;

/**
 *
 * @author HP
 */
public class InvoiceDAL extends AbstractDAL<InvoiceDTO> implements IInvoiceDAL {

    @Override
    public List<InvoiceDTO> findAll() {
        String sql = "SELECT * FROM invoice";
        return query(sql, new InvoiceMapper());
    }

    @Override
    public InvoiceDTO findById(Long id) {
        String sql = "SELECT * FROM invoice WHERE id = ?";
        List<InvoiceDTO> invoice = query(sql, new InvoiceMapper(), id);
        return invoice.isEmpty() ? null : invoice.get(0);
    }

    @Override
    public Long save(InvoiceDTO invoice) {
        String sql = "INSERT INTO invoice (`address`, `cancelling_date`, `confirmation_date`, `order_date`, `payment_date`, "
                    + "`phone`, `recipient_first_name`, `recipient_last_name`, `ship_date`, `status`, `total`, `user_id`, `employee_id`)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return insert(sql, invoice.getAddress(), invoice.getCancelDateFormat(), invoice.getConfirmDateFormat(), invoice.getOrderDateFormat(), invoice.getPaidDateFormat(),
                     invoice.getPhone(), invoice.getRecipientFirstName(), invoice.getRecipientLastName(), invoice.getShipDate(), invoice.getStatus(),
                     invoice.getTotal(), invoice.getUserId(), invoice.getEmployeeId()
                     );
    }

    @Override
    public void update(InvoiceDTO invoice) {
        String sql = 
                "UPDATE invoice SET address = ?, cancelling_date = ?, confirmation_date = ?, order_date = ?, payment_date = ?, phone = ?, recipient_first_name = ?,"
                +"recipient_last_name = ?, ship_date = ?, status = ?, total = ?, user_id = ?, employee_id = ? WHERE id = ?";
        update(sql, invoice.getAddress(), invoice.getCancelDateFormat(), invoice.getConfirmDateFormat(), invoice.getOrderDateFormat(), invoice.getPaidDateFormat(),
                     invoice.getPhone(), invoice.getRecipientFirstName(), invoice.getRecipientLastName(), invoice.getShipDateFormat(), invoice.getStatus(),
                     invoice.getTotal(), invoice.getUserId(), invoice.getEmployeeId(), invoice.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM invoice WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<RevenueDTO> getMonthlyProductReport(int month, int year) {
        String sql = "{CALL usp_invoice_monthlyReport(?, ?)}";
        return callQueryProc(sql, new RevenueMapper(), month, year);
    }

    @Override
    public List<InvoiceDTO> getProcessedOrder(int month, int year) {
        String sql = "SELECT * " +
                    "FROM invoice " +
                    "WHERE status IS NOT NULL AND MONTH(order_date) = ? AND YEAR(order_date) = ?";
        return query(sql, new InvoiceMapper(), month, year);
    }

    @Override
    public List<InvoiceDTO> getUnprocessedOrder(int month, int year) {
        String sql = "SELECT * " +
                    "FROM invoice " +
                    "WHERE status IS NULL AND MONTH(order_date) = ? AND YEAR(order_date) = ?";
        return query(sql, new InvoiceMapper(), month, year);
    }
}
