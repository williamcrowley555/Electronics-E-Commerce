package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Cart;
import com.william.electronics_ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(SESSION_KEY_SHOPPING_CART);
        if (cart == null) {
            cart = new Cart();
            setCart(session, cart);
        }
        return cart;
    }

    @Override
    public void setCart(HttpSession session, Cart cart) {
        session.setAttribute(SESSION_KEY_SHOPPING_CART, cart);
    }

    @Override
    public void removeCart(HttpSession session) {
        session.removeAttribute(SESSION_KEY_SHOPPING_CART);
    }

}
