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
public class DsDiaDiemTourDTO {
    
    private Long idTour;
    private Long idDiaDiem;
    private Integer stt;

    public DsDiaDiemTourDTO() {
    }

    public DsDiaDiemTourDTO(Long idTour, Long idDiaDiem, Integer stt) {
        this.idTour = idTour;
        this.idDiaDiem = idDiaDiem;
        this.stt = stt;
    }

    public Long getIdTour() {
        return idTour;
    }

    public void setIdTour(Long idTour) {
        this.idTour = idTour;
    }

    public Long getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(Long idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    @Override
    public String toString() {
        return "DsDiaDiemTourDTO{" + "idTour=" + idTour + ", idDiaDiem=" + idDiaDiem + ", stt=" + stt + '}';
    }
}
