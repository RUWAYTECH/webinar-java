package com.ruwaytech.shared.mapper;

import com.ruwaytech.entity.CustomerEntity;
import com.ruwaytech.shared.dto.CustomerDto;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Named("CustomerMapper.toDto")
    public CustomerDto toDto(CustomerEntity entity);
    @Named("CustomerMapper.toEntity")
    public CustomerEntity toEntity(CustomerDto dto);
    @Named("CustomerMapper.toEntityIgnoredId")
    @Mapping(target = "idCustomer", ignore = true)
    public CustomerEntity toEntityIgnoredId(CustomerDto dto);
    @Named("CustomerMapper.toListDtos")
    @IterableMapping(qualifiedByName = "CustomerMapper.toDto")
    Collection<CustomerDto> toListDtos(Collection<CustomerEntity> listEntities);
    @Named("CustomerMapper.toListEntities")
    @IterableMapping(qualifiedByName = "CustomerMapper.toEntity")
    Collection<CustomerEntity> toListEntities(Collection<CustomerDto> listDtos);
    @Named("CustomerMapper.updateEntityFromDto")
    void updateEntityFromDto(CustomerDto dto, @MappingTarget CustomerEntity entity);
    @Named("CustomerMapper.updateEntityFromDtoIgnoredId")
    @Mapping(target = "idCustomer", ignore = true)
    void updateEntityFromDtoIgnoredId(CustomerDto dto, @MappingTarget CustomerEntity entity);
}
