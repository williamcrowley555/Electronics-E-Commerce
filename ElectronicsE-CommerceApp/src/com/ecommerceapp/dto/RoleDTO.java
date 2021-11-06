/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

import java.util.Objects;

/**
 *
 * @author Hi
 */
public class RoleDTO {
    private Long id;
    private String name;
    private String normalizedName;
    
    public RoleDTO(Long id) {
        this.id = id;
    }

    public RoleDTO(Long id, String name, String normalizedName) {
        this.id = id;
        this.name = name;
        this.normalizedName = normalizedName;
    }
    
    public RoleDTO() {}
    
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

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO role = (RoleDTO) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
