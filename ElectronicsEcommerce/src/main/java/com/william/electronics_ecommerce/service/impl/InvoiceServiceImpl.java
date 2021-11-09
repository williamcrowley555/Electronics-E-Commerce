package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.*;
import com.william.electronics_ecommerce.repository.InvoiceRepository;
import com.william.electronics_ecommerce.service.CartService;
import com.william.electronics_ecommerce.service.InvoiceService;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getInvoiceByUser(User user) {
        return invoiceRepository.findByUser(user);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        Invoice invoice = null;
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            invoice = optional.get();
        }
        else {
            throw new RuntimeException("Invoice ID: " + id + " does not exist");
        }
        return invoice;
    }

    @Override
    public Invoice saveInvoice(Invoice invoice, HttpSession session) {
        Cart cart = cartService.getCart(session);

        if (cart.isEmpty()) {
            return null;
        }

        invoice.setTotal(cart.getTotal());
        Invoice savedInvoice = invoiceRepository.save(invoice);

        for (CartItem item : cart.getItems()) {
            savedInvoice.getDetails().add(new InvoiceDetails(new InvoiceDetailsId(savedInvoice.getId(), item.getProduct().getId()),
                                            savedInvoice, item.getProduct(), item.getPrice(), item.getQuantity(), item.getSubTotal()));

            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productService.saveProduct(product);
        }

        cartService.clearCart(session);

        return invoiceRepository.save(savedInvoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice cancelInvoice(Invoice invoice) {
        invoice.setStatus(4);
        invoice.setCancellingDate(LocalDate.now());

        for (InvoiceDetails detail : invoice.getDetails()) {
            Product product =  detail.getProduct();
            int quantity = detail.getQuantity();
            product.setQuantity(product.getQuantity() + quantity);
            productService.saveProduct(product);
        }
        return invoiceRepository.save(invoice);
    }
}
