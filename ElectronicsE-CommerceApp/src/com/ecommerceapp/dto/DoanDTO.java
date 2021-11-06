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
public class DoanDTO {
    private Long id;
    private String tenDoan;
    private LocalDate ngayKhoiHanh;
    private LocalDate ngayKetThuc;
    private Long idTour;
    private int soLuong;
    private Long giaTien;
    
    public DoanDTO(){}

    public DoanDTO(Long id, String tenDoan, LocalDate ngayKhoiHanh, LocalDate ngayKetThuc, Long idTour, int soLuong, Long giaTien) {
        this.id = id;
        this.tenDoan = tenDoan;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.idTour = idTour;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public Long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Long giaTien) {
        this.giaTien = giaTien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDoan() {
        return tenDoan;
    }

    public void setTenDoan(String tenDoan) {
        this.tenDoan = tenDoan;
    }

    public LocalDate getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Long getIdTour() {
        return idTour;
    }

    public void setIdTour(Long idTour) {
        this.idTour = idTour;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    @Override
    public String toString() {
        return "DoanDTO{" + "id=" + id + ", tenDoan=" + tenDoan + ", ngayKhoiHanh=" + ngayKhoiHanh + ", ngayKetThuc=" + ngayKetThuc + ", idTour=" + idTour + ", soLuong=" + soLuong + ", giaTien=" + giaTien + '}';
    }
}
