package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "Product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên sản phầm không được để trống")
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 0, message = "Giá tiền không hợp lệ")
    @Max(value = 1000000000, message = "Giá tiền quá lớn")
    private Long price;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "status", nullable = false, columnDefinition="tinyint(1) default 1")
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @Valid
    private Brand brand;

    public Product() {
    }

    public Product(String name, Long price, String description, boolean status, Brand brand) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.brand = brand;
    }

    public Product(String name, Long price, String description, String image, boolean status, Brand brand) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.brand = brand;
    }

    public Product(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
