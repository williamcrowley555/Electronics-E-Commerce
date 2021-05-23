package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Invoice;
import com.william.electronics_ecommerce.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoices();
    List<Invoice> getInvoiceByUser(User user);
    Invoice getInvoiceById(Long id);
    Invoice saveInvoice(Invoice invoice, HttpSession session);
    void deleteInvoiceById(Long id);
    Invoice cancelInvoice(Invoice invoice);
}
