package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Catalog")
@Table(name = "catalog",
        uniqueConstraints = {
                @UniqueConstraint(name = "catalog_name_unique", columnNames = "name")
        })
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên loại sản phầm không được để trống")
    private String name;

    public Catalog() {
    }

    public Catalog(String name) {
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
}
