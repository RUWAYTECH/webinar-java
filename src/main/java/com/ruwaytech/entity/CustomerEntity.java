package com.ruwaytech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer idCustomer;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;
    @Column(name = "phone", nullable = false, length = 9, unique = true)
    private String phone;
    @Column(name = "email", nullable = true, unique = true)
    private String email;
    @Builder.Default
    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> listOrders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(idCustomer, that.idCustomer) && Objects.equals(name, that.name) && Objects.equals(dni, that.dni) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, dni, phone, email);
    }

}