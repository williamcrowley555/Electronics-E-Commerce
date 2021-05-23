package com.william.electronics_ecommerce.controller;

import com.william.electronics_ecommerce.model.Invoice;
import com.william.electronics_ecommerce.model.User;
import com.william.electronics_ecommerce.service.InvoiceService;
import com.william.electronics_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/login?checkout";
        }

        User currentUser = userService.getUserByEmail(auth.getName());

        Invoice invoice = new Invoice();
        invoice.setRecipientFirstName(currentUser.getFirstName());
        invoice.setRecipientLastName(currentUser.getLastName());
        invoice.setPhone(currentUser.getPhone());
        invoice.setAddress(currentUser.getAddress());

        model.addAttribute("invoice", invoice);

        return "checkout";
    }

    @PostMapping("/save")
    public String saveInvoice(@Valid @ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult,
                              HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "checkout";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByEmail(auth.getName());
        invoice.setUser(currentUser);

        Invoice savedInvoice = invoiceService.saveInvoice(invoice, session);

        return "checkout_success";
    }

    @GetMapping("/history")
    public String showAllCustomerInvoices(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }

        User currentUser = userService.getUserByEmail(auth.getName());

        List<Invoice> invoiceList = invoiceService.getInvoiceByUser(currentUser);

        model.addAttribute("invoiceList", invoiceList);

        return "invoice_history";
    }

    @GetMapping("/details/{invoiceId}")
    public String showInvoiceDetails(Model model, @PathVariable("invoiceId") Long invoiceId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }

        Invoice invoice = invoiceService.getInvoiceById(invoiceId);

        model.addAttribute("invoice", invoice);

        return "invoice_details";
    }

    @GetMapping("/cancel/{invoiceId}")
    public String cancelInvoice(Model model, @PathVariable("invoiceId") Long invoiceId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }

        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        invoiceService.cancelInvoice(invoice);

        return "redirect:/invoice/history";
    }
}
