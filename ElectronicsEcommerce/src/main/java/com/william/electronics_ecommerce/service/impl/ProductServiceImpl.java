package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.repository.ProductRepository;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Product> getPaginated(int pageNo, int pageSize, String catalog, String brand, String sortField, String sortDirection, String keyword) {
        Pageable pageable = null;


        System.out.println("Sort Field: " + sortField);
        if (sortField == null || sortDirection == null)
            pageable = PageRequest.of(pageNo - 1, pageSize);
        else {
            Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                    : Sort.by(sortField).descending();
            pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        }

        if (catalog != null && brand != null) {
            return productRepository.findByBrandNameAndCatalogName(brand, catalog, pageable);
        } else if (catalog != null || brand != null) {
            if (catalog != null) {
                return productRepository.findByCatalogName(catalog, pageable);
            }
            else {
                return productRepository.findByBrandName(brand, pageable);
            }
        } else {
            if(keyword != null) {
                return productRepository.findAll(keyword, pageable);
            }
        }

        return productRepository.findAll(pageable);
    }
}
