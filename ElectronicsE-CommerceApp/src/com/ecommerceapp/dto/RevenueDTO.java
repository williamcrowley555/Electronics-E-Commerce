/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

/**
 *
 * @author HP
 */
public class RevenueDTO {
    private Long productId;
    private String productName;
    private Long productPrice;
    private int productTotalQuantity;
    private Long productSubTotal;

    public RevenueDTO() {
    }

    public RevenueDTO(Long productId, String productName, Long productPrice, int productTotalQuantity, Long productSubTotal) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTotalQuantity = productTotalQuantity;
        this.productSubTotal = productSubTotal;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductTotalQuantity() {
        return productTotalQuantity;
    }

    public void setProductTotalQuantity(int productTotalQuantity) {
        this.productTotalQuantity = productTotalQuantity;
    }

    public Long getProductSubTotal() {
        return productSubTotal;
    }

    public void setProductSubTotal(Long productSubTotal) {
        this.productSubTotal = productSubTotal;
    }

    @Override
    public String toString() {
        return "Revenue{" + "productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", productTotalQuantity=" + productTotalQuantity + ", productSubTotal=" + productSubTotal + '}';
    }
}
