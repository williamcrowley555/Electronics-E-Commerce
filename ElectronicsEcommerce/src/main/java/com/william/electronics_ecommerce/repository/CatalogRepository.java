package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

}
