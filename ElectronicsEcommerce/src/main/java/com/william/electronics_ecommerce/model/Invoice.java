package com.william.electronics_ecommerce.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "total", nullable = false)
    private long total;

    @Column(name = "status")
    @Min(value = 1)
    @Max(value = 5)
//    1: Confirmed
//    2: Shipped
//    3: Paid
//    4: Cancelled
    private Integer status;

    @Column(name = "orderDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate = LocalDate.now();

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

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetails> details = new ArrayList<>();

    public Invoice() {
    }

    public Invoice(String recipientFirstName, String recipientLastName, String address, String phone, String note, long total, Integer status, LocalDate orderDate, LocalDate paymentDate, LocalDate confirmationDate, LocalDate shipDate, LocalDate cancellingDate, User user) {
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
        this.user = user;
    }

    public Invoice(String recipientFirstName, String recipientLastName, String address, String phone, String note, long total, Integer status, LocalDate orderDate, LocalDate paymentDate, LocalDate confirmationDate, LocalDate shipDate, LocalDate cancellingDate, User user, List<InvoiceDetails> details) {
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
        this.user = user;
        this.details = details;
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

    public String getRecipientFullName() {
        return recipientLastName + " " + recipientFirstName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return orderDate.format(formatter);
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

    public List<InvoiceDetails> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetails> details) {
        this.details = details;
    }

    public void addDetail(Product product, long price, int quantity, long subTotal) {
        this.details.add(new InvoiceDetails(this, product, price, quantity, subTotal));
    }

    public void setDetail(InvoiceDetails detail) {
        this.details.remove(detail);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", recipientFirstName='" + recipientFirstName + '\'' +
                ", recipientLastName='" + recipientLastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", total=" + total +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", paymentDate=" + paymentDate +
                ", confirmationDate=" + confirmationDate +
                ", shipDate=" + shipDate +
                ", cancellingDate=" + cancellingDate +
                ", user=" + user +
                ", details=" + details +
                '}';
    }
}
