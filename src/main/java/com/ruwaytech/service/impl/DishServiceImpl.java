package com.ruwaytech.service.impl;

import com.ruwaytech.entity.DishEntity;
import com.ruwaytech.repository.DishRepository;
import com.ruwaytech.service.DishService;
import com.ruwaytech.shared.dto.DishDto;
import com.ruwaytech.shared.exception.RestaurantException;
import com.ruwaytech.shared.mapper.DishMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<DishDto> getAll() {
        List<DishEntity> listEntities = this.dishRepository.findAll();
        Collection<DishDto> listDishes = this.dishMapper.toListDtos(listEntities);
        return List.copyOf(listDishes);
    }

    @Override
    public DishDto getById(Integer idDish) {
        Optional<DishEntity> optional = this.dishRepository.findById(idDish);

        if (optional.isPresent()) {
            DishEntity dishEntity = optional.get();
            return this.dishMapper.toDto(dishEntity);
        } else {
            throw new RestaurantException("No existe el plato con id: " + idDish);
        }
    }

    @Override
    public DishDto create(DishDto dishDto) {
        DishEntity dishEntity = this.dishMapper.toEntityIgnoredId(dishDto);
        DishEntity dishCreated = this.dishRepository.save(dishEntity);
        return this.dishMapper.toDto(dishCreated);
    }

    @Override
    public DishDto update(Integer idDish, DishDto dishDto) {
        Optional<DishEntity> optional = this.dishRepository.findById(idDish);

        if (optional.isPresent()) {
            DishEntity dishEntity = optional.get();
            this.dishMapper.updateEntityFromDtoIgnoredId(dishDto, dishEntity);
            DishEntity dishUpdated = this.dishRepository.save(dishEntity);
            return this.dishMapper.toDto(dishUpdated);
        } else {
            throw new RestaurantException("No existe el plato con id: " + idDish);
        }
    }

    @Override
    public DishDto delete(Integer idDish) {
        Optional<DishEntity> optional = this.dishRepository.findById(idDish);
        if (optional.isPresent()) {
            DishEntity dishEntity = optional.get();
            this.dishRepository.delete(dishEntity);
            return this.dishMapper.toDto(dishEntity);
        } else {
            throw new RestaurantException("No existe el plato con id: " + idDish);
        }
    }
}
