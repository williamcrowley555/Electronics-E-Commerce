/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

/**
 *
 * @author HP
 */
public class VaiTroDTO {
    
    private Long id;
    private String tenVaiTro;

    public VaiTroDTO() {
    }

    public VaiTroDTO(Long id, String tenVaiTro) {
        this.id = id;
        this.tenVaiTro = tenVaiTro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    @Override
    public String toString() {
        return "VaiTroDTO{" + "id=" + id + ", tenVaiTro=" + tenVaiTro + '}';
    }
}
