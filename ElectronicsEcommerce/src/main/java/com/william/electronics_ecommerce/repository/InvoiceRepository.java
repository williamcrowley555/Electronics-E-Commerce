package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.Invoice;
import com.william.electronics_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUser(User user);
}
