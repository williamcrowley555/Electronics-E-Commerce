/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

/**
 *
 * @author kossp
 */
public class DiaDiemDTO {
    private Long id;
    private String tenDiaDiem;
    private String diaChi;
    private String gioiThieu;
    private byte[] hinhAnh; 
    private Long idTinh;
    
    public DiaDiemDTO() {}

    public DiaDiemDTO(Long id, String tenDiaDiem, String diaChi, String gioiThieu, byte[] hinhAnh, Long idTinh) {
        this.id = id;
        this.tenDiaDiem = tenDiaDiem;
        this.diaChi = diaChi;
        this.gioiThieu = gioiThieu;
        this.hinhAnh = hinhAnh;
        this.idTinh = idTinh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Long getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(Long idTinh) {
        this.idTinh = idTinh;
    }
    
    @Override
    public String toString() {
        return "DiaDiemDTO{" + "id=" + id + ", tenDiaDiem=" + tenDiaDiem + ", diaChi=" + diaChi + ", gioiThieu=" + gioiThieu + ", hinhAnh=" + hinhAnh + ", idTinh=" + idTinh + '}';
    }

}
