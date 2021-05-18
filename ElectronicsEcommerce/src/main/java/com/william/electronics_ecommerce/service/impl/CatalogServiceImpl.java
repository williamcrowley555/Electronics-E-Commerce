package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Catalog;
import com.william.electronics_ecommerce.repository.CatalogRepository;
import com.william.electronics_ecommerce.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog getCatalogById(Long id) {
        Catalog catalog = null;
        Optional<Catalog> optional = catalogRepository.findById(id);
        if (optional.isPresent()) {
            catalog = optional.get();
        } else {
            throw new RuntimeException("Catalog ID: " + id + " does not exist");
        }
        return catalog;
    }

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void deleteCatalogById(Long id) {
        catalogRepository.deleteById(id);
    }
}
