package com.ruwaytech.entity;

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
@Entity
@Table(name = "dishes")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish")
    private Integer idDish;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "price", nullable = false, precision = 9, scale = 2)
    private BigDecimal price;
    @Builder.Default
    @ManyToMany(mappedBy = "listDishes")
    private Set<OrderEntity> listOrders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishEntity that = (DishEntity) o;
        return Objects.equals(idDish, that.idDish) && Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDish, name, price);
    }

}