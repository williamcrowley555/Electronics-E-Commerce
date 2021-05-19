/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;



/**
 *
 * @author HP
 */
public class NhanVienDTO {
    
    private Long id;
    private String ho;
    private String ten;
    private Boolean gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;
    private String sdt;
    private byte[] hinhAnh;
    private Long idVaiTro;
    private Boolean trangThai;
    
    public NhanVienDTO(){}

    public NhanVienDTO(Long id, String ho, String ten, Boolean gioiTinh, LocalDate ngaySinh, String diaChi, String sdt, byte[] hinhAnh, Long idVaiTro) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
        this.idVaiTro = idVaiTro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Long getIdVaiTro() {
        return idVaiTro;
    }

    public void setIdVaiTro(Long idVaiTro) {
        this.idVaiTro = idVaiTro;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.ho);
        hash = 83 * hash + Objects.hashCode(this.ten);
        hash = 83 * hash + Objects.hashCode(this.gioiTinh);
        hash = 83 * hash + Objects.hashCode(this.ngaySinh);
        hash = 83 * hash + Objects.hashCode(this.diaChi);
        hash = 83 * hash + Objects.hashCode(this.sdt);
        hash = 83 * hash + Arrays.hashCode(this.hinhAnh);
        hash = 83 * hash + Objects.hashCode(this.idVaiTro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (!Objects.equals(this.ho, other.ho)) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        if (!Objects.equals(this.diaChi, other.diaChi)) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.gioiTinh, other.gioiTinh)) {
            return false;
        }
        if (!Objects.equals(this.ngaySinh, other.ngaySinh)) {
            return false;
        }
        if (!Arrays.equals(this.hinhAnh, other.hinhAnh)) {
            return false;
        }
        if (!Objects.equals(this.idVaiTro, other.idVaiTro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "id=" + id + ", ho=" + ho + ", ten=" + ten + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", sdt=" + sdt + ", hinhAnh=" + hinhAnh + ", idVaiTro=" + idVaiTro + ", trangThai=" + trangThai + '}';
    }
    
    
}