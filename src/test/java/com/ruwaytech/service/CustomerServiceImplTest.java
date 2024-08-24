package com.ruwaytech.service;

import com.ruwaytech.entity.CustomerEntity;
import com.ruwaytech.repository.CustomerRepository;
import com.ruwaytech.service.impl.CustomerServiceImpl;
import com.ruwaytech.shared.dto.CustomerDto;
import com.ruwaytech.shared.exception.RestaurantException;
import com.ruwaytech.shared.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<CustomerEntity> entities = Arrays.asList(new CustomerEntity(), new CustomerEntity());
        List<CustomerDto> dtos = Arrays.asList(new CustomerDto(), new CustomerDto());

        when(customerRepository.findAll()).thenReturn(entities);
        when(customerMapper.toListDtos(entities)).thenReturn(dtos);

        List<CustomerDto> result = customerService.getAll();

        assertEquals(dtos, result);
        verify(customerRepository).findAll();
        verify(customerMapper).toListDtos(entities);
    }

    @Test
    void testGetById_ExistingCustomer() {
        Integer id = 1;
        CustomerEntity entity = new CustomerEntity();
        CustomerDto dto = new CustomerDto();

        when(customerRepository.findById(id)).thenReturn(Optional.of(entity));
        when(customerMapper.toDto(entity)).thenReturn(dto);

        CustomerDto result = customerService.getById(id);

        assertEquals(dto, result);
        verify(customerRepository).findById(id);
        verify(customerMapper).toDto(entity);
    }

    @Test
    void testGetById_NonExistingCustomer() {
        Integer id = 1;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RestaurantException.class, () -> customerService.getById(id));
        verify(customerRepository).findById(id);
    }

    @Test
    void testCreate() {
        CustomerDto inputDto = new CustomerDto();
        CustomerEntity entity = new CustomerEntity();
        CustomerEntity savedEntity = new CustomerEntity();
        CustomerDto outputDto = new CustomerDto();

        when(customerMapper.toEntityIgnoredId(inputDto)).thenReturn(entity);
        when(customerRepository.save(entity)).thenReturn(savedEntity);
        when(customerMapper.toDto(savedEntity)).thenReturn(outputDto);

        CustomerDto result = customerService.create(inputDto);

        assertEquals(outputDto, result);
        verify(customerMapper).toEntityIgnoredId(inputDto);
        verify(customerRepository).save(entity);
        verify(customerMapper).toDto(savedEntity);
    }

    @Test
    void testUpdate_ExistingCustomer() {
        Integer id = 1;
        CustomerDto inputDto = new CustomerDto();
        CustomerEntity existingEntity = new CustomerEntity();
        CustomerEntity updatedEntity = new CustomerEntity();
        CustomerDto outputDto = new CustomerDto();

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(customerRepository.save(existingEntity)).thenReturn(updatedEntity);
        when(customerMapper.toDto(updatedEntity)).thenReturn(outputDto);

        CustomerDto result = customerService.update(id, inputDto);

        assertEquals(outputDto, result);
        verify(customerRepository).findById(id);
        verify(customerMapper).updateEntityFromDtoIgnoredId(inputDto, existingEntity);
        verify(customerRepository).save(existingEntity);
        verify(customerMapper).toDto(updatedEntity);
    }

    @Test
    void testUpdate_NonExistingCustomer() {
        Integer id = 1;
        CustomerDto inputDto = new CustomerDto();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RestaurantException.class, () -> customerService.update(id, inputDto));
        verify(customerRepository).findById(id);
    }

    @Test
    void testDelete_ExistingCustomer() {
        Integer id = 1;
        CustomerEntity entity = new CustomerEntity();
        CustomerDto dto = new CustomerDto();

        when(customerRepository.findById(id)).thenReturn(Optional.of(entity));
        when(customerMapper.toDto(entity)).thenReturn(dto);

        CustomerDto result = customerService.delete(id);

        assertEquals(dto, result);
        verify(customerRepository).findById(id);
        verify(customerRepository).delete(entity);
        verify(customerMapper).toDto(entity);
    }

    @Test
    void testDelete_NonExistingCustomer() {
        Integer id = 1;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RestaurantException.class, () -> customerService.delete(id));
        verify(customerRepository).findById(id);
    }
}