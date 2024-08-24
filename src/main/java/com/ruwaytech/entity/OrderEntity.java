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
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;
    @Column(name = "total", nullable = false, precision = 9, scale = 2)
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private CustomerEntity customer;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "orders_dishes",
            joinColumns = @JoinColumn(name = "id_order_fk", referencedColumnName = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_dish_fk", referencedColumnName = "id_dish"))
    private Set<DishEntity> listDishes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(total, that.total) && Objects.equals(customer, that.customer) && Objects.equals(listDishes, that.listDishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, total, customer, listDishes);
    }

}