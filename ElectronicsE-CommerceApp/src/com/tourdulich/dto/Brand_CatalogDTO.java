/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

/**
 *
 * @author Hi
 */
public class Brand_CatalogDTO {
    private Long brand_id;
    private Long catalog_id;

    public Brand_CatalogDTO(Long brand_id, Long catalog_id) {
        this.brand_id = brand_id;
        this.catalog_id = catalog_id;
    }

    public Brand_CatalogDTO() {
    }
    
    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public Long getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(Long catalog_id) {
        this.catalog_id = catalog_id;
    }
    
    
}
