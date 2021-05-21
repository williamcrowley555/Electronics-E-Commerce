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
public class ProductDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String base64Image;
    private boolean status = true;
    private int quantity;
    private long brandId;
    private long catalogId;
    
    
    public ProductDTO(String name, Long price, String description, boolean status, int quantity, long brandId, long catalogId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.quantity = quantity;
        this.brandId = brandId;
        this.catalogId = catalogId;
    }


    public ProductDTO(Long id, String name, Long price, String description, String base64Image, int quantity, long brandId, long catalogId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.base64Image = base64Image;
        this.quantity = quantity;
        this.brandId = brandId;
        this.catalogId = catalogId;
    }
    
    

    public ProductDTO(String name) {
        this.name = name;
    }

    public ProductDTO() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    
    
}
