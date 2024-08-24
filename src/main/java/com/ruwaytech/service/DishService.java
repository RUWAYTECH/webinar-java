package com.ruwaytech.service;

import com.ruwaytech.shared.dto.DishDto;
import java.util.List;

public interface DishService {

    List<DishDto> getAll();
    DishDto getById(Integer idDish);
    DishDto create(DishDto dishDto);
    DishDto update(Integer idDish, DishDto dishDto);
    DishDto delete(Integer idDish);

}