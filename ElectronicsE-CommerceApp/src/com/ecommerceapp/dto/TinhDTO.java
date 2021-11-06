/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

/**
 *
 * @author kossp
 */
public class TinhDTO {
    private Long id;
    private String tenTinh;
    
    public TinhDTO(){}

    public TinhDTO(Long id, String tenTinh) {
        this.id = id;
        this.tenTinh = tenTinh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
    
    @Override
    public String toString() {
        return "TinhDTO{" + "id=" + id + ", tenTinh=" + tenTinh + '}';
    }
}
