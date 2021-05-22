package com.william.electronics_ecommerce.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "Invoice")
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipient_first_name", nullable = false)
    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$", message = "Tên không hợp lệ")
    private String recipientFirstName;

    @Column(name = "recipient_last_name", nullable = false)
    @NotBlank(message = "Họ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z]+$", message = "Họ không hợp lệ")
    private String recipientLastName;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Địa chỉ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z0-9.,\\s\\-\\/]+$", message = "Địa chỉ không hợp lệ")
    private String address;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "0{1}\\d{9,10}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Column(name = "total", nullable = false)
    @NotNull(message = "Tổng tiền không được để trống")
    private long total;

    @Column(name = "status", nullable = false)
    @Min(value = 1)
    @Max(value = 5)
//    1: Confirmed
//    2: Shipped
//    3: Cancelled
    private Integer status;

    @Column(name = "orderDate", nullable = false)
    @NotNull(message = "Ngày đặt hàng không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Column(name = "paymentDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(name = "confirmationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate confirmationDate;

    @Column(name = "shipDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shipDate;

    @Column(name = "cancellingDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancellingDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Valid
    private User user;

    public Invoice() {
    }

    public Invoice(String recipientFirstName, String recipientLastName, String address, String phone, long total, Integer status, LocalDate orderDate, LocalDate paymentDate, LocalDate confirmationDate, LocalDate shipDate, LocalDate cancellingDate, User user) {
        this.recipientFirstName = recipientFirstName;
        this.recipientLastName = recipientLastName;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
        this.paymentDate = paymentDate;
        this.confirmationDate = confirmationDate;
        this.shipDate = shipDate;
        this.cancellingDate = cancellingDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", recipientFirstName='" + recipientFirstName + '\'' +
                ", recipientLastName='" + recipientLastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", total=" + total +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", paymentDate=" + paymentDate +
                ", confirmationDate=" + confirmationDate +
                ", shipDate=" + shipDate +
                ", cancellingDate=" + cancellingDate +
                ", user=" + user +
                '}';
    }
}
