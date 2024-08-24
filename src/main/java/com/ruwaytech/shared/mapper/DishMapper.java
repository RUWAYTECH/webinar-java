package com.ruwaytech.shared.mapper;
import com.ruwaytech.entity.DishEntity;
import com.ruwaytech.shared.dto.DishDto;
import org.mapstruct.*;
import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {

    @Named("DishMapper.toDto")
    public DishDto toDto(DishEntity entity);
    @Named("DishMapper.toEntity")
    public DishEntity toEntity(DishDto dto);
    @Named("DishMapper.toEntityIgnoredId")
    @Mapping(target = "idDish", ignore = true)
    public DishEntity toEntityIgnoredId(DishDto dto);
    @Named("DishMapper.toListDtos")
    @IterableMapping(qualifiedByName = "DishMapper.toDto")
    Collection<DishDto> toListDtos(Collection<DishEntity> listEntities);
    @Named("DishMapper.toListEntities")
    @IterableMapping(qualifiedByName = "DishMapper.toEntity")
    Collection<DishEntity> toListEntities(Collection<DishDto> listDtos);
    @Named("DishMapper.updateEntityFromDto")
    void updateEntityFromDto(DishDto dto, @MappingTarget DishEntity entity);
    @Named("DishMapper.updateEntityFromDtoIgnoredId")
    @Mapping(target = "idDish", ignore = true)
    void updateEntityFromDtoIgnoredId(DishDto dto, @MappingTarget DishEntity entity);
}