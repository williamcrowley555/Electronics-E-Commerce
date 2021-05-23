package com.william.electronics_ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InvoiceDetailsId implements Serializable {

    @Column(name = "invoice_id", nullable=false, updatable=false)
    private Long invoiceId;

    @Column(name = "product_id", nullable=false, updatable=false)
    private Long productId;

    public InvoiceDetailsId() {
    }

    public InvoiceDetailsId(Long invoiceId, Long productId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
    }
}
