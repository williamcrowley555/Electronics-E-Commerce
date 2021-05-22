package com.william.electronics_ecommerce.model;

public class CartItem {

    private final Product product;
    private int quantity;
    private long subTotal;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getSubTotal() {
        subTotal = product.getPrice() * quantity;
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }
}
