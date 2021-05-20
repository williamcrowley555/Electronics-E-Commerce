package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Brand")
@Table(name = "brand",
        uniqueConstraints = {
                @UniqueConstraint(name = "brand_name_unique", columnNames = "name")
        })
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên thương hiệu không được để trống")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "brand_catalog",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "catalog_id")
    )
    @Valid
    private Set<Catalog> catalogList = new HashSet<>();

    public Brand() {
    }

    public Brand(String name) {
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

    public Set<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(Set<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
