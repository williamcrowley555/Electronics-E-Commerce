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
public class VaiTroNhanVienDoanDTO {
    private Long idDsNvDoan;
    private Long idVaiTro;
    
    public VaiTroNhanVienDoanDTO(){}

    public VaiTroNhanVienDoanDTO(Long idDsNvDoan, Long idVaiTro) {
        this.idDsNvDoan = idDsNvDoan;
        this.idVaiTro = idVaiTro;
    }

    public Long getIdDsNvDoan() {
        return idDsNvDoan;
    }

    public void setIdDsNvDoan(Long idDsNvDoan) {
        this.idDsNvDoan = idDsNvDoan;
    }

    public Long getIdVaiTro() {
        return idVaiTro;
    }

    public void setIdVaiTro(Long idVaiTro) {
        this.idVaiTro = idVaiTro;
    }
    
    @Override
    public String toString() {
        return "VaiTroNhanVienDoanDTO{" + "idDsNvDoan=" + idDsNvDoan + ", idVaiTro=" + idVaiTro + '}';
    }
}
