package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "InvoiceDetails")
@Table(name = "invoice_details")
public class InvoiceDetails implements Serializable {

    @EmbeddedId
    private InvoiceDetailsId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng không hợp lệ")
    @Max(value = 1000000000, message = "Số lượng quá lớn")
    private int quantity;

    @Column(name = "subTotal", nullable = false)
    @NotNull(message = "Thành tiền không được để trống")
    @Min(value = 1, message = "Thành tiền không hợp lệ")
    private long subTotal;

    public InvoiceDetails() {
    }

    public InvoiceDetails(InvoiceDetailsId id, Invoice invoice, Product product, int quantity, long subTotal) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public InvoiceDetails(Invoice invoice, Product product, int quantity, long subTotal) {
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public InvoiceDetailsId getId() {
        return id;
    }

    public void setId(InvoiceDetailsId id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", product=" + product +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
