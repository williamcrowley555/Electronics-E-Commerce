package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "ProductPriceHistory")
@Table(name = "product_price_history")
public class ProductPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Valid
    private Product product;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 0, message = "Giá tiền không hợp lệ")
    @Max(value = 1000000000, message = "Giá tiền quá lớn")
    private long price;

    @Column(name = "effective_date", nullable = false)
    private Date effectiveDate;

    public ProductPriceHistory() {
    }

    public ProductPriceHistory(Long id, Product product, long price, Date effectiveDate) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.effectiveDate = effectiveDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "ProductPriceHistory{" +
                "id=" + id +
                ", product=" + product +
                ", price=" + price +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
