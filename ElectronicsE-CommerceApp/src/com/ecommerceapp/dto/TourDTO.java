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
public class TourDTO {
    
    private Long id;
    private String tenTour;
    private Long idLoaiDuLich;
    private String dacDiem;

    public TourDTO() {
    }

    public TourDTO(Long id, String tenTour, Long idLoaiDuLich, String dacDiem) {
        this.id = id;
        this.tenTour = tenTour;
        this.idLoaiDuLich = idLoaiDuLich;
        this.dacDiem = dacDiem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public Long getIdLoaiDuLich() {
        return idLoaiDuLich;
    }

    public void setIdLoaiDuLich(Long idLoaiDuLich) {
        this.idLoaiDuLich = idLoaiDuLich;
    }

    public String getDacDiem() {
        return dacDiem;
    }

    public void setDacDiem(String dacDiem) {
        this.dacDiem = dacDiem;
    }

    @Override
    public String toString() {
        return "TourDTO{" + "id=" + id + ", tenTour=" + tenTour + ", idLoaiDuLich=" + idLoaiDuLich + ", dacDiem=" + dacDiem + '}';
    }
}
