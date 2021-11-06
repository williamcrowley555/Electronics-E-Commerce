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
public class DsNhanVienDoanDTO {
    
    private Long id;
    private Long idDoan;
    private Long idNhanVien;

    public DsNhanVienDoanDTO() {
    }

    public DsNhanVienDoanDTO(Long idDoan, Long idNhanVien) {     
        this.idDoan = idDoan;
        this.idNhanVien = idNhanVien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoan() {
        return idDoan;
    }

    public void setIdDoan(Long idDoan) {
        this.idDoan = idDoan;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    @Override
    public String toString() {
        return "DsNhanVienDoanDTO{" + "id=" + id + ", idDoan=" + idDoan + ", idNhanVien=" + idNhanVien + '}';
    }
}
