package com.ruwaytech.controller;

import com.ruwaytech.service.DishService;
import com.ruwaytech.shared.dto.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDto>> getAll() {
        List<DishDto> listDishes = this.dishService.getAll();
        return ResponseEntity.ok(listDishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getById(@PathVariable Integer id) {
        DishDto dish = this.dishService.getById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<DishDto> create(@RequestBody DishDto dishDto) {
        DishDto dish = this.dishService.create(dishDto);
        return ResponseEntity.ok(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> update(@PathVariable(name = "id") Integer idDish, @RequestBody DishDto dishDto) {
        DishDto dish = this.dishService.update(idDish, dishDto);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DishDto> delete(@PathVariable(name = "id") Integer idDish) {
        DishDto dish = this.dishService.delete(idDish);
        return ResponseEntity.ok(dish);
    }

}