package com.ruwaytech.shared.dto;

import com.ruwaytech.entity.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Integer idCustomer;
    private String name;
    private String dni;
    private String phone;
    private String email;
    private Set<OrderEntity> listOrders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(idCustomer, that.idCustomer) && Objects.equals(name, that.name) && Objects.equals(dni, that.dni) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(listOrders, that.listOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, dni, phone, email, listOrders);
    }

}