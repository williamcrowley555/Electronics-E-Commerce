package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Cart;

import javax.servlet.http.HttpSession;

public interface CartService {

    static final String SESSION_KEY_SHOPPING_CART = "shoppingCart";

    Cart getCart(HttpSession session);
    void setCart(HttpSession session, Cart cart);
    void removeCart(HttpSession session);
    void clearCart(HttpSession session);
}
