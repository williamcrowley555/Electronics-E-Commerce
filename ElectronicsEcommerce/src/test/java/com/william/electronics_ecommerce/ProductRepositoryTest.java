package com.william.electronics_ecommerce;

import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetProductsByBrandName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Product> page = productRepository.findByBrandName("asus", pageable);
        List<Product> products = page.getContent();
        products.forEach(System.out::println);

    }

    @Test
    public void testGetProductsByCatalogName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Product> page = productRepository.findByBrand_CatalogList_Name("phụ kiện", pageable);
        List<Product> products = page.getContent();
        products.forEach(System.out::println);

    }

    @Test
    public void testGetProductsByBrandNameAndCatalogName() {
        Pageable pageable = PageRequest.of(0, 9);
        String brand = "asus";
        String catalog = "laptop";
        Page<Product> page = productRepository.findByBrandNameAndBrand_CatalogList_Name(brand, catalog, pageable);
        List<Product> products = page.getContent();
        products.forEach(System.out::println);

    }
}
