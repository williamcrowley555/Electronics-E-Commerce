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
    private byte[] image;
    private boolean status = true;
    private long brandId;
    
    public ProductDTO(String name, Long price, String description, boolean status, long brandId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.brandId = brandId;
    }

    public ProductDTO(String name, Long price, String description, byte[] image, boolean status, long brandId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.brandId = brandId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    
}