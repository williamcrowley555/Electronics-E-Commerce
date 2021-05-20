package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProductById(Long id);
    Page<Product> getPaginated(int pageNo, int pageSize, String catalog, String brand);
}
