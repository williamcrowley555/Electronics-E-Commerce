/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

/**
 *
 * @author Hi
 */
public class InvoiceDetailDTO {
    private Long invoice_id;
    private Long product_id;
    private int quantity;
    private Long subTotal;
    private Long price;

    public InvoiceDetailDTO() {
    }

    public InvoiceDetailDTO(Long invoice_id, Long product_id, int quantity, Long subTotal, Long price) {
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.price = price;
    }

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
 
}
