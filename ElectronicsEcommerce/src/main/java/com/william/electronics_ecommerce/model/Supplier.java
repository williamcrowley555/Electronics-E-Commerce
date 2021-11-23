package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "Supplier")
@Table(name = "supplier",
        uniqueConstraints = {
                @UniqueConstraint(name = "supplier_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "supplier_phone_unique", columnNames = "phone")
        })
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Tên nhà cung cấp không được để trống")
    private String companyName;

    @Column(name = "contact_name", nullable = false)
    @NotBlank(message = "Tên người liên lạc không được để trống")
    private String contactName;

    @Column(name = "contact_job_title", nullable = false)
    @NotBlank(message = "Chức vụ người liên lạc không được để trống")
    private String contactJobTitle;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Địa chỉ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z0-9.,\\s\\-\\/]+$", message = "Địa chỉ không hợp lệ")
    private String address;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "0{1}\\d{9,10}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(name = "country", nullable = false)
    @NotBlank(message = "Quốc gia không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$", message = "Quốc gia không hợp lệ")
    private String country;

    public Supplier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactJobTitle='" + contactJobTitle + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
