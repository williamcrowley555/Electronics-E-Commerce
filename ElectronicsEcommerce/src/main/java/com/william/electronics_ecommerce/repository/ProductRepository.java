package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findByBrandName(String brand, Pageable pageable);
    public Page<Product> findByBrand_CatalogList_Name(String catalog, Pageable pageable);
    public Page<Product> findByBrandNameAndBrand_CatalogList_Name(String brand, String catalog, Pageable pageable);
}
