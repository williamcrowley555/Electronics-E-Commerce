package com.william.electronics_ecommerce.model;

public class CartItem {

    private final Product product;
    private long price;
    private int quantity;
    private long subTotal;

    public CartItem(Product product) {
        this.product = product;
        this.price = product.getPrice();
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getSubTotal() {
        subTotal = price * quantity;
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }
}
