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
public class ThongKeDoanDTO {
    private Long id;
    private String tenDoan;
    private int soKhach;
    private Long giaTien;
    private Long doanhThu;
    private Long tongChiPhi;
    private Long lai;
    
    public ThongKeDoanDTO(){}

    public ThongKeDoanDTO(Long id, String tenDoan, int soKhach, Long giaTien, Long doanhThu, Long tongChiPhi, Long lai) {
        this.id = id;
        this.tenDoan = tenDoan;
        this.soKhach = soKhach;
        this.giaTien = giaTien;
        this.doanhThu = doanhThu;
        this.tongChiPhi = tongChiPhi;
        this.lai = lai;
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

    public int getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(int soKhach) {
        this.soKhach = soKhach;
    }

    public Long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Long giaTien) {
        this.giaTien = giaTien;
    }
    
    public Long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Long doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Long getTongChiPhi() {
        return giaTien;
    }

    public void setTongChiPhi(Long tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public Long getLai() {
        return lai;
    }

    public void setLai(Long lai) {
        this.lai = lai;
    }

    @Override
    public String toString() {
        return "ThongKeDoanDTO{" + "id=" + id + ", tenDoan=" + tenDoan + ", soKhach=" + soKhach + ", giaTien=" + giaTien + ", doanhThu=" + doanhThu + ", tongChiPhi=" + tongChiPhi + ", lai=" + lai + '}';
    }
}
