package com.william.electronics_ecommerce.controller;

import com.william.electronics_ecommerce.model.Cart;
import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.service.CartService;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public String addProduct(HttpSession session,
                             @RequestParam("id") Long productId,
                             @RequestParam(value = "qty", required = false, defaultValue = "1") int quantity) {
        Product product = productService.getProductById(productId);
        Cart cart = cartService.getCart(session);
        cart.addItem(product, quantity);

        return "cart";
    }

    @PostMapping("/remove-product")
    public String remove(HttpSession session, @RequestParam("id") Long productId) {
        Product product = productService.getProductById(productId);
        Cart cart = cartService.getCart(session);
        cart.removeItem(product);

        return "cart";
    }

    @PostMapping("/update-product")
    public String remove(HttpSession session,
                         @RequestParam("id") Long productId,
                         @RequestParam(value = "qty") int quantity) {
        Product product = productService.getProductById(productId);
        Cart cart = cartService.getCart(session);
        cart.updateItem(product, quantity);

        return "cart";
    }
}
