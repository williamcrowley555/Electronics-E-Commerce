package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.repository.ProductRepository;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = null;
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Product ID: " + id + " does not exist");
        }
        return product;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPaginated(int pageNo, int pageSize, String catalog, String brand) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        if (catalog != null && brand != null) {
            return productRepository.findByBrandNameAndBrand_CatalogList_Name(brand, catalog, pageable);
        }
        else if (catalog != null || brand != null) {
            if (catalog != null) {
                return productRepository.findByBrand_CatalogList_Name(catalog, pageable);
            }
            else {
                return productRepository.findByBrandName(brand, pageable);
            }
        }

        return productRepository.findAll(pageable);
    }
}
