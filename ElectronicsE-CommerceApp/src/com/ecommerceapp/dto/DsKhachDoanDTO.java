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
public class DsKhachDoanDTO {
    private Long idDoan;
    private Long idKhach;
    
    public DsKhachDoanDTO(){}

    public DsKhachDoanDTO(Long idDoan, Long idKhach) {
        this.idDoan = idDoan;
        this.idKhach = idKhach;
    }

    public Long getIdDoan() {
        return idDoan;
    }

    public void setIdDoan(Long idDoan) {
        this.idDoan = idDoan;
    }

    public Long getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(Long idKhach) {
        this.idKhach = idKhach;
    }
    
    @Override
    public String toString() {
        return "DsKhachDoanDTO{" + "id=" + idDoan + ", idKhach=" + idKhach + '}';
    }
}
