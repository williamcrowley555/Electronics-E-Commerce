package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProductById(Long id);
}
