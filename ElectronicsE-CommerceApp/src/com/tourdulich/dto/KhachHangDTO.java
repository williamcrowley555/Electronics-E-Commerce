/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;



/**
 *
 * @author HP
 */
public class KhachHangDTO {
    
    private Long id;
    private String ho;
    private String ten;
    private Boolean gioiTinh;
    private String cmnd;
    private String sdt;
    private String diaChi;
    private byte[] hinhAnh;
    private LocalDate ngaySinh;
    
    public KhachHangDTO(){}

    public KhachHangDTO(Long id, String ho, String ten, Boolean gioiTinh, String cmnd, String sdt, String diaChi, byte[] hinhAnh, LocalDate ngaySinh) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.hinhAnh = hinhAnh;
        this.ngaySinh = ngaySinh;
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

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "id=" + id + ", ho=" + ho + ", ten=" + ten + ", gioiTinh=" + gioiTinh + ", cmnd=" + cmnd + ", sdt=" + sdt + ", diaChi=" + diaChi + ", hinhAnh=" + hinhAnh + ", ngaySinh=" + ngaySinh + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.ho);
        hash = 79 * hash + Objects.hashCode(this.ten);
        hash = 79 * hash + Objects.hashCode(this.gioiTinh);
        hash = 79 * hash + Objects.hashCode(this.cmnd);
        hash = 79 * hash + Objects.hashCode(this.sdt);
        hash = 79 * hash + Objects.hashCode(this.diaChi);
        hash = 79 * hash + Arrays.hashCode(this.hinhAnh);
        hash = 79 * hash + Objects.hashCode(this.ngaySinh);
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
        final KhachHangDTO other = (KhachHangDTO) obj;
        if (!Objects.equals(this.ho, other.ho)) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        if (!Objects.equals(this.cmnd, other.cmnd)) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        if (!Objects.equals(this.diaChi, other.diaChi)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.gioiTinh, other.gioiTinh)) {
            return false;
        }
        if (!Arrays.equals(this.hinhAnh, other.hinhAnh)) {
            return false;
        }
        if (!Objects.equals(this.ngaySinh, other.ngaySinh)) {
            return false;
        }
        return true;
    }
}