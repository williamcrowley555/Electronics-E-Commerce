/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.ICatalogDAL;
import com.tourdulich.dal.IInvoiceDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import com.tourdulich.mapper.impl.LoaiDuLichMapper;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;
import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.InvoiceDTO;
import com.tourdulich.mapper.impl.CatalogMapper;
import com.tourdulich.mapper.impl.InvoiceMapper;

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
                    + "`phone`, `recipient_first_name`, `recipient_last_name`, `ship_date`, `status`, `total`, `user_id`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return insert(sql, invoice.getAddress(), invoice.getCancelDateFormat(), invoice.getConfirmDateFormat(), invoice.getOrderDateFormat(), invoice.getPaidDateFormat(),
                     invoice.getPhone(), invoice.getRecipientFirstName(), invoice.getRecipientLastName(), invoice.getShipDate(), invoice.getStatus(),
                     invoice.getTotal(), invoice.getUserId()
                     );
    }

    @Override
    public void update(InvoiceDTO invoice) {
        String sql = 
                "UPDATE invoice SET address = ?, cancelling_date = ?, confirmation_date = ?, order_date = ?, payment_date = ?, phone = ?, recipient_first_name = ?,"
                +"recipient_last_name = ?, ship_date = ?, status = ?, total = ?, user_id = ? WHERE id = ?";
        update(sql, invoice.getAddress(), invoice.getCancelDateFormat(), invoice.getConfirmDateFormat(), invoice.getOrderDateFormat(), invoice.getPaidDateFormat(),
                     invoice.getPhone(), invoice.getRecipientFirstName(), invoice.getRecipientLastName(), invoice.getShipDateFormat(), invoice.getStatus(),
                     invoice.getTotal(), invoice.getUserId(), invoice.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM invoice WHERE id = ?";
        update(sql, id);
    }
}
