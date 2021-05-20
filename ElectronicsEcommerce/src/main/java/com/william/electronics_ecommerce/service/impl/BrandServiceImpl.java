package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Brand;
import com.william.electronics_ecommerce.repository.BrandRepository;
import com.william.electronics_ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        Brand brand = null;
        Optional<Brand> optional = brandRepository.findById(id);
        if (optional.isPresent()) {
            brand = optional.get();
        } else {
            throw new RuntimeException("Brand ID: " + id + " does not exist");
        }
        return brand;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
}
