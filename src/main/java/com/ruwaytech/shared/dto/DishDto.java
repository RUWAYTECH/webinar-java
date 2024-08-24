package com.ruwaytech.shared.dto;

import com.ruwaytech.entity.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
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
public class DishDto {

    private Integer idDish;
    private String name;
    private BigDecimal price;
    private Set<OrderEntity> listOrders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dishDto = (DishDto) o;
        return Objects.equals(idDish, dishDto.idDish) && Objects.equals(name, dishDto.name) && Objects.equals(price, dishDto.price) && Objects.equals(listOrders, dishDto.listOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDish, name, price, listOrders);
    }

}