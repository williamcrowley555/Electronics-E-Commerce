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
public class LoaiDuLichDTO {
    
    private Long id;
    private String tenLoaiDuLich;

    public LoaiDuLichDTO() {
    }

    public LoaiDuLichDTO(Long id, String tenLoaiDuLich) {
        this.id = id;
        this.tenLoaiDuLich = tenLoaiDuLich;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoaiDuLich() {
        return tenLoaiDuLich;
    }

    public void setTenLoaiDuLich(String tenLoaiDuLich) {
        this.tenLoaiDuLich = tenLoaiDuLich;
    }

    @Override
    public String toString() {
        return "LoaiDuLichDTO{" + "id=" + id + ", tenLoaiDuLich=" + tenLoaiDuLich + '}';
    }
}
