package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> getAllCatalog();
    Catalog getCatalogById(Long id);
    Catalog saveCatalog(Catalog catalog);
    void deleteCatalogById(Long id);
}
