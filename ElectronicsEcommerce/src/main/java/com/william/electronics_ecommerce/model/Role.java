package com.william.electronics_ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity(name = "Role")
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(name = "role_name_unique", columnNames = "name"),
                @UniqueConstraint(name = "role_normalized_name_unique", columnNames = "normalized_name")
        })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$")
    private String name;

    @Column(name = "normalized_name", nullable = false)
    @NotBlank
    @Pattern(regexp = "^[A-Za-z_ ]+$")
    private String normalizedName;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, @NotBlank @Pattern(regexp = "^[A-Za-z ]+$") String name, @NotBlank @Pattern(regexp = "^[\\p{L}A-Za-z_ ]+$") String normalizedName) {
        this.id = id;
        this.name = name;
        this.normalizedName = normalizedName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
