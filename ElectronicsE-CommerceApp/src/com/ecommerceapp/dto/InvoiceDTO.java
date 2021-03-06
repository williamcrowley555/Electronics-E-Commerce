/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hi
 */
public class InvoiceDTO {
    private Long id;
    private String recipientFirstName;
    private String recipientLastName;
    private String address;
    private String phone;
    private String note;
    private long total;
    private Integer status;
    private LocalDate orderDate;
    private LocalDate paymentDate;
    private LocalDate confirmationDate;
    private LocalDate shipDate;
    private LocalDate cancellingDate;
    private Long userId;
    private Long employeeId;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, String recipientFirstName, String recipientLastName, String address, String phone, String note, long total, Integer status, LocalDate orderDate, LocalDate paymentDate, LocalDate confirmationDate, LocalDate shipDate, LocalDate cancellingDate, Long userId, Long employeeId) {
        this.id = id;
        this.recipientFirstName = recipientFirstName;
        this.recipientLastName = recipientLastName;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
        this.paymentDate = paymentDate;
        this.confirmationDate = confirmationDate;
        this.shipDate = shipDate;
        this.cancellingDate = cancellingDate;
        this.userId = userId;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientFirstName() {
        return recipientFirstName;
    }

    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDate getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    public LocalDate getCancellingDate() {
        return cancellingDate;
    }

    public void setCancellingDate(LocalDate cancellingDate) {
        this.cancellingDate = cancellingDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getOrderDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return orderDate.format(formatter);
    }
    
    public String getConfirmDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (confirmationDate != null)
        return confirmationDate.format(formatter);
        else return null;
    }
    
    public String getPaidDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (paymentDate != null)
        return paymentDate.format(formatter);
        else return null;
    }
    
    public String getCancelDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (cancellingDate != null)
        return cancellingDate.format(formatter);
        else return null;
    }
    
    public String getShipDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (shipDate != null)
        return shipDate.format(formatter);
        else return null;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" + "id=" + id + ", recipientFirstName=" + recipientFirstName + ", recipientLastName=" + recipientLastName + ", address=" + address + ", phone=" + phone + ", note=" + note + ", total=" + total + ", status=" + status + ", orderDate=" + orderDate + ", paymentDate=" + paymentDate + ", confirmationDate=" + confirmationDate + ", shipDate=" + shipDate + ", cancellingDate=" + cancellingDate + ", userId=" + userId + ", employeeId=" + employeeId + '}';
    }
}
