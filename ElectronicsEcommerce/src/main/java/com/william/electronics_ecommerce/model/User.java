package com.william.electronics_ecommerce.model;

import com.william.electronics_ecommerce.validation.Age;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user",
        uniqueConstraints = {
                        @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                        @UniqueConstraint(name = "user_phone_unique", columnNames = "phone")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$", message = "Tên không hợp lệ")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Họ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z]+$", message = "Họ không hợp lệ")
    private String lastName;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Age
    private LocalDate dob;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Hãy chọn giới tính")
    @Min(value = 0)
    @Max(value = 5)
    private Integer gender = 1;

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

    @Column(name = "password", nullable = false)
    @Size(min = 5, message = "Mật khẩu tối thiểu 5 ký tự")
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition="tinyint(1) default 1")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns = {@JoinColumn(name = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Valid
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(@NotBlank(message = "Tên không được để trống") @Pattern(regexp = "^[\\p{L}A-Za-z ]+$", message = "Tên không hợp lệ") String firstName, @NotBlank(message = "Họ không được để trống") @Pattern(regexp = "^[\\p{L}A-Za-z]+$", message = "Họ không hợp lệ") String lastName, @NotNull(message = "Ngày sinh không được để trống") @Age LocalDate dob, @NotNull(message = "Hãy chọn giới tính") @Min(value = 0) @Max(value = 5) Integer gender, @NotBlank(message = "Địa chỉ không được để trống") @Pattern(regexp = "^[A-Za-z0-9.,\\s\\-\\/]+$", message = "Địa chỉ không hợp lệ") String address, @NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "0{1}\\d{9,10}", message = "Số điện thoại không hợp lệ") String phone, @NotBlank(message = "Email không được để trống") @Email(message = "Email không hợp lệ") String email, @Size(min = 5, message = "Mật khẩu tối thiểu 5 ký tự") String password, @Valid Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public String getDobFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dob.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
