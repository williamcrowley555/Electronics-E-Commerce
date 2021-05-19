/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

/**
 *
 * @author HP
 */
public class DichVuDTO {
    
    private Long id;
    private String tenDichVu;
    private String moTa;

    public DichVuDTO() {
    }

    public DichVuDTO(Long id, String tenDichVu, String moTa) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.moTa = moTa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "DichVuDTO{" + "id=" + id + ", tenDichVu=" + tenDichVu + ", moTa=" + moTa + '}';
    }
}
