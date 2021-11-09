/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

import java.util.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Hi
 */
public class ProductPriceHistoryDTO {
    private long id;
    private Date effective_date;
    private long price;
    private long product_id;

    public ProductPriceHistoryDTO() {
    }

    public ProductPriceHistoryDTO(long id, Date effective_date, long price, long product_id) {
        this.id = id;
        this.effective_date = effective_date;
        this.price = price;
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(Date effective_date) {
        this.effective_date = effective_date;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "ProductPriceHistoryDTO{" + "id=" + id + ", effective_date=" + effective_date + ", price=" + price + ", product_id=" + product_id + '}';
    }
}
