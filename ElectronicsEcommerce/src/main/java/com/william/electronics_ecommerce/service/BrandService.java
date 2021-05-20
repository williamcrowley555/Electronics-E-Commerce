package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    Brand saveBrand(Brand brand);
    void deleteBrandById(Long id);
}
