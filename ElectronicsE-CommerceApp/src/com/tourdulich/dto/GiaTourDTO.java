/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

import java.time.LocalDate;

/**
 *
 * @author kossp
 */
public class GiaTourDTO {
    private Long id;
    private Long idTour;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private Long giaTien;
    
    public GiaTourDTO(){}

    public GiaTourDTO(Long id, Long idTour, LocalDate ngayBatDau, LocalDate ngayKetThuc, Long giaTien) {
        this.id = id;
        this.idTour = idTour;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTien = giaTien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTour() {
        return idTour;
    }

    public void setIdTour(Long idTour) {
        this.idTour = idTour;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Long giaTien) {
        this.giaTien = giaTien;
    }
    
     @Override
    public String toString() {
        return "GiaTourDTO{" + "id=" + id + ", idTour=" + idTour + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", giaTien=" + giaTien + '}';
    }
}
