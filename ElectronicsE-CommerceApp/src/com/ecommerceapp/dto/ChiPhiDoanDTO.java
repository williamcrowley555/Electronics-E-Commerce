/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

import java.time.LocalDate;

/**
 *
 * @author kossp
 */
public class ChiPhiDoanDTO {
    private Long id;
    private Long idDoan;
    private Long idDichVu;
    private Integer chiPhi;
    private String hoaDon;
    private LocalDate ngayHoaDon;
    
    public ChiPhiDoanDTO(){}

    public ChiPhiDoanDTO(Long id, Long idDoan, Long idDichVu, Integer chiPhi, String hoaDon, LocalDate ngayHoaDon) {
        this.id = id;
        this.idDoan = idDoan;
        this.idDichVu = idDichVu;
        this.chiPhi = chiPhi;
        this.hoaDon = hoaDon;
        this.ngayHoaDon = ngayHoaDon;
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

    public Long getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(Long idDichVu) {
        this.idDichVu = idDichVu;
    }

    public Integer getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(Integer chiPhi) {
        this.chiPhi = chiPhi;
    }
    
    public String getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(String hoaDon) {
        this.hoaDon = hoaDon;
    }
    
    public LocalDate getNgayHoaDon() {
        return ngayHoaDon;
    }

    public void setNgayHoaDon(LocalDate ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }
    @Override
    public String toString() {
        return "ChiPhiDoanDTO{" + "id=" + id + ", idDoan=" + idDoan + ", idDichVu=" + idDichVu + ", chiPhi=" + chiPhi + ", hoaDon=" + hoaDon + ", ngayHoaDon=" + ngayHoaDon + '}';
    }    
}
