package com.ruwaytech.repository;

import com.ruwaytech.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Integer> {

}