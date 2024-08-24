package com.ruwaytech.shared.dto;

import com.ruwaytech.entity.CustomerEntity;
import com.ruwaytech.entity.DishEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Integer idOrder;
    private BigDecimal total;
    private CustomerEntity customer;
    private Set<DishEntity> listDishes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(idOrder, orderDto.idOrder) && Objects.equals(total, orderDto.total) && Objects.equals(customer, orderDto.customer) && Objects.equals(listDishes, orderDto.listDishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, total, customer, listDishes);
    }

}