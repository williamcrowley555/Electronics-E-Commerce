package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceHistory extends JpaRepository<Invoice, Long> {
}
