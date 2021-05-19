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
public class ThongKeTourDTO {
    private Long tourId;
    private String tenTour;
    private Long tongSoDoan;
    private Long tongDoanhThu;
    private Long tongChiPhi;
    private Long lai;

    public ThongKeTourDTO() {
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public Long getTongSoDoan() {
        return tongSoDoan;
    }

    public void setTongSoDoan(Long tongSoDoan) {
        this.tongSoDoan = tongSoDoan;
    }

    public Long getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(Long tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public Long getTongChiPhi() {
        return tongChiPhi;
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
        return "ThongKeTourDTO{" + "tourId=" + tourId + ", tenTour=" + tenTour + ", tongSoDoan=" + tongSoDoan + ", tongDoanhThu=" + tongDoanhThu + ", tongChiPhi=" + tongChiPhi + ", lai=" + lai + '}';
    }
    
    
}
